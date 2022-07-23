package sortalg;

import java.util.Arrays;

public class ShellSortingDemo {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    //移位法，比交换法快 基本思想和交换法一样
    public static void shellSort2(int[] arr) {
        for (int step = arr.length / 2; step > 0; step /= 2) {
            //从第step个元素，逐个对其所在的组进行直接插入排序
            for(int i=step;i<arr.length;i++) {
                int j = i;
                int tmp = arr[j];
                if (arr[j] < arr[j-step]) {
                    while(j - step >=0 && tmp < arr[j-step]) {
                        arr[j] = arr[j-step];
                        j -= step;
                    }
                    //退出表示找到了插入的位置
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void shellSort(int[] arr) {
        //临时变交换数据时使用
        int tmp = 0;
        //默认步长当
        //int step = arr.length;
        for(int step=arr.length/2;step>0;step/=2) {
            for(int i=step;i<arr.length;i++) {
                for(int j=i-step;j>=0;j-=step) {
                    if (arr[j] > arr[j+step]) {
                        tmp = arr[j];
                        arr[j] = arr[j+step];
                        arr[j+step] = tmp;
                    }
                }
            }
        }
    }

    //使用推导法一步一步来实现希尔排序
    public static void shellSortTuidao(int[] arr) {
        //希尔排序 第1轮排序
        //因为第1轮排序，是将10个数据分成5组
        int tmp = 0;
        for(int i=5;i<arr.length;i++) {
            //遍历 各组中所有的元素（共5组，每组两个元素） 步长为5
            for(int j=i-5;j>=0;j-=5) {
                //如果当前元素大于加上步长后面的那个元素，说明交换
                if (arr[j] > arr[j+5]) {
                    tmp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = tmp;
                }
            }
        }
        //第二轮的步长应该是为2=5/2
        for(int i=2;i<arr.length;i++) {
            //遍历 各组中所有的元素（共5组，每组两个元素） 步长为5
            for(int j=i-2;j>=0;j-=2) {
                //如果当前元素大于加上步长后面的那个元素，说明交换
                if (arr[j] > arr[j+2]) {
                    tmp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = tmp;
                }
            }
        }

        //第三轮的步长应该是为1=2/2
        for(int i=1;i<arr.length;i++) {
            //遍历 各组中所有的元素（共5组，每组两个元素） 步长为5
            for(int j=i-1;j>=0;j-=1) {
                //如果当前元素大于加上步长后面的那个元素，说明交换
                if (arr[j] > arr[j+1]) {
                    tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }

}


