package sortalg;

import java.util.Arrays;

public class QuickSortingDemo {
    public static void main(String[] args) {
        int[] arr = {-9,7,0,23,-567,70};
        //-9,-567,0,23
        quikSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    public static void quikSort(int[] arr, int left, int right) {
        //左递归下标开始位置
        int l = left;
        //右递归下标开始位置
        int r = right;
        //拿到中轴数
        int pivot = arr[(left + right) / 2];
        //作为交换时使用
        int tmp = 0;

        //循环的目的是让比pivot值小放到左边比pivot值大放到右边
        while(l < r) {
            //在pivot一直找找到大于等于pivot的值才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot一直找一个小于等于pivot的值才退出
            while(arr[r] > pivot) {
                r -= 1;
            }
            //如果l大于r 说明pivot的左右两边的值，已按照左边全部是
            //小于等于pivot,右国全部是大于等于pivot的值
            //相当于左边的下标和右边的下标重合了停止整个循环
            if ( l >= r ) {
                break;
            }
            //交换变量值
            tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
            //上面步骤做完可以拭到 比中间值小的索引下标和比中间值大的索引下标
            //然后交换他们下标的值，得到左边就是比pivot的值小,右边比pivot的值
            //大的数组

            //如果交换完成后，发现arr[l] == pivot 值相等 -- 前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完成 后发现arr[r] == pivot 值相等 ++ 后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //当l和r相交也就是两个值相等表示，他们重合了
        //那么就要让r-=1和l+=1 左递归要从 left到r-1 开始排序
        //而 右递归要从 right到l+1 开始排序
        //因为l==r表示到了中间位置
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左左递归 将左边的数整成有序
        if (left < r) {
            quikSort(arr, left, r);
        }

//        向右递归 右边的下标要大于l
        if (right > l) {
            quikSort(arr, l, right);
        }
    }

}


