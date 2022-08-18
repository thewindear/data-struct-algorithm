package algorithm;

import java.util.Arrays;

public class FloydDemo {
    final static int INF = 65535;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        //                    a    b  c  d    e    f    g
        matrix[0] = new int[]{0, 5, 7, INF, INF, INF, 2};     //a
        matrix[1] = new int[]{5, 0, INF, 9, INF, INF, 3};     //b
        matrix[2] = new int[]{7, INF, 0, INF, 8, INF, INF};   //c
        matrix[3] = new int[]{INF, 9, INF, 0, INF, 4, INF};   //d
        matrix[4] = new int[]{INF, INF, 8, INF, 0, 5, 4};     //e
        matrix[5] = new int[]{INF, INF, INF, 4, 5, 0, 6};     //f
        matrix[6] = new int[]{2, 3, INF, INF, 4, 6, 0};       //g

        //创建一个图对象
        FloydGraph graph = new FloydGraph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();
    }
}

//创建图
class FloydGraph {
    char[] vertex; //顶点
    // 保存从各个顶点出发到其他顶点的距离。最后结果也是在这个数组中
    int[][] dis;
    // 保存到达目标顶点的前驱顶点
    int[][] pres;

    public FloydGraph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        dis = matrix;
        //初始化保存达到目标顶点的前驱顶点数组
        pres = new int[matrix.length][matrix.length];
        for (int i = 0; i < length; i++) {
            //默认每个前驱为当前这个顶点的下标
            Arrays.fill(pres[i], i);
        }
    }

    /**
     * 弗洛伊德算法
     * 广度优先算法来完成的这个弗洛伊德算法，每次遍历各个顶点，然后再将各个顶点和其他顶点相联
     * 得到最短路径 这里是7个顶点因为遍历每个顶点所以是 最外层是遍历7次，然后每个顶点还要继续向
     * 他各个顶点进行遍历所以又是7次，最内部层是一个各个顶点到达的终点的距离然后进行判断取最小的
     * 那个距离，所以又是7次所以他是时间复杂度为O(n3) 立方阶
     */
    public void floyd() {
        int len = 0; //变量保存距离
        //对中间顶点的遍历，k就是中间顶点的下标 [A,B,C,D,E,F,G]
        for (int k = 0; k < dis.length; k++) {
            //从i顶点开始出发[A,B,C,D,E,F,G]
            for (int i = 0; i < dis.length; i++) {
                for (int j = 0; j < dis.length; j++) {
                    //从ik出发到达kj的距离就是 ij
                    len = dis[i][k] + dis[k][j];
                    //如果ij的距离小于len那么就将ij距离换成len
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        //那么[i][j]的前驱节点就是[k][j]
                        pres[i][j] = pres[k][j];
                    }
                }
            }
        }
    }

    //显示dis和dis数组
    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int k = 0; k < dis.length; k++) {
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pres[k][i]] + " ");
            }
            System.out.println();
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertex[k] + "-" + vertex[i] + ":" + dis[k][i] + ") ");
            }
            System.out.println();
        }
    }
}