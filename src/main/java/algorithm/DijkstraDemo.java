package algorithm;

import java.util.Arrays;

//迪杰斯特拉算法，求图的最小路径
public class DijkstraDemo {

    public static int INF = 65535;

    public static void main(String[] args) {
        //顶点
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        //                    a    b  c  d    e    f    g
        matrix[0] = new int[]{INF, 5, 7, INF, INF, INF, 2};     //a
        matrix[1] = new int[]{5, INF, INF, 9, INF, INF, 3};     //b
        matrix[2] = new int[]{7, INF, INF, INF, 8, INF, INF};   //c
        matrix[3] = new int[]{INF, 9, INF, INF, INF, 4, INF};   //d
        matrix[4] = new int[]{INF, INF, 8, INF, INF, 5, 4};     //e
        matrix[5] = new int[]{INF, INF, INF, 4, 5, INF, 6};     //f
        matrix[6] = new int[]{2, 3, INF, INF, 4, 6, INF};       //g

        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(6);
        graph.showDijkstra();

    }
}

//核心：访问邻接矩阵记录类
class VisitedVertex {
    public int[] alreadyArr; //记录各个顶点是否访问过 1表示访问过，0未访问，会动态更新
    public int[] preVisited; //每个下标对应的值为前一个顶点下标，会动态更新
    public int[] dis; //记录出发顶点到其他所有顶点的距离，如：G为出发点，就会记录G到其他顶点的距离，会动态更新，求的最短距离就会存放到这变量中

    /**
     * @param length 顶点个数
     * @param index  出发顶点
     */
    public VisitedVertex(int length, int index) {
        //这里的alreadyArr就是顶点的个数
        this.alreadyArr = new int[length];
        //出发顶点
        this.preVisited = new int[length];
        this.dis = new int[length];
        //初始化dis全部填充为65535
        Arrays.fill(this.dis, 65535);
        this.alreadyArr[index] = 1;
        //设置出发顶点的访问距离为0
        this.dis[index] = 0;
    }

    /**
     * 判断index顶点是否被访问过，如果访问过返回true
     *
     * @param index
     * @return
     */
    public boolean in(int index) {
        return this.alreadyArr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        this.dis[index] = len;
    }

    /**
     * 更新pre这个顶点的前驱为index顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        preVisited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     *
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点，比如这里的G，完成后，就是A作为新的访问顶点
     *
     * @return
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新index这个顶点被访问过
        alreadyArr[index] = 1;
        return index;
    }


    public void show() {
        System.out.println("=======================");
        for (int i : alreadyArr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : preVisited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dis) {
            System.out.print(i + " ");
        }
    }
}

class Graph {
    //保存顶点
    private char[] vertex;
    //邻接矩阵
    private int[][] matrix;

    private VisitedVertex vv;

    public char[] getVertex() {
        return vertex;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Graph(char[] vertex, int[][] matrix) {
        this.matrix = matrix;
        this.vertex = vertex;
    }

    public void showDijkstra() {
        vv.show();
    }

    /**
     * 迪杰斯特拉算法
     *
     * @param index 出发点下标
     */
    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        //更新index顶点到周围顶点的距离和前驱顶点
        update(index);
        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();//选择并返回新的访问顶点
            update(index); //
        }
    }

    //更新index下标顶点到周围的顶点的距离和周围顶点的前驱顶点
    private void update(int index) {
        int len = 0;
        //根据遍历邻接矩阵的matrix[index]行
        for (int j = 0; j < matrix[index].length; j++) {
            // 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
            len = vv.getDis(index) + matrix[index][j];
            //如果j顶点没有被访问过，并且len小于出发顶点到j顶点的距离就需要更新
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index);
                vv.updateDis(j, len);
            }
        }
    }

    public void showGraph() {
        System.out.printf("%5s\t", "");
        for (char c : vertex) {
            System.out.printf("%5s\t", c);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("%5s\t", vertex[i]);
            for (int d : matrix[i]) {
                System.out.printf("%5s\t", d);
            }
            System.out.println();
        }
    }
}
