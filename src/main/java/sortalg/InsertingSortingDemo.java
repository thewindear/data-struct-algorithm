package sortalg;

import java.util.Arrays;

public class InsertingSortingDemo {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1};
        insertionSorting(arr);
        //他的结果应该是{-1,6,32,43,2332,3245}
        System.out.println(Arrays.toString(arr));
    }

    //插入排序
    public static void insertionSorting(int[] arr) {

        for(int i=1;i<arr.length;i++) {
            //定义待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1; //即arr[1]的前面这个数的下标

            //给insertVal找要插入的位置
            //1、insertIndex >= 0 保证在给insertVal找插入位置，不越界
            //2、insertVal < arr[insertIndex] 待插入的数，还没有找到插入的位置
            //3、就需要将arr[insertIndex]后移
            while(insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //退出while循环时，说明插入的位置找到了，insertIndex + 1
            arr[insertIndex + 1] = insertVal;
        }
        System.out.println(Arrays.toString(arr));
    }

}
