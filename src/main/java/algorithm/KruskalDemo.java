package algorithm;

import java.util.Arrays;

//克鲁斯卡尔算法
public class KruskalDemo {
    //边的个数
    private int edgeNum;
    //顶点数组
    private char[] vertexs;
    //邻接矩阵
    private int[][] matrix;
    //使用INF类型表示两个顶点不能联通
    private static final int INF = Integer.MAX_VALUE;


    public static void main(String[] args) {
        //顶点
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //0代表自己和自己连  比如下面的
        //i=0j=0 取值也为0 表示他是自己的   INF无法连通
        int[][] matrix = {
                //A  b   c    d    e   f   g
                {0, 12, INF, INF, INF, 16, 16}, //a
                {12, 0, 10, INF, INF, 7, INF},  //b
                {INF, 10, 0, 3, 5, 6, INF},     //c
                {INF, INF, 3, 0, 4, INF, INF},  //d
                {INF, INF, 5, 4, 0, 2, 8},      //e
                {16, 7, 6, INF, 2, 0, 9},       //f
                {14, INF, INF, INF, 8, 9, 0}    //g
        };

        KruskalDemo k = new KruskalDemo(vertexs, matrix);
        k.print();
        k.kruskal();
    }

    public void kruskal() {
        int index = 0; //表示最后结果数组的索引
        //用于保存"已有最小生成树" 中的每个顶点在最小生成树的终点
        int[] ends = new int[edgeNum];
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];
        //所有原始的边
        EData[] edges = getEdges();
        System.out.println("图的边的集合:" + Arrays.toString(edges) + " 共" + edges.length);
        sortEdges(edges);
        //遍历edges数组， 将边添加到最小生成树中，判断是否准备加入的边是否形成回路
        //就加入rets 否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第1个顶点（起点）
            int p1 = getPosition(edges[i].start);
            //获取到第i条边的第2个顶点
            int p2 = getPosition(edges[i].end);
            //获取到p1这个顶点的已有最小生成树中的终点
            int m = getEnd(ends, p1);  //当p1或下面的p2还没加说进ends时那他就等于自己
            //获取到p2这个顶点的已有最小生成树的终点
            int n = getEnd(ends, p2);
            //是否构成回路
            if (m != n) {
                //设置m在"已有最小生成树"中的终点<E,F>
                ends[m] = n; //设置m在已经有最小生成树的终点
                rets[index++] = edges[i]; //有一条边加入到rest数组中
            }
        }

        //打印最小生成树输出rets
        System.out.println(Arrays.toString(rets));

    }

    //构造器
    public KruskalDemo(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertexs.length;
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边，使用的是复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                //说明边是有效的
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为:");
        for (int i = 0; i < vertexs.length; i++) {
            System.out.printf("%10s\t", vertexs[i]);
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < vertexs.length; i++) {
            System.out.printf("%s\t", vertexs[i]);
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%10d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //因为克鲁斯卡尔算法，在做生成最小树之前先要将
    // 边进行从小到大进行排序
    // 对边进行排序处理

    /**
     * 对所有边进行排序
     *
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges) {
        if (edges.length == 0) {
            return;
        }
        boolean flag = false;
        EData tmp;
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

    /**
     * @param ch 顶点的值传入顶点的值返回下标
     * @return 如果找不到返回-1
     */
    private int getPosition(char ch) {
        return Arrays.binarySearch(this.vertexs, ch);
    }

    /**
     * 生成边数组放到EData[]数组中，后面
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        //会将所有的边放到这个数组中
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的终点，用于判断两个顶点的终点是否相同
     *
     * @param ends : 数组就是记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成的不是一步到位的数据
     * @param i    表示传入顶点对应的下标
     * @return 返回的就是下标为i这个顶点对应的终点下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

}

//它的对象实例表示一条边
class EData {
    char start;     //边的起点
    char end;       //边的另一个点
    int weight;     //权值

    public EData(char start, char end, int weight) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "EData [<" + this.start + ", " + this.end + "> =" + this.weight + "]";
    }
}
