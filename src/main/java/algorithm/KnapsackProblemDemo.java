package algorithm;


//动态规划算法，求解背包问题
public class KnapsackProblemDemo {
    public static void main(String[] args) {
        //用于保存物品重量
        int[] w = {1, 4, 3};
        //物品的价值
        int[] val = {1500, 3000, 2000};
        //背包的容量
        int m = 4;
        //物品的个数
        int n = w.length;

        //记录放入商品的情况定一个二维数组来保存商品价值和容量
        int[][] path = new int[n + 1][m + 1];

        //创建二维数组表
        //int[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        for (int i = 0; i < v.length; i++) {
            //把第一列设置为
            v[i][0] = 0;

        }

        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; //将第1行设置为0
        }

        //动态规划处理 要从1开始
        for (int i = 1; i < v.length; i++) { //不处理第1行
            for (int j = 1; j < v[0].length; j++) { //不处理第1列
                //套用公式
                if (w[i - 1] > j) { //因为上面定义了w只有3个元素应该要将i-1
                    v[i][j] = v[i - 1][j];
                } else {
                    //为了记录商品存放背包的情况，所以要使用if else 来判断
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }


        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //输出最后放入哪些商品
        int i = path.length - 1; //行最大下标
        int j = path[0].length - 1; //列最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}


