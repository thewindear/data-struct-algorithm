package sortalg;

import java.util.Arrays;

//堆排序
public class HeapSortingDemo {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    //
    public static void heapSort(int[] arr) {
        int temp = 0;

        //分步完成
//        adjustHeap(arr, 1, arr.length);
//        System.out.println(Arrays.toString(arr));
//
//        adjustHeap(arr, 0, arr.length);
//        System.out.println(Arrays.toString(arr));
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //System.out.println(Arrays.toString(arr));
        //
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println(Arrays.toString(arr));

    }

    //将一个数组调整成大顶堆

    /**
     * 完成将i对应的非叶子节点调整成大顶堆
     * 如 int[] arr = {4,6,8,5,9} => i = 1 => adjustHeap => {4,9,8,5,6}
     * 如 int[] arr = {4,9,8,5,6} => i = 0 => adjustHeap => {9,6,8,5,4}
     *
     * @param arr    要调整的数组
     * @param i      表示非叶子节点的在数组中的索引
     * @param length 表示多少个元素进行排序，在排序的过程中长度会一直减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; //保存临时变量
        //这里的k为非叶子节点的左子节点
        //循环就可以得到当前k的他的左子节点
        //k已经是左子节点的下标
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //表示左子节点比右子节点小
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++; //k指向右子节点
            }
            //左子节点大于父节点
            if (arr[k] > temp) {
                //把较大的值赋值给当前节点
                arr[i] = arr[k];
                //i指向k继续循环比较
                i = k;
            } else {
                break;
            }
        }
        //当for循环结束后，已经将以i为父节点的树的最大值，放在了最顶（局部）
        arr[i] = temp;
    }

}
