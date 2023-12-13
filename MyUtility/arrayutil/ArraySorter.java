package arrayutil;

import java.util.*;

/**
 * 排序工具类,包含对数组的一些排序方法，注意该排序器排序的对象必须实现{@link Comparable<T>}接口
 */
public class ArraySorter<T extends Comparable<T>> {
    /**
     * 内部要排列的数组会被复制到该容器中，便于操作维护
     */
    private final List<T> targetList;

    /**
     * 和传入该对象的数组持有同一引用，当targetList已经有序时，调用transform()接口将容器中已经有序的元素重新填回该数组，则达到了对外部数组进行排序的目的
     */
    private T[] theArr;

    /**
     * 构造ArraySorter对象，构造之后尚不能排序，调用resetArray接口设定待排序数组
     */
    public ArraySorter() {
        targetList = new ArrayList<>();
    }


    /**
     * 将容器中已经排好序的元素重新装回原数组
     */
    private void transform() {
        int bounds = targetList.size() - 1;
        for (int i = 0; i < bounds; i++) {
            theArr[i] = targetList.get(i + 1);
        }
    }

    /**
     * 重设要排序的数组，一般用法是仅声明一个ArraySorter对象，调用该方法重设排序数组即可对其他数组进行排序
     */
    public void setSortArray(T[] theArray) {
        theArr = theArray;
        if (!targetList.isEmpty())
            targetList.clear();
        targetList.add(null);//targetList中的元素从下标序号为1的单元开始存储
        targetList.addAll(Arrays.asList(theArray));
    }


    /**
     * 包装快速排序，使设定边界的时候不用再手动设定，关于快速排序的详细讲解，参阅下方链接
     *
     * @see ArraySorter#quickSortDetail(int, int)
     */
    public void quickSort() {
        if (theArr == null) {
            throw new RuntimeException("未设置待排序的数组，请调用resetArray设定待排序数组");
        }
        quickSortDetail(1, targetList.size() - 1);
    }

    /**
     * <strong>快速排序</strong><br/><br/>
     * 算法特点<br/>
     * 1、不稳定排序<br/>
     * 2、排序时需要定位表的上下界，所以适合于顺序结构<br/>
     * 3、适合初始记录无序，数组元素个数较大的情况<br/>
     * 4、快速排序的时间复杂度：最好情况O(nlogn) 最坏情况O(n^2) 平均情况O(nlogn)<br/>
     * 5、空间复杂度：由于快速排序是递归的，执行时需要占用函数栈，最大递归调用次数和递归树的深度一致，
     * 所以最好情况下的空间复杂度为O(logn)，因为递归树的深度在最好情况下是logn，最坏情况下：O(n)，
     * 在最坏情况下也就是在排序序列已经排好序的情况下，递归树成为单支树
     *
     * @param left  快速排序要指定的左边界，在调用此函数时请填写1
     * @param right 快速排序要指定的右边界，在调用此函数时请填写数组的元素个数
     */
    private void quickSortDetail(int left, int right) {

        /*如何选取轴值
         * 1、使用第一个记录的关键码
         * 2、选取序列中间记录的关键码
         * 3、比较序列中第一个记录，最后一个记录和中间记录的关键码，取关键码居中的作为轴值并调换到第一个记录的位置
         * 4、随机选取轴值
         * */
        if (left < right) {
            int i = left, j = right;
            T standard = targetList.get(i);
            while (i < j) {
                //如果每次都选取区间的左端作为枢轴值，则右侧指针先向左走然后左侧指针再朝右走
                while (targetList.get(j).compareTo(standard) >= 0 && i < j) {
                    j--;
                }
                if (i < j) {
                    swap(i, j);
                    i++;
                }
                while (targetList.get(i).compareTo(standard) <= 0 && i < j) {
                    i++;
                }
                if (i < j) {
                    swap(i, j);
                    j--;
                }
            }
            //此时i和j相等，i和j的值就是轴值的下标，轴值左侧都是小于轴值的值，轴值右侧都是大于轴值的值
            quickSortDetail(left, i - 1);
            quickSortDetail(i + 1, right);
        }
        transform();
    }

    private int split(int low, int high) {
        int k, i = low;
        T x = targetList.get(low);
        for (k = low + 1; k <= high; k++) {
            if (targetList.get(k).compareTo(x) <= 0) {
                i++;
                if (i != k) {
                    swap(i, k);
                }
            }
        }
        swap(low, i);
        return i;
    }


    /**
     * <strong>直接插入排序</strong><br/><br/>
     * 插入排序的思想:从数组的第二个元素开始遍历数组，比较该元素与前一个元素，如果该元素
     * 比前一个元素大，则继续遍历下一个元素，如果该元素比前一个元素小，则保存这个元素的值，
     * 将这个元素不断地向后挪，直到该元素比后一个元素小为止，挪的过程中采用将前一个元素覆
     * 写后一个元素的方式实现，不用担心后一个元素被抹掉，因为已经提前保存了这个值<p>
     * 如何改进插入排序：1、若待排序序列基本有序，则直接插入排序的效率可以提高 2、由于直接插入排序算法简单，则当n较小时效率也很高<p>
     * 算法特点：<p>
     * 1、稳定排序<p>
     * 2、适合链式存储结构<p>
     * 3、适合初始基本有序且数组元素个数较小的情况<p>
     * 4、时间复杂度：最好情况O(n) 最坏情况O(n^2) 平均情况O(n^2)
     */
    public void insertSort() {
        if (theArr == null) {
            throw new RuntimeException("未设置待排序的数组，请调用resetArray设定待排序数组");
        }
        for (int i = 1; i < targetList.size(); i++) {
            T value = targetList.get(i);
            int k;
            for (k = i - 1; k >= 1 && value.compareTo(targetList.get(k)) < 0; k--)
                targetList.set(k + 1, targetList.get(k));
            targetList.set(k + 1, value);
        }
        transform();
    }

    /**
     * <strong>希尔排序</strong><br/><br/>
     * 希尔排序是插入排序的一种，是针对直接插入排序算法的改进，又称缩小增量排序。<br/>
     * 算法思想：将整个待排序记录分割成若干个子序列，在子序列中分别进行直接插入排序，待整个序列中的记录
     * 基本有序时，对全体记录进行直接插入排序<br/>
     * 算法特点
     * <p>
     * 1、排序不稳定<p>
     * 2、只能用于顺序结构，希尔排序涉及随机访问，因为要不停的比较间隔已经距离的元素，所以肯定要随机访问，而链式存储对随机访问的支持比较差，所以仅适用于顺序存储<p>
     * 3、增量序列可以有各种取法，但应该使增量序列中的值没有除1之外的公因子，并且最后一个增量值必须等于1<p>
     * 4、适合初始记录无序，数组元素个数较大时的情况<p>
     * 5、时间复杂度：O(n^(1.3~2))<br/>
     * 时间复杂度分析：希尔排序开始时增量比较大，每个子序列中的记录个数较少，从而排序速度较快；当
     * 增量较小时，虽然每个子序列中记录个数较多，但整个序列已经基本有序，排序速度也比较快。希尔排序
     * 算法的时间性能还与增量函数有关系
     */
    public void shellSort() {
        if (theArr == null) {
            throw new RuntimeException("未设置待排序的数组，请调用resetArray设定待排序数组");
        }
        int size = targetList.size() - 1;
        //当d变为1的时候也就是对一个基本有序的序列进行直接插入排序，大大减少了移动比较的次数
        for (int d = size / 2; d >= 1; d = d / 2) {
            //d是希尔排序的增量
            for (int i = d; i <= size; i++) {
                T temp = targetList.get(i);
                int j = i - d;
                while (j > 0 && temp.compareTo(targetList.get(j)) < 0) {
                    targetList.set(j + d, targetList.get(j));
                    j = j - d;
                }
                targetList.set(j + d, temp);
            }
        }
        transform();
    }

    /**
     * <strong>冒泡排序</strong><br/><br/>
     * 算法特点<p>
     * 1、排序稳定<p>
     * 2、可用于链式存储结构<p>
     * 3、适用于初始记录有序，数组元素个数较小的情况<br/>
     * 4、时间复杂度：最好情况O(n) 最坏情况O(n^2) 平均情况O(n^2)
     */
    public void bubbleSort() {
        if (theArr == null) {
            throw new RuntimeException("未设置待排序的数组，请调用resetArray设定待排序数组");
        }
        for (int i = 1; i < targetList.size(); i++) {
            for (int j = 1; j < targetList.size() - i; j++) {
                if (targetList.get(j).compareTo(targetList.get(j + 1)) > 0) {
                    swap(j, j + 1);
                }
            }
        }
        transform();
    }

    /**
     * <strong>优化之后的冒泡排序</strong><br/><br/>
     * 优化冒泡排序中每一次遍历不断比较的过程，如果在一次遍历的过程中没有发生交换，
     * 则说明已经排好序了，不需要再进行下一轮的交换了
     */
    public void betterBubbleSort() {
        if (theArr == null) {
            throw new RuntimeException("未设置待排序的数组，请调用resetArray设定待排序数组");
        }
        boolean swapped = true;
        for (int i = 1; i < targetList.size() && swapped; i++) {
            swapped = false;
            for (int j = 1; j < targetList.size() - i; j++) {
                if (targetList.get(j).compareTo(targetList.get(j + 1)) > 0) {
                    swap(j, j + 1);
                    swapped = true;
                }
            }
        }
        transform();
    }

    /**
     * <strong>折半插入排序</strong><br/><br/>
     * 算法特点：<br/>
     * 1、排序稳定<br/>
     * 2、只能用于顺序结构(需要随机访问)<br/>
     * 3、适合初始记录无序，数组元素数较大的情况<br/>
     * 4、相对于直接插入排序，减少了元素的比较次数，每次的比较次数=Math.floor(log(2,i))但是相对于直接插入排序，元素的移动次数不变<br/>
     * 算法时间复杂度：最好情况O(nlogn) 最坏情况O(n^2) 平均情况O(n^2)
     */
    public void binaryInsertSort() {
        if (theArr == null) {
            throw new RuntimeException("未设置待排序的数组，请调用resetArray设定待排序数组");
        }

        //依次插入第2到第n个元素
        for (int i = 2; i < targetList.size(); i++) {
            T temp = targetList.get(i);
            int low = 1, high = i - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (temp.compareTo(targetList.get(mid)) < 0)
                    high = mid - 1;
                else low = mid + 1;
            }
            //上一个while循环结束之后，high+1就是插入位置
            //下面for循环的目的是把[high+1,i-1]的元素向后挪一位
            for (int j = i - 1; j >= high + 1; j--)
                targetList.set(j + 1, targetList.get(j));
            targetList.set(high + 1, temp);
        }
        transform();
    }

    /**
     * <strong>简单选择排序</strong><br/><br/>
     * 算法思想：将序列分为两部分，前半部分为有序序列，后半部分为无序序列，一开始都是无序序列
     * 不断地在无序序列中选出一个最小的元素然后无序序列最开头的元素进行交换，此时该元素变为
     * 有序序列的一部分，然后继续这种交换过程<br/>
     * 算法特点<p>
     * 1、不稳定排序<p>
     * 2、可用于链式存储结构<p>
     * 3、适用于移动记录次数较少，每一记录占用空间较多时的情况<p>
     * 4、时间复杂度：最好情况O(n^2) 最坏情况O(n^2) 平均情况O(n^2)
     */
    public void simpleSelectSort() {
        if (theArr == null) {
            throw new RuntimeException("未设置待排序的数组，请调用resetArray设定待排序数组");
        }
        for (int i = 1; i < targetList.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < targetList.size(); j++)
                if (targetList.get(j).compareTo(targetList.get(min)) < 0)
                    min = j;
            if (min != i)
                swap(i, min);
        }
        transform();
    }

    /**
     * <strong>优化之后的简单选择排序</strong><br/><br/>
     * 优化之后的选择排序，可以在已经排好序的前提下提前终止循环
     */
    public void betterSelectSort() {
        if (theArr == null) {
            throw new RuntimeException("未设置待排序的数组，请调用resetArray设定待排序数组");
        }
        boolean sorted = false;
        for (int i = targetList.size() - 1; !sorted && (i > 1); i--) {
            int indexOfMax = 1;
            sorted = true;
            //找出未排列区间谁的值最大，就将该值和该区间最后一个元素（i指向的元素之前的一个元素）交换，然后不断缩小区间，不断交换直到排好序
            for (int j = 2; j < i; j++) {
                if (targetList.get(indexOfMax).compareTo(targetList.get(j)) <= 0) indexOfMax = j;
                else sorted = false;
            }
            swap(indexOfMax, i - 1);
        }
        transform();
    }


    /**
     * <strong>堆排序</strong><br/><br/>
     * 算法特点<p>
     * 1、不稳定排序<p>
     * 2、只能用于顺序结构，由于堆排序是直接将顺序数组作为大根堆的顺序存储序列，利用大根堆中父节点与子节点编号之间的数学关系作为随机访问的依据，所以堆排序不适合链式存储<p>
     * 3、适合初始无序且数据元素较多的情况使用
     * 4、时间复杂度：最好情况O(nlogn) 最坏情况O(nlogn) 平均情况O(nlogn)
     */
    public void heapSort() {
        /*
         * 如果大根堆的根的编号从1开始，则满足以下性质
         * 1、下标为i的节点的父节点下标：Math.floor(i/2) 或 (i-1)/2
         * 2、下标为i的节点的左孩子下标：2*i
         * 3、下标为i的节点的右孩子下标：2*i+1
         * 注意本堆排序所采用的堆的根节点的编号从1开始算，数组的下标也从1开始算，数组的0号位是空引用null
         * */
        if (theArr == null) {
            throw new RuntimeException("未设置待排序的数组，请调用resetArray设定待排序数组");
        }
        //此时将初始的未排序序列视为完全二叉树的层序遍历的结果，就可以将初始序列视为一个完全二叉树，此时对这个完全二叉树进行堆调整，使其符合大根堆的定义

        //k的初始值是堆最后一个节点的父节点的编号
        //k = (targetList.size() - 1) / 2 相当于将K定位到了最后一个非叶子节点上，随后k不断变小遍历每一个非叶子节点
        for (int k = (targetList.size() - 1) / 2; k >= 1; k--)
            biggerHeapAdjust(k, targetList.size() - 1);//创建初始大根堆的循环

        // 注意创建初始大根堆的外层循环和交换堆顶并调整大根堆的外层循环不一样，创建初始大根堆的外层循环是自下向上自右向左地将非叶节点作为待调整子树根节点，相当于自下向上建堆。
        //交换堆顶并调整大根堆在每一次交换之后的调整是由根节点向下调整，注意不能和创建初始大根堆从下向上的顺序弄反，
        for (int k = 0; k < targetList.size() - 2; k++) {
            swap(1, targetList.size() - k - 1);
            biggerHeapAdjust(1, targetList.size() - k - 2);
        }
        transform();
    }

    /**
     * 大根堆调整函数，将整个无序数组视为完全二叉树后，对其进行调整，使其符合大根堆的定义
     * 大根堆是每个节点的值都大于或等于其左右孩子节点的值的完全二叉树，小根堆相反
     *
     * @param begin 待调整的子树的根节点在整个堆中按照层序遍历的编号
     * @param end   整个堆最后一个节点的层序遍历编号
     */
    private void biggerHeapAdjust(int begin, int end) {
        int i = begin;
        int j = 2 * i;//j所指节点是i所指节点的左子节点

        //此时i是待调整子树根节点的编号，j是待调整子树根节点的左子节点的编号

        while (j <= end) {

            //j<end实际是在判断i所指节点有没有右子树，如果有右子树，则必然满足此条件，则说明有右子树
            if (j < end && targetList.get(j).compareTo(targetList.get(j + 1)) < 0)
                j++;//当i所指节点有右子树且其左子节点比右子节点小，则将j指向较大的孩子节点，也就是i所指节点的右子节点

            //根节点与子节点中较大节点的值进行比较然后让较大值成为新的根节点
            if (targetList.get(i).compareTo(targetList.get(j)) < 0) {
                swap(i, j);
            }
            //以上两个if语句及操作保证了根节点和左右子节点三个节点中最大的节点成为新的根节点以满足大根堆的定义
            //更新i,j的值在下一个循环中检查子树是否符合大根堆的定义并调整
            i = j;
            j = 2 * i;
        }
    }


    /**
     * <strong>归并排序</strong><br/><br/>
     * 算法特点<br/>
     * 1、稳定排序（如果相同的数字a1和a2分别在要合并的两段的开头，此时比较时a1<=a2，此时会先将a1放在临时容器中，相当于不改变
     * 相同数字之间的相对位置，而如果相同的数字在同一段中则一定不会改变相对位置）<br/>
     * 2、可用于链式结构<br/>
     * 3、适合于数据量大且初始无序的情况<br/>
     * 4、时间复杂度：最好情况O(nlogn) 最坏情况O(nlogn) 平均情况O(nlogn)<br/><br/>
     * 时间复杂度分析：序列的每次分解都是一分为二，所以代价可忽略不计。归并算法中比较耗时的是归并操作，也就是把两个子数组合并为大数组。
     * 每一层归并操作消耗的时间总和是一样的，跟要排序的数据规模有关。我们把每一层归并操作消耗的时间记作n，
     * 归并排序递归树是一颗满二叉树，满二叉树的高度是logn，归并排序二叉树的高度就是归并的次数，用归并的次数乘以每一次归并的时间，就是总的时间，
     * 所以，归并排序递归实现的时间复杂度就是 O(nlogn)。<br/>
     * 5、空间复杂度：归并排序的空间复杂度是复杂排序中最大的，因为每一次实现归并的时候，需要一个大小为要归并的两段长度之和的辅助数组或容器，不停地
     * 比较放入容器，所以空间复杂度取决于排序序列地长度，所以空间复杂度是O(n)
     */
    public void mergeSort() {
        if (theArr == null) {
            throw new RuntimeException("未设置待排序的数组，请调用resetArray设定待排序数组");
        }
        mergeSortPri(1, targetList.size());
        transform();
    }

    /**
     * 归并排序的递归函数
     *
     * @param begin 归并排序分割的左边界
     * @param end   归并排序分割的右边界
     */
    private void mergeSortPri(int begin, int end) {
        if (end - begin <= 1) return;
        int mid = (begin + end) / 2;
        mergeSortPri(begin, mid);
        mergeSortPri(mid, end);
        merge(begin, mid, end);
    }


    /**
     * 归并排序中归并的过程，归并的过程是需要额外空间的，归并排序需要和待排序记录个数相等的辅助存储空间，所以空间复杂度是所有排序算法中最高的，为O(n)。
     *
     * @param begin 归并第一段的起始编号
     * @param mid   归并第一段的终止编号
     * @param end   归并第二段的终止编号
     */
    private void merge(int begin, int mid, int end) {
        int i = begin, j = mid;
        ArrayList<T> tempList = new ArrayList<>();
        while (i < mid && j < end) {
            if (targetList.get(i).compareTo(targetList.get(j)) <= 0) {
                tempList.add(targetList.get(i++));
            } else {
                tempList.add(targetList.get(j++));
            }
        }
        //如果此时i或j其中一个没有到第一段末尾或第二段末尾，则下面两个while循环则将剩下的元素放入tempList这个已经有序的容器中
        while (i < mid) {
            tempList.add(targetList.get(i++));
        }
        while (j < end) {
            tempList.add(targetList.get(j++));
        }
        //将已经有序的序列重新写回原容器，达成归并的目的
        for (int k = 0; k < tempList.size(); k++) {
            targetList.set(begin + k, tempList.get(k));
        }

    }

    /**
     * <strong>基数排序</strong><br/><br/>
     * 算法特点<br/>
     * 1、基数排序是对桶排序的扩展，速度快<br/>
     * 2、基数排序是经典的空间换时间的策略，占用内存大，对大量数据排序时可能出现OutOfMemoryError<br/>
     * 3、基数排序是稳定排序<br>
     * 4、适用于链式结构 <a href="https://blog.csdn.net/qq_44739053/article/details/90048158">对链式基数排序的详解</a>
     * 4、基数排序不能对含有负数、浮点数的数组进行排序<br/><br/>
     * 时间复杂度分析：<br/>
     * 假设有n个记录（要排序的对象），每个记录有d个关键字(假如1214为一个记录，那么组成该数字的每一位上的数字就是该记录的关键字)，
     * 每个关键字的取值范围是x个值（1作为记录1214的关键字，可以有10种取值也就是0~9），进行链式基数排序时，每一趟分配的时间复杂度
     * 为O(n)，因为要遍历所有的n个记录；每一趟收集过程的时间复杂度是O(x)，因为关键字的取值范围决定了桶的数量，桶的数量决定了收集的
     * 时间复杂度；整个排序要进行d次分配和收集，因为记录有d个关键字，要针对每个关键字进行一次分配和收集；所以最后总的时间复杂度
     * 就是O(d(n+x))<br/>
     * 综上所述时间复杂度为：最好情况O(d(n+x)) 最坏情况O(d(n+x)) 平均情况O(d(n+x))
     */
    public void radixSort() {
        if (theArr == null) {
            throw new RuntimeException("未设置待排序的数组，请调用resetArray设定待排序数组");
        }
        //创建十个桶
        ArrayList<ArrayList<T>> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            lists.add(new ArrayList<>());
        //找出所有数字中最大的数，然后以该数的位数作为基数排序的轮数
        ArrayList<T> newTargetList = new ArrayList<>();
        for (int i = 1; i < targetList.size(); i++) {
            newTargetList.add(targetList.get(i));
        }
        Optional<T> maxOptional = newTargetList.stream().max(Comparable::compareTo);
        if (maxOptional.isPresent()) {
            T maxElement = maxOptional.get();
            int maxDigit = String.valueOf(maxElement).toCharArray().length;

            //maxDigit是所有数字中最大的位数，是基数排序的轮数
            for (int k = 0; k < maxDigit; k++) {
                for (T t : newTargetList) {

                    char[] chars = String.valueOf(t).toCharArray();
                    Character[] characters = new Character[chars.length];
                    for (int i = 0; i < chars.length; i++) {
                        characters[i] = chars[i];
                    }
                    Collections.reverse(Arrays.asList(characters));
                    int result;
                    try {
                        result = Integer.parseInt(String.valueOf(characters[k]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        result = 0;
                    }

                    lists.get(result).add(t);//放入到对应的桶中
                }
                newTargetList.clear();
                for (ArrayList<T> list : lists) {
                    if (!list.isEmpty()) {
                        newTargetList.addAll(list);
                        list.clear();
                    }
                }

            }
            targetList.clear();
            targetList.add(null);
            targetList.addAll(newTargetList);
            transform();
        } else {
            throw new RuntimeException("待排序的容器为空");
        }

    }


    /**
     * 在排序的诸多算法中涉及交换数组中的两个元素，故有此交换函数<p>
     *
     * @param i 要交换的第一个元素的下标
     * @param j 要交换的第二个元素的下标
     */
    private void swap(int i, int j) {
        T temp = targetList.get(i);
        targetList.set(i, targetList.get(j));
        targetList.set(j, temp);
    }

}


class ArraySorterTester {
    private ArraySorterTester() {
    }

    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.randomIntegerArrayWithoutDuplicate(7, 1, 100);
        //Integer[] arr ={46,79,56,38,40,84};
        System.out.println(Arrays.toString(arr));
        ArraySorter<Integer> sorter = new ArraySorter<>();
        sorter.setSortArray(arr);
        sorter.heapSort();
        System.out.println(Arrays.toString(arr));
    }

}