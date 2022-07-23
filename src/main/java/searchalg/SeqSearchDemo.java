package searchalg;

public class SeqSearchDemo {
    public static void main(String[] args) {
        int[] arr = {1,4,43,654,764,32,1};
        int index = seqSearch(arr, 764);
        if (index != -1) {
            System.out.println("找到了下标为:" + index);
        }

    }

    public static int seqSearch(int[] arr, int value) {
        for (int i=0;i<arr.length;i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
