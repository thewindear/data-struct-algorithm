package sortalg;

import java.util.Arrays;

//堆排序
public class HeapSorting2Demo {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
//        int[] arr = {4, 6, 8, 5, 9, 10, 12, 11};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //
    public static void heapSort(int[] arr) {
        //这一步是将这个数组转换成一个大顶堆数组
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            System.out.println(i);
            adjustHeap(arr, i, arr.length);
        }
        //当上面循环完成后数组已经是一个大顶堆或小顶堆，那么还需要再进行一次交换
        //因为是一个大顶域小顶域，那么arr[0]应该是最大的值或最小的值
        //那么应该将他替换到最后
        int temp = 0;
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //因为这里再次调整的时候应该是越调整越少所以这里应该length参数为j而且是从0开始调整
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 将一个数组调整成一个大顶堆或小顶堆
     * 功能：完成将以i对应的非叶子节点调整成大
     * 每次i的位置等于 = arr.length/2-1  这个length应该是递减每调整一次应该就要-1
     * {4,6,8,5,9} i应该是从1开始0也就是4为根节点
     * int[] arr = {4,6,8,5,9} => i => 1 adjustHeap 得到 {4,9,8,5,6}
     * 如果再次调整
     * int[] arr = {4,9,8,5,6} => i => 0 adjustHeap 得到 {9,6,8,5,4}
     *
     * @param arr    要调整的数组
     * @param i      非叶子节点的在数组中的索引
     * @param length 对多少个元素继续调整（调整完一次就减少一次）
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前元素的值，保存临时变量
        int temp = arr[i];
        //开始调整
        //1、k = i * 2 + 1 k是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //左子节点小于右子节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++; //让k指向右子节点
            }
            //当前k应该是指向了子节点最大的值的下标，那么需要将
            //父节点的值和子节点的值进行交换
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k; //指向k继续循环比较 因为可能还存在左子树和右子树，所以需要将找到最大的下标的索引，
                //赋值给当前循环的i，继续向下遍历
            } else {
                //小于就break
                break;
            }
        }
        //当循环结束后，已经将以i为父节点的树的最大值放在了i这个下标上
        //这里只是局部，以i为节点的子树调整
        //再将temp放到调整后的位置
        arr[i] = temp;
    }

}
