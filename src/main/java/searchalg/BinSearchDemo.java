package searchalg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//二分查找必须要求有序数组
public class BinSearchDemo {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 18, 33, 33, 33, 33, 89, 100, 1234};
        int index = binrarySearch(arr, 0, arr.length - 1, 33);
        System.out.println(index);

        List<Integer> arrList = binrarySearch2(arr, 0, arr.length - 1, 33);
        System.out.println(Arrays.toString(arrList.toArray()));
    }

    /**
     * 二分查找
     *
     * @param arr       数组
     * @param left      左边的索引
     * @param right     右边的索引
     * @param findValue 要查找的值
     * @return int 如果找到了就返回下标，如果没找到就返回-1  4 6
     */
    public static int binrarySearch(int[] arr, int left, int right, int findValue) {
        //表示这两个下标一直在 left 右移 right左移还是没有找到
        //那当left > right时表示下标超过了right还没表示还是没有找到
        //那么就返回-1
        if (left > right) {
            return -1;
        }
        //先除2取中间值
        int mid = (left + right) / 2;
        //拿到不中间值去和要查找的值比对如果找到了直接返回
        int midValue = arr[mid];
        //如果不间值小于要找到的，（那么要向数组的后面继续找 这时
        //下一次查找的 left + 1开始到right结束
        if (midValue < findValue) {
            return binrarySearch(arr, mid + 1, right, findValue);
        } else if (midValue > findValue) {
            //如果要找的数小于中间件 表示要向左找
            //那么下一轮开始的位置就是
            return binrarySearch(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

    public static List<Integer> binrarySearch2(int[] arr, int left, int right, int findValue) {
        //表示这两个下标一直在 left 右移 right左移还是没有找到
        //那当left > right时表示下标超过了right还没表示还是没有找到
        //那么就返回-1
        if (left > right) {
            return new ArrayList<>();
        }
        //先除2取中间值
        int mid = (left + right) / 2;
        //拿到不中间值去和要查找的值比对如果找到了直接返回
        int midValue = arr[mid];
        //如果不间值小于要找到的，（那么要向数组的后面继续找 这时
        //下一次查找的 left + 1开始到right结束
        if (midValue < findValue) {
            return binrarySearch2(arr, left + 1, right, findValue);
        } else if (midValue > findValue) {
            //如果要找的数小于中间件 表示要向左找
            //那么下一轮开始的位置就是
            return binrarySearch2(arr, left, mid - 1, findValue);
        } else {
            //如果要查找如 {1,2,3,4,55,55,66} 所有 55 出现的下标就需要再进行遍历
            List<Integer> arrList = new ArrayList<>();
            int temp = mid - 1;
            //先向左找
            while (true) {
                if (temp < 0 || arr[temp] != findValue) {
                    break;
                }
                arrList.add(temp);
                temp--;
            }
            arrList.add(mid);
            temp = mid + 1;
            //再向右找
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findValue) {
                    break;
                }
                arrList.add(temp);
                temp++;
            }
            return arrList;
        }
    }

}
