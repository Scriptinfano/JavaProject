package other.carriage;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class CarriageRearrangementSolver {
    private final int[] inputOrder;//记录车厢初始排列顺序的数组，索引从入轨道的右端到左端依次递增，车厢的初始顺序:[1,numberOfTracks]
    private ArrayList<Stack<Integer>> bufferTrack;//缓冲轨道数组
    private int nextCarToOutTrack = 0;//出轨道最左端车厢的编号

    private int trackInIndex;//应该进入的轨道的编号
    private int itsTrack = 1;//当所有缓冲轨道为空时，此属性代表默认的车厢待入轨道的编号

    private ArrayBlockingQueue<Integer> outTrack;//代表出轨道的队列集合

    /**
     * 设定列车的车厢数以及缓冲轨道的数量
     *
     * @param theInputOrder 车厢的初始排列顺序所代表的数组
     */
    public CarriageRearrangementSolver(int[] theInputOrder) {
        //列车的缓冲轨道和一开始的列车数量有一定的关系，目前尚不得知，所以暂时先以9个车厢和3个缓冲轨道来计算，尚不能任意指定车厢的数量
        bufferTrack = new ArrayList<>(getNumOfTracks() + 1);//为什么要加一：因为要使得缓冲轨道的编号从1开始
        inputOrder = new int[theInputOrder.length + 1];//为什么要加一：因为车厢的初始顺序:[1,numberOfTracks]，下标从1开始比较方便
        System.arraycopy(theInputOrder, 0, inputOrder, 1, this.numberOfCars);
        outTrack = new ArrayBlockingQueue<>(theInputOrder.length);
    }

    public static void main(String[] args) {

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
    public void run() {
        for (int i = 1; i <= inputOrder.length; i++) {
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


                    trackPush(trackInIndex);//smallestCar此时因为执行了上一个函数，上一个函数已经判断好了应该进入哪个轨道，这个轨道的编号就是类属性smallestCar

                } else {
                    //此时没有合适的位置插入，判断有车厢的缓冲轨道中有没有可以直接弹出进入出轨道的车厢
                    while (true) {
                        int trackOutIndex = judgeWhichTrackOut();
                        trackPop(trackOutIndex);
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
        if (allTrackIsEmpty()) {
            //所有轨道都空的时候说明应该结束整个流程
            return;
        } else {
            trackPop();//弹栈流程
        }

    }

    /**
     * 判断当前有车厢的缓冲轨道中有没有可以直接弹出移动到出轨道的车厢，如果有应该返回轨道的编号
     *
     * @return int 返回的适合弹出移动到出轨道的编号
     */
    private int judgeWhichTrackOut() {


    }

    /**
     * 判断当前处理的车厢应该进入哪个轨道，将应该进入轨道的编号保存在类属性trackInIndex中
     *
     * @param index 输入当前正在处理的车厢编号
     * @return 如果有合适的缓冲轨道可以让车厢进入则返回true，如果没有则返回false
     */
    private boolean judgeWhichTrackIn(int index) {


    }

    /**
     * 检测所有轨道是否为空
     *
     * @return boolean
     */
    private boolean allTrackIsEmpty() {

    }

    /**
     * 根据要弹出车厢的轨道编号弹出指定缓冲轨道顶部的车厢
     *
     * @param index 要弹出车厢的缓冲轨道编号
     */
    private void trackPop(int index) {

    }


    /**
     * 如果入轨道中仍然有车厢，则执行该流程，
     *
     * @param index 指定要压入哪个轨道
     */
    private void trackPush(int index) {

    }


}
