package os_experiment.group_link;

import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public final static Scanner scanner = new Scanner(System.in);
    public final static int groupSize = 5;//一个组中最多能有几个物理块
    public final static int groupNum = 5;//总的分组数
    public final static Block[][] blocks = new Block[groupNum][groupSize];//实际存储盘块引用的二维数组
    public final static HashMap<Integer, Block> table = new HashMap<>();//盘块编号和实际盘块引用的映射
    /**
     * 存储已经分配出去的文件
     */
    public final static HashMap<String, MyFile> files = new HashMap<>();
    /**
     * 指令集
     */
    public final static String[] instructions = {
            "touch",//创建一个新文件，后跟文件名字
            "rm",//删除一个文件，后跟文件名字
            "cat",//查看一个文件，后跟文件名字
            "ls",//列出当前所有文件名
            "shutdown"//结束输入
    };

    /**
     * 将字符串拆分为块
     *
     * @param inputString 输入的字符串
     * @param blockNum    将输入的字符串等分为blockNum份
     * @return {@link List}<{@link String}>
     * @throws IllegalArgumentException 非法参数异常
     */
    public static List<String> splitStringIntoBlocks(String inputString, int blockNum) throws IllegalArgumentException {
        // 计算每份字符串的长度
        int lengthPerBlock = (int) Math.ceil((double) inputString.length() / blockNum);

        // 分割字符串
        List<String> result = new ArrayList<>();
        for (int i = 0; i < blockNum; i++) {
            int start = i * lengthPerBlock;
            int end = Math.min(start + lengthPerBlock, inputString.length());
            String block = inputString.substring(start, end);
            if (block.length() > Block.blockSize) {
                throw new IllegalArgumentException("将指定字符串等分为blockNum份之后，每一份的长度超出了blockSize");
            }
            result.add(block);
        }

        return result;
    }

    public static void main(String[] args) {
        Pattern linuxStyle = Pattern.compile("^[\\w-]+(\\s+[\\w-]+)*$");//定义linux命令的识别模式
        init();//初始化所有物理块
        while (true) {
            System.out.print("输入命令>>");
            String instruction = scanner.nextLine();
            if (!linuxStyle.matcher(instruction).matches()) {
                System.out.println("请输入正确格式的命令，重新输入");
                continue;
            }
            String[] instructArr = instruction.split(" ");
            String fileName = "";
            if (instructArr.length > 2) {
                System.out.println("请输入正确格式的命令，重新输入");
                continue;
            } else if (instructArr.length == 2) {
                fileName = instructArr[1];//指令作用到的文件名
            }
            String instructionName = instructArr[0];//指令名字

            //下面是识别指令并分类处理的代码
            if (instructionName.equals(instructions[0])) {
                //读取到了创建新文件的命令
                if (files.containsKey(fileName)) {
                    System.out.println("文件名重复，请重新命名");
                    continue;
                }
                MyFile file = new MyFile(fileName);
                System.out.println("请输入该文件的内容");
                String text = scanner.nextLine();
                try {
                    file.setText(text);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                files.put(fileName, file);//将创建的文件放入存储区
                System.out.println("文件创建成功");
            } else if (instructionName.equals(instructions[1])) {
                //读取到了删除文件的命令
                MyFile deleteFile = files.get(fileName);//获取要删除的文件的引用
                if (deleteFile == null) {
                    //用户要删除的文件找不到
                    System.out.println("你要删除的文件不存在");
                    continue;
                }
                try {
                    for (int i = 0; i < deleteFile.blocks.length; i++) {
                        recovery(deleteFile.blocks[i]);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException("回收文件的过程中发生了错误，检查代码");
                }
                files.remove(fileName);//从文件区移除要删除的文件
                System.out.println("文件删除成功");
            } else if (instructionName.equals(instructions[2])) {
                //读取到了查看文件的命令
                MyFile catFile = files.get(fileName);//获取要删除的文件的引用
                if (catFile == null) {
                    //用户要删除的文件找不到
                    System.out.println("你要查看的文件不存在");
                    continue;
                }
                StringBuilder theText = new StringBuilder();
                for (int i = 0; i < catFile.blocks.length; i++) {
                    theText.append(catFile.blocks[i].text);
                }
                System.out.println(theText);
            } else if (instructionName.equals(instructions[3])) {
                for (Map.Entry<String, MyFile> entry : files.entrySet()) {
                    System.out.println(entry.getValue().fileName);
                }
            } else if (instructionName.equals(instructions[4])) {
                System.out.println("结束输入，终止程序...");
                break;
            } else {
                System.out.println("请输入正确格式的命令，重新输入");
            }
        }

    }

    /**
     * 初始化所有盘块，用全局的二维盘块数组存储
     */
    public static void init() {
        //用blocks[0][0]代表加载进入内存的超级块
        //初始化二维块数组，在同一行的块属于同一组，每一行的第一个块存储下一组块的信息
        LinkedList<Integer> temp = new LinkedList<>();
        //下面的代码初始化二维块数组中各个盘块应有的编号
        int begin = 3;
        temp.add(1);
        temp.add(2);
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (j == 0) {
                    int popIndex = temp.poll();
                    blocks[i][j] = new Block(popIndex);
                    table.put(popIndex, blocks[i][j]);
                    blocks[i][j].n = groupSize;
                    continue;
                }
                blocks[i][j] = new Block(begin);
                table.put(begin, blocks[i][j]);
                begin++;
            }
            temp.add(begin);
            begin++;
        }
        //实现成组连接法
        for (int i = 0; i < blocks.length; i++) {
            if (i == blocks.length - 1) blocks[i][0].next = -1;//最后一组的第一个盘块的指针位不指向任何东西，此处用-1标识
            else
                blocks[i][0].next = blocks[i + 1][0].index;//每组的第一个盘块中的指针位指向下一组的记录块
            for (int j = 1; j < groupSize; j++) {
                blocks[i][0].indexes.add(blocks[i][j].index);//每组的第一个盘块的编号栈将本组其他盘块的编号压栈
            }
        }
    }

    /**
     * 返回分配的盘块
     *
     * @return {@link Block}
     */
    public static Block allocate() throws RuntimeException {
        Block popBlock;
        if (blocks[0][0].next == -1)//所有空闲块均已分配完毕
            throw new RuntimeException("没有空闲块可以分配");
        if (blocks[0][0].n > 1) {
            //可以直接弹栈并且将记录块中的剩余空闲块-1的情况
            int popIndex = blocks[0][0].indexes.pop();//弹出块编号
            blocks[0][0].n--;//剩余空闲块-1
            popBlock = table.get(popIndex);//取得实际要分配的盘块
        } else {
            //组中只剩下一个空闲盘块了，也就是记录组信息的盘块
            int popIndex = blocks[0][0].next;
            //将要分配的最后一块的内容复制到超级块中
            popBlock = table.get(popIndex);
            copyBlock(blocks[0][0], popBlock);
        }
        //将要分配的块设为文件块
        popBlock.setFileBlock();
        return popBlock;
    }

    /**
     * 回收某已经分配出去的物理盘块
     *
     * @param recoveryBlock 要回收的物理块
     */
    public static void recovery(Block recoveryBlock) throws IllegalArgumentException {
        if (recoveryBlock.text == null) {
            //如果回收的是尚未分配的块则会报错
            throw new IllegalArgumentException("你要回收的块尚未分配，请重新检查代码");
        }
        if (blocks[0][0].n < groupSize) {
            //如果超级块中的盘块栈还没有满，则将回收的块的编号压入栈中，并将空闲块数+1
            blocks[0][0].indexes.add(recoveryBlock.index);
            blocks[0][0].n++;
        } else {
            //如果超级块中的盘块栈已经满了，则将超级块的内容复制到当前要回收的块中，并让超级块的指针域指向该回收的块，并设定空闲块为1，确保超级块中的空闲盘块栈为空
            copyBlock(recoveryBlock, blocks[0][0]);
            blocks[0][0].n = 1;
            blocks[0][0].next = recoveryBlock.index;
            blocks[0][0].indexes.clear();
        }
        recoveryBlock.text = null;//移除该回收块的文本读写权
    }

    /**
     * 未分配的物理块之间的深拷贝，将b2的内容全部拷贝到b1中
     *
     * @param b1 拷贝目的地
     * @param b2 拷贝源
     */
    public static void copyBlock(Block b1, Block b2) {
        b1.next = b2.next;
        b1.n = b2.n;
        Stack<Integer> temp = new Stack<>();
        while (!b2.indexes.empty()) {
            temp.add(b2.indexes.pop());
        }
        while (!temp.isEmpty()) {
            b1.indexes.add(temp.pop());
        }
    }

    /**
     * 模拟物理块
     *
     * @author Mingxiang
     */
    public static class Block {
        /**
         * 该块被分配给文件使用时，该物理块的文本域所能存储的字符的最大值
         */
        public static final int blockSize = 5;

        /**
         * 当前块的编号
         */
        public int index;
        /**
         * 当前块链接的组中还剩多少空闲块
         */
        public int n;
        /**
         * 存储当前块链接的组中的块的编号
         */
        public Stack<Integer> indexes;
        /**
         * 下一组记录块的编号
         */
        public int next;
        /**
         * 若该字符串不是空的，仍然存储信息，则不能被回收，实际上起了标识该块是否被分配
         */
        public String text = null;

        /**
         * 给定块的编号初始化物理块，块的编号是固定的，在任何情况下不得改变
         *
         * @param index 块的编号
         */
        public Block(int index) {
            this.index = index;
            indexes = new Stack<>();
        }

        /**
         * 将当前块设为文件块，清空所有变量，容器则清空所有元素
         */
        public void setFileBlock() {
            n = 0;
            indexes.clear();
            next = 0;
            text = "";
        }

    }

    /**
     * 模拟文件
     *
     * @author Mingxiang
     */
    public static class MyFile {
        /**
         * 一个文件最少要占多少个物理块
         */
        public final static int fileSize = 3;
        /**
         * 文件所关联的指定数量的物理块
         */
        public final Block[] blocks = new Block[fileSize];
        /**
         * 文件名
         */
        public String fileName;//文件名字

        public MyFile(String fileName) {
            this.fileName = fileName;
        }

        /**
         * 用文本写入该文件
         *
         * @param text 用户输入的内容
         * @throws IllegalArgumentException 非法参数异常
         */
        public void setText(String text) throws IllegalArgumentException {
            try {
                for (int i = 0; i < blocks.length; i++) {
                    blocks[i] = allocate();
                }
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("所有空闲块均分配，无法再创建任何文件，请删除一部分文件之后再操作");
            }
            int length = text.length();
            int blockNum = (int) Math.ceil((double) length / Block.blockSize);//该文本需要几个物理块
            List<String> strs;
            try {
                strs = splitStringIntoBlocks(text, blockNum);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("请重新输入你的文本");
                return;
            }
            if (strs.size() > fileSize)
                throw new IllegalArgumentException("文件无法容纳该字符串");
            for (int i = 0; i < strs.size(); i++) {
                blocks[i].text = strs.get(i);
            }
        }
    }
}
