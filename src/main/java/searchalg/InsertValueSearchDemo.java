package searchalg;

public class InsertValueSearchDemo {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for(int i=0;i<100;i++) {
            arr[i] = i+1;
        }
//        int[] arr = {12,34,123,443,654,7868,9873};
        int index = insertValueSearch(arr, 0, arr.length - 1, 443);
        System.out.println(index);

    }

    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        System.out.println("1");
        //如果小于第一个数和大于最后一个数也没必要查找
        if (left > right || findValue < arr[0] || findValue > arr[arr.length - 1]) {
            return -1;
        }
        //和普通二分查找的区别在于这里 普通二分查找 是 （left+right) / 2
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        //比中间值大向右查找
        if (findValue > midValue) {
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return insertValueSearch(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

}
