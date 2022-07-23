package sortalg;

import java.util.Arrays;

//基数排序法
public class RadixSortDemo {
    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,124,-123,-9};
        radixSort(arr);
        System.out.println("基数排序后:" + Arrays.toString(arr));

    }

    public static void radixSort(int[] arr) {
        //首先取这个数组中最大的数
        int max = arr[0];
        for (int i=1;i<arr.length;i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //获取这个最大数的位数 要排序的次数
        int maxLength = (max+"").length();
        //先定义一个大桶，然后可以放10个小桶的数组
        //1、这个二维数组包含10个1维数组也就是0-9的那10个桶
        //2、为了防止放入的数据溢出，则每个桶的大小为arr.length
        //3、基数排序是空间换时间算法
        int[][] buket = new int[10][arr.length];
        //第1轮（针对每个元素的个位数进行排序处理)

        //为了记录每个桶中，实际放入了多少个数据，定义一个一维数组来记录各个桶每次放入的数据个数
        //buketElementCounts[0] 记录的就是每次放入的个数
        int[] buketElementCounts = new int[10];
        int index = 0;
        for(int ii=1, n = 1;ii<=maxLength;ii++,n*=10) {
            //得到每次下面要除以的位数 或者使用另一个变量n=1 然后每个n的步长乘以10
            //int base = (int)Math.pow(10, ii);
            for (int item : arr) {
                // 这个数 / 10 % 10 可以得到十分位
                // 这个数 / 100 % 10 可以得到百分位的数
                // 以此类推
                int digest = item / n % 10;
                //放到对应桶中
                buket[digest][buketElementCounts[digest]] = item;
                //每个桶的计数
                buketElementCounts[digest] += 1;
            }
            //当前数组下标
            for(int l=0;l<buketElementCounts.length;l++) {
                //如果计数大于0表示该桶中存在数据
                if (buketElementCounts[l] > 0) {
                    for(int x=0;x<buketElementCounts[l];x++) {
                        //将暂存的数据重新放回数组中
                        arr[index] = buket[l][x];
                        //清理暂存数据
                        buket[l][x] = 0;
                        //数组下标++
                        index++;
                    }
                    //将桶中的值放回数组后需要将计数清零
                    buketElementCounts[l] = 0;
                }
            }
            //需要重置数组索引，因为下一轮排序时需要使用到
            index = 0;
        }
    }

    //基数排序推导方法一步一步推导而来
    public static void radixSortTuidao(int[] arr) {
        //先定义一个大桶，然后可以放10个小桶的数组
        //1、这个二维数组包含10个1维数组也就是0-9的那10个桶
        //2、为了防止放入的数据溢出，则每个桶的大小为arr.length
        //3、基数排序是空间换时间算法
        int[][] buket = new int[10][arr.length];
        //第1轮（针对每个元素的个位数进行排序处理)

        //为了记录每个桶中，实际放入了多少个数据，定义一个一维数组来记录各个桶每次放入的数据个数
        //buketElementCounts[0] 记录的就是每次放入的个数
        int[] buketElementCounts = new int[10];

        for(int j=0;j<arr.length;j++) {
            // 这个数 / 10 % 10 可以得到十分位
            // 这个数 / 100 % 10 可以得到百分位的数
            // 以此类推
            int digest = arr[j] % 10;
            //放到对应桶中
            buket[digest][buketElementCounts[digest]] = arr[j];
            //每个桶的计数
            buketElementCounts[digest] += 1;
        }
        //当前数组下标
        int index = 0;
        for(int l=0;l<buketElementCounts.length;l++) {
            //如果计数大于0表示该桶中存在数据
            if (buketElementCounts[l] > 0) {
                for(int x=0;x<buketElementCounts[l];x++) {
                    arr[index] = buket[l][x];
                    index++;
                }
                //将桶中的值放回数组后需要将计数清零
                buketElementCounts[l] = 0;
            }
        }
        index = 0;
        //第1次排序后得到542,53,3,14,124,748
        System.out.println("第一次排序后得到的数组:" + Arrays.toString(arr));


        for(int j=0;j<arr.length;j++) {
            // 这个数 / 10 % 10 可以得到十分位
            // 这个数 / 100 % 10 可以得到百分位的数
            // 以此类推
            int digest = arr[j] / 10 % 10;
            //放到对应桶中
            buket[digest][buketElementCounts[digest]] = arr[j];
            //每个桶的计数
            buketElementCounts[digest] += 1;
        }
        //当前数组下标
        //index = 0;
        for(int l=0;l<buketElementCounts.length;l++) {
            //如果计数大于0表示该桶中存在数据
            if (buketElementCounts[l] > 0) {
                for(int x=0;x<buketElementCounts[l];x++) {
                    arr[index] = buket[l][x];
                    index++;
                }
                //将桶中的值放回数组后需要将计数清零
                buketElementCounts[l] = 0;
            }
        }
        //第1次排序后得到542,53,3,14,124,748
        System.out.println("第二次排序后得到的数组:" + Arrays.toString(arr));

        for(int j=0;j<arr.length;j++) {
            // 这个数 / 10 % 10 可以得到十分位
            // 这个数 / 100 % 10 可以得到百分位的数
            // 以此类推
            int digest = arr[j] / 100 % 10;
            //放到对应桶中
            buket[digest][buketElementCounts[digest]] = arr[j];
            //每个桶的计数
            buketElementCounts[digest] += 1;
        }
        //当前数组下标
        index = 0;
        for(int l=0;l<buketElementCounts.length;l++) {
            //如果计数大于0表示该桶中存在数据
            if (buketElementCounts[l] > 0) {
                for(int x=0;x<buketElementCounts[l];x++) {
                    arr[index] = buket[l][x];
                    index++;
                }
                //将桶中的值放回数组后需要将计数清零
                buketElementCounts[l] = 0;
            }
        }
        //第1次排序后得到542,53,3,14,124,748
        System.out.println("第三次排序后得到的数组:" + Arrays.toString(arr));

    }

}
