package algorithm;

import java.util.Arrays;

public class PrimDemo {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertxs = data.length;
        //这个邻接矩阵 这里使用10000代表不联通
        int[][] weight = new int[][]{
                //A     B  C  D      E      F      G
                {10000, 5, 7, 10000, 10000, 10000, 2}, //A
                {5, 10000, 10000, 9, 10000, 10000, 3}, //B
                {7, 10000, 10000, 10000, 8, 10000, 10000}, //C
                {10000, 9, 10000, 10000, 10000, 4, 10000}, //D
                {10000, 10000, 8, 10000, 10000, 5, 4}, //E
                {10000, 10000, 10000, 4, 5, 10000, 6}, //F
                {2, 3, 10000, 10000, 4, 6, 10000}, //G
        };

        //创建MGraph对象
        MGraph mGraph = new MGraph(vertxs);
        //创建一个mini tree
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, vertxs, data, weight);
        //输出
        minTree.showGraph(mGraph);
        //v表示从哪个顶点开始
        minTree.prim(mGraph, 0);
    }
}

//创建最小生成树 -> 村庄的图
class MinTree {
    //创建图的邻接矩阵
    public void createGraph(MGraph graph, int vertxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < vertxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写prim 算法，得到最小生成树
     *
     * @param mGraph 图
     * @param v      表示从图的第几个顶点开始生成 'A' 对应为0
     */
    public void prim(MGraph mGraph, int v) {
        //作用，标记顶点是否被访问过，默认为0，表示没有访问过
        int[] visibled = new int[mGraph.vertx];
        visibled[v] = 1;
        //h1和h2记录两个顶点下标
        int h1 = -1;
        int h2 = -2;
        //将minWeight初始化成一个大数，后面遍历过程中，会被替换
        int minWeight = 10000;
        for (int k = 1; k < mGraph.vertx; k++) {
            //因为有graph.vertx个顶点，prim算法结束后，有graph.vertx-1条边
            for (int i = 0; i < mGraph.vertx; i++) {
                for (int j = 0; j < mGraph.vertx; j++) {
                    //循环遍历所有 矩阵列表 拿到每个顶点最小的那条边
                    if (visibled[i] == 1 && visibled[j] == 0 && mGraph.weight[i][j] < minWeight) {
                        //替换minWeight （寻找已访问过的节点和未访问的节点权值最小的边）
                        minWeight = mGraph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("第" + k + "条边:" + mGraph.data[h1] + "-" + mGraph.data[h2] + ",权值:" + minWeight);
            //将访问的坐标设置为1已访问
            visibled[h2] = 1;
            minWeight = 10000;
        }
    }

}

class MGraph {
    int vertx; //表示图的顶个数
    char[] data; //存放节点数据
    int[][] weight; //存放边  邻接矩阵

    public MGraph(int vertx) {
        this.vertx = vertx;
        this.data = new char[this.vertx];
        this.weight = new int[this.vertx][this.vertx];
    }
}