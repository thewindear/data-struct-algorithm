package algorithm;

//二分查找非递归
public class BinarySearchRecursionDemo {
    public static void main(String[] args) {
        //
        int[] arr = {1, 4, 6, 98, 123, 3455, 7854};
        System.out.println(binarySearch(arr, 221));
    }

    /**
     * @param arr    待查找的数组
     * @param target 要查找的数
     * @return 返回下标如果没有找到返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        //从开头
        int left = 0;
        //到最后
        int right = arr.length - 1;
        //只要左指针小于右下标可以继续查找
        while (left <= right) {
            //先获取中间值
            int mid = (left + right) / 2;
            //当中间值等于要查找的直接返回下标
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                //如果中间值大于要查找的值，那么需要那左查找
                //下次循环应该是从 left 到 mid - 1
                right = mid - 1;
            } else {
                //如果中间值大于要查找的值，那么需要就向右查找
                //下次循环应该是从 mid + 1 到 right
                left = mid + 1;
            }
        }
        return -1;
    }

}
