package sortalg;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

//选择排序
public class SelectionSortingDemo {
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

        selectSort(arr);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2str = simpleDateFormat2.format(date2);

        System.out.println("排序后: " + date2str);
    }

    public static void selectSort(Integer[] arr) {
        //逐步推导方式
        //原始数组：101，34，119，1
        //第1次：1,34,119,101
        //第2次：1,34,119,101
        //第3次：1,34,101,119

        //先设置一个最小值的索引
        int minIndex;
        //先设置一个索引的值
        int min;
        for(int i=0;i<arr.length;i++) {
            //先假定第一个数为最小的数
            minIndex = i;
            min = arr[i];
            for(int j=i+1;j<arr.length;j++) {
                //假定当前遍历的数是最小值，和后面的数组的下标值进行比较，如果比当前下标值大
                //记录下最小索引和最小值 等内部循环结束时交换他们
                if (min > arr[j]) {
                    //说明假定最小值不是最小和值的索引
                    min = arr[j];   //将最小的值放至min
                    minIndex = j;   //将最小的值的索引放置minIndex
                }
            }
            //如果当前最小索引不等于当前循环的下标值，那么表示找到了
            //比当前下标还小的值，那么需要交换他们
            if (minIndex != i) {
                //交换位置
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.println(Arrays.toString(arr));
        }

    }

}


