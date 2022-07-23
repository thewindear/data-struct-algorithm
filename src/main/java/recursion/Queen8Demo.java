package recursion;

public class Queen8Demo {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array 保存皇后放置位置的结果 arr = {0,4,7,5,2,6,1,3}
    //用于保存每种解法的 摆放坐标，0 表示第1 1表示第2行以此类推
    int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queen8Demo q = new Queen8Demo();
        q.check(0);
        System.out.println(count);
    }

    //放置第n个皇后
    private void check(int n) {
        if (n == max) { //当放置第8个表示已全部放完
            print();
            return;
        }
        //今次放入皇后，并判断是否冲突
        for(int i=0;i<max;i++) {
            array[n] = i;
            //先把当前这个皇后n，。 放到该行的第1列
            //判断当前放置第n个皇后到i列时，是否冲突
            if (judge(n)) { //如果不冲突
                check(n+1); //再放n+1个皇后
            }
            //如果冲突，就继续执行array[n]=i
        }

    }

    //查看当前放置第n个皇后，就去检测该皇后是否和前面已摆放的皇后冲突
    private boolean judge(int n) {
        for(int i=0;i<n;i++) {
            //1、array[i] == array[n] 表示判断 第n个皇后是否和前面的n-1个皇后在同一列
            //2、Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后是否在同一斜线
            //  n=1放置第2列1 n=1 array[1] = 1
            //  Math.abs(1-0) == 1 Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
            //3、判断是否在同一行，没必要n每 次都在递增
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        count++;
        for (int j : array) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
