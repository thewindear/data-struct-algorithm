package sortalg;

import java.util.Arrays;

//归并排序
public class MergeSortingDemo {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length]; //
        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并方法
     *
     * @param arr   需要排序的数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //左边有序序列的初始值
        int j = mid + 1; //右边有序序列的初始值
        int t = 0;   //指向tmp数组的当前索引

        //1、
        //先把左右两边有序的数据按照规则填充到tmp数组中
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边的有序序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素，填充到tmp数组
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //2
        //把剩余的数据的一边数据依次全部填充到temp
        //i<=mid 说明左边还有元素没有处理
        //那么应该将左边的所有元素全部填充到temp中
        while (i <= mid) {
            temp[t] = arr[i];
            i += 1;
            t += 1;
        }
        //如果是右边还有剩余那么将右边的剩余的复制到temp中
        while (j <= right) {
            temp[t] = arr[j];
            j += 1;
            t += 1;
        }

        //3
        //将temp数组的元素拷贝到arr
        //并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        //第一次合并：tempLeft = 0, right = 1
        //第二次合并：tempLeft = 2  right = 3
        //最后一次合并：tempLeft = 0, right = 7
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }
}
