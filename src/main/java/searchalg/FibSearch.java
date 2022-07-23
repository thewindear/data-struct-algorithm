package searchalg;

import java.util.Arrays;

public class FibSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fiboSearch(arr, 89));
    }

    //因为后面mid=low+F(k-1)-1需要使用到斐波那契数列，因此需要先获取一个斐波那契数列
    //非递归写法得到一个斐波那契
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 非递归
     *
     * @param a   数组
     * @param key 要查找的值
     * @return 存在就返回下标没有就返回-1
     */
    public static int fiboSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; //斐波那契分割数值下标
        int mid; //存入mid值
        int[] f = fib();
        System.out.println(Arrays.toString(f));
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        System.out.println(k);
        //因为f[k]值可能大于a的长度，因此需要使用Arrays类，构造一个新的数组，并指向a[]
        //不足的部分使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //需要使用a数组最后的数组填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        System.out.println(Arrays.toString(temp));

        while (low <= high) { //只要这个条件满足就开始查找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //说明应该继续向左边查找
                high = mid - 1;
                //为什么是k--
                //说明：
                //1、全部元素=前面元素+后面元素
                //2、f[k] = f[k-1] + f[k-2]
                //因为前面有f[k-1]个元素，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1]的前面继续查找
                //即下次f[k-1] 的前面继续查找k-- 即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                //为什么是k-=2
                //说明：
                //1、全部元素=前面元素+后面元素
                //2、f[k] = f[k-1] + f[k-2]
                //3、因为前面有f[k-1]个元素，所以可以继续拆分f[k-1] = f[k-3] + f[k-4]
                //4、即在f[k-2]的前面继续查找
                //5、即下次f[k-2]的后面继续查找k-=2 即下次循环 mid = f[k-1-2]-1
                k -= 2;
            } else {
                //找到了，需要返回的是哪个下标
                return Math.min(mid, high);
            }
        }
        //没有找到
        return -1;
    }

}
