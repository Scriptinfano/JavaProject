//自动求解列车车厢重排列问题
package carriage;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;


/**
 * 车厢重排解算器
 * 使用方法：实例化此类，构造器参数填写列车车厢从车头到车尾的顺序
 * 然后对象调用run()方法即可输出求解步骤
 *
 * @author Mingxiang
 * @date 2022/09/16
 */
public class CarriageRearrangementSolver {
    private final int[] inputOrder;//记录车厢初始排列顺序的数组，索引从入轨道的右端到左端依次递增，车厢的初始顺序:[1,numberOfTracks]
    private ArrayList<Stack<Integer>> bufferTrack;//缓冲轨道数组
    private int nextCarToOutTrack = 0;//出轨道最左端车厢的编号

    private int trackInIndex;//应该进入的轨道的编号
    private int itsTrack = 0;//当所有缓冲轨道为空时，此属性代表默认的车厢待入轨道的编号

    private ArrayBlockingQueue<Integer> outTrack;//代表出轨道的队列集合

    /**
     * 设定列车的车厢数以及缓冲轨道的数量
     *
     * @param theInputOrder 车厢的初始排列顺序所代表的数组
     */
    public CarriageRearrangementSolver(int[] theInputOrder) {
        //列车的缓冲轨道和一开始的列车数量有一定的关系，目前尚不得知，所以暂时先以9个车厢和3个缓冲轨道来计算，尚不能任意指定车厢的数量
        bufferTrack = new ArrayList<Stack<Integer>>(getNumOfTracks());//为什么要加一：因为要使得缓冲轨道的编号从1开始
        for (int i = 0; i < getNumOfTracks(); i++) {
            bufferTrack.add(new Stack<Integer>());
        }
        inputOrder = new int[theInputOrder.length + 1];//为什么要加一：因为车厢的初始顺序:[1,numberOfTracks]，下标从1开始比较方便
        System.arraycopy(theInputOrder, 0, inputOrder, 1, theInputOrder.length);
        outTrack = new ArrayBlockingQueue<>(theInputOrder.length);//初始化出轨道

    }

    public static void main(String[] args) {

        int[] InTrackArray = {7, 2, 5, 4, 6, 1, 3, 9, 8};
        CarriageRearrangementSolver solver = new CarriageRearrangementSolver(InTrackArray);
        solver.run();
    }

    /**
     * 这是一个关于车厢数量和能重排车厢序列所要求的缓冲轨道数量之间的一个函数关系，目前尚不得知该函数关系，因此目前默认返回3以解决9个车厢的车厢重排问题
     *
     * @return int 返回根据车厢数量计算得出的需要多少缓冲轨道来计算此重排问题
     */
    private int getNumOfTracks() {
        return 3;
    }

    /**
     * 整个求解过程的主流程控制函数
     */
    public boolean run() {
        for (int i = 1; i < inputOrder.length; i++) {
            //该for循环中的每一次循环代表处理每个入轨道的车厢的过程

            if (inputOrder[i] == nextCarToOutTrack + 1) {
                //当前正在处理的车厢适合直接放入出轨道，无需经过缓冲轨道的处理
                System.out.println("将入轨道中编号为" + inputOrder[i] + "的车厢直接移动到出轨道");
                outTrack.offer(inputOrder[i]);//将当前车厢直接放入出轨道
                nextCarToOutTrack++;//此时nextCarToOutTrack变为1代表出轨道
                continue;
            }
            //以下情况是需要经过缓冲轨道处理的

            if (!allTrackIsEmpty()) {
                //缓冲轨道并不全是为空的情况

                //先检查有车厢的轨道，判断所有有车厢轨道顶部的车厢编号之中，是否有大于当前车厢编号且该车厢编号是所有轨道顶部的车厢编号中最小的
                if (judgeWhichTrackIn(inputOrder[i])) {
                    //此时有合适的位置插入
                    trackPush(inputOrder[i]);//此时因为执行了judgeWhichTrackIn，已经判断好了应该进入哪个轨道，这个轨道的编号就是类属性trackInIndex
                } else {
                    i--;
                    //此时没有合适的位置插入，判断有车厢的缓冲轨道中有没有可以直接弹出进入出轨道的车厢
                    while (true) {
                        try {
                            int trackOutIndex = judgeWhichTrackOut();
                            trackPop(trackOutIndex);
                        } catch (NoTrackOutException e) {
                            break;
                        }
                    }
                }
            } else {
                //全部轨道都是空的，直接进入默认规定的缓冲轨道
                bufferTrack.get(itsTrack).add(inputOrder[i]);
                System.out.println("将入轨道中编号为" + inputOrder[i] + "的车厢车厢移动到" + itsTrack + "号缓冲轨道");
                itsTrack++;
            }


        }

        //走到这一步说明入轨道中的所有车厢都处理完了，如果轨道中还有车厢则进入弹栈流程
        if (!allTrackIsEmpty()) {
            //仍然有轨道有车厢
            while (true) {
                try {
                    int trackOutIndex = judgeWhichTrackOut();
                    trackPop(trackOutIndex);
                } catch (NoTrackOutException e) {
                    break;
                }
            }
            return allTrackIsEmpty() && checkOutTrack();
        } else {
            return checkOutTrack();
        }
    }

    /**
     * 判断当前有车厢的缓冲轨道中有没有可以直接弹出移动到出轨道的车厢，如果有应该返回轨道的编号
     *
     * @return int 返回的适合弹出移动到出轨道的编号
     * @throws NoTrackOutException 抛出异常说明没有合适的轨道可以弹出直接移动到出轨道的车厢
     */
    private int judgeWhichTrackOut() throws NoTrackOutException {
        for (int i = 0; i < bufferTrack.size(); i++) {
            //检查每一个缓冲轨道顶部是否有合适的可以弹出的车厢
            if (!bufferTrack.get(i).empty() && bufferTrack.get(i).peek() == nextCarToOutTrack + 1) {
                return i;
            }
        }
        //所有轨道都没有合适的车厢可以弹出，则应该抛出异常
        throw new NoTrackOutException();
    }

    /**
     * 找出满足规则的缓冲轨道顶部的最小车厢编号
     *
     * @param theMatchTrack 匹配追踪
     * @return int
     */
    private int minTrack(HashMap<Integer, Integer> theMatchTrack) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(theMatchTrack.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return list.get(0).getKey();
    }

    /**
     * 判断当前处理的车厢应该进入哪个轨道，将应该进入轨道的编号保存在类属性trackInIndex中
     *
     * @param index 输入当前正在处理的车厢编号
     * @return 如果有合适的缓冲轨道可以让车厢进入则返回true，如果没有则返回false
     */
    private boolean judgeWhichTrackIn(int index) {

        //ArrayList<Integer> matchArray = new ArrayList<>();//将符合条件的轨道编号放入该数组
        HashMap<Integer, Integer> matchTrack = new HashMap<>();//第一个Integer是符合条件的轨道编号,第二个Integer是符合条件的缓冲轨道顶部的车厢的编号
        ArrayList<Integer> emptyTrackArray = new ArrayList<>();//如果轨道是空的，则将轨道的编号放入该数组
        for (int i = 0; i < bufferTrack.size(); i++) {
            //检查每一个缓冲轨道是否符合条件足以让当前处理的车厢进入缓冲轨道
            if (!bufferTrack.get(i).empty() && bufferTrack.get(i).peek() > index) {
                matchTrack.put(i, bufferTrack.get(i).peek());
                continue;
            }
            if (bufferTrack.get(i).empty())
                emptyTrackArray.add(i);
        }
        if (!matchTrack.isEmpty()) {
            //matchArray中保存的是轨道的编号，根据这些编号
            trackInIndex = minTrack(matchTrack);
            return true;

        }
        if (!emptyTrackArray.isEmpty()) {
            trackInIndex = emptyTrackArray.get(0);
            return true;
        }
        return false;
    }

    /**
     * 检测所有轨道是否为空
     *
     * @return boolean
     */
    private boolean allTrackIsEmpty() {
        for (int i = 0; i < bufferTrack.size(); i++) {
            if (!bufferTrack.get(i).isEmpty()) return false;
        }
        return true;
    }

    /**
     * 根据要弹出车厢的轨道编号弹出指定缓冲轨道顶部的车厢
     *
     * @param index 要弹出车厢的缓冲轨道编号
     */
    private void trackPop(int index) {
        if (bufferTrack.get(index).empty()) throw new EmptyStackException();
        System.out.println("将" + index + "号缓冲轨道顶部的编号为" + bufferTrack.get(index).peek() + "的车厢移动到出轨道");
        outTrack.offer(bufferTrack.get(index).pop());
        nextCarToOutTrack++;
    }

    /**
     * 将车厢压入缓冲轨道栈顶的具体操作，此时应该进入类属性trackInIndex所指示的轨道中
     *
     * @param index 当前处理的车厢编号
     */
    private void trackPush(int index) {
        System.out.println("将入轨道中编号为" + index + "的车厢移动到" + trackInIndex + "号缓冲轨道");
        bufferTrack.get(trackInIndex).push(index);

    }

    /**
     * 判断出轨道是否有序
     *
     * @return boolean 如果出轨道已经有序了则返回true，如果序则返回false
     */
    private boolean checkOutTrack() {
        while (!outTrack.isEmpty()) {
            Integer m1 = outTrack.poll();
            Integer m2 = outTrack.peek();
            try {
                if (m1.compareTo(Objects.requireNonNull(m2)) > 0) return false;
            } catch (NullPointerException e) {
                break;
            }
        }
        return true;
    }

    /**
     * 详细作用参阅下面的链接，它是函数抛出的一个异常以发挥一定的作用
     *
     * @see CarriageRearrangementSolver#judgeWhichTrackOut()
     */
    class NoTrackOutException extends RuntimeException {
        public NoTrackOutException() {
            super();
        }
    }

}
