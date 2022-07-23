package sortalg;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class BubbleSortingDemo {
    //冒泡排序算法
    public static void main(String[] args) {

        Integer[] arr = new Integer[80000];
        Random random = new Random();
        for(int i=0;i<80000;i++) {
            arr[i] = random.nextInt(80000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1str = simpleDateFormat.format(date1);
        System.out.println("排序前: " + date1str);

        arr = bobbleSorting(arr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2str = simpleDateFormat2.format(date2);

        System.out.println("排序后: " + date2str);
    }

    public static Integer[] bobbleSorting(Integer[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        //优化配置，当循环一次后发现
        boolean flag = false;
        int tmp; //临时变量用来保存需要交换的数据
        for(int i=1;i<arr.length;i++) {
            for(int j=0;j<arr.length-i;j++) {
                //当当前遍历的数和后一个数进行对比
                if (arr[j] > arr[j+1]) {
                    //如果大于后一个数就交换他们
                    tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    //判断量否发生过交换。如果交换过元素，那么将标记设置为true
                    flag = true;

                    //这样可以得到第一次排序后的结果 每次内部的一次排序会将 最大的一个数向后排放
                    //在默认情况下，某次循环后发现结果中，已经是有序的可以不用再继续循环
                    //那么就需要一个变量来保存是否有做过交换操作,如果没有表示数组已经是有序的直接退出循环即可
                }
            }
            //如果遍历完后发现没有发生过交换那么表示这个数组是有序的直接退出循环
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
        return arr;
    }
}
