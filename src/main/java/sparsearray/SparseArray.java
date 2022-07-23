package sparsearray;

import java.util.Arrays;

public class SparseArray {
    public static void main(String[] args) {
        //0没有子，1表示黑子 2表示白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        for(int[] row: chessArr1) {
            for (int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组转为稀疏数组
        //1、遍历二维数组，得到非0数据的个数
        int sum = 0;
        for(int i=0;i<11;i++) {
            for(int j=0;j<11;j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2、创建对应稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //几行
        sparseArr[0][0] = 11;
        //几列
        sparseArr[0][1] = 11;
        //有几个有效值
        sparseArr[0][2] = sum;

        //遍历二维数组将非0保存至数组中
        int x = 1;
        for(int i=0;i<11;i++) {
            for(int j=0;j<11;j++) {
                if (chessArr1[i][j] != 0) {
                    sparseArr[x][0] = i;
                    sparseArr[x][1] = j;
                    sparseArr[x][2] = chessArr1[i][j];
                    x++;
                }
            }
        }


        System.out.println();

        for(int i=0;i<sparseArr.length;i++) {
            System.out.printf("%d\t%d\t%d\t", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
            System.out.println();
        }
        System.out.println("稀疏数组转二维数组:");
        //使用稀疏数组恢复回二维数组
        //int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i=0;i<sparseArr[0][0];i++) {
            for(int j=0;j<sparseArr[0][1];j++) {
                var xxx = 0;
                for(int xx=1;xx<=sparseArr[0][2];xx++) {
                    if (i == sparseArr[xx][0] && j == sparseArr[xx][1]) {
                        xxx = sparseArr[xx][2];
                    }
                }
                System.out.printf("%d\t",xxx);

            }
            System.out.println();
        }


    }
}
