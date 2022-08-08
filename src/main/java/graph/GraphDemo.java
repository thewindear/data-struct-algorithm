package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GraphDemo {
    public static void main(String[] args) {
        //n个节点
        int n = 5;
        String[] vertexValue = {"A", "B", "C", "D", "E"};
        GraphDemo graph = new GraphDemo(n);
        //循环添加顶点
        for (String value : vertexValue) {
            graph.insertVertex(value);
        }
        //添加边
        //A和B是联的
        graph.insertEdge(0, 1, 1);
        //A和C是联的
        graph.insertEdge(0, 2, 1);
        //b和e b和d b和a b和c都是联的
        graph.insertEdge(1, 0, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        //c和b c和a 是联的
        graph.insertEdge(2, 0, 1);
        graph.insertEdge(2, 1, 1);
        //d和b
        graph.insertEdge(3, 1, 1);
        //e和b
        graph.insertEdge(4, 1, 1);

        graph.showGraph();

        System.out.println(graph.getNumOfVertex());
        System.out.println(graph.getNumOfEdges());


        System.out.println("----深度优先遍历----");
        graph.dfs();
        System.out.println("\n----深度优先遍历----");
        System.out.println("----广度优先遍历----");
        graph.bfs();
        System.out.println("\n----广度优先遍历----");

    }

    //每个顶点
    private ArrayList<String> vertexList;
    //存储图的邻接矩阵
    private int[][] edges;
    //边的数量
    private int numOfEdges;
    //定义一个数组 Boolean[] 记录某个节点是否被访问 大小和顶点一至
    private boolean[] isVisible;

    //要构建一个图要知道有多少个顶点
    public GraphDemo(int n) {
        //初始化邻接矩阵 有几个顶点矩阵就是n
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        isVisible = new boolean[5];
    }

    public void bfs() {
        boolean[] isVisible = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisible[i]) {
                bfs(isVisible, i);
            }
        }
    }

    //对一个节点进行广度优先遍历
    private void bfs(boolean[] isVisible, int i) {
        int u; //表示队列的头节点对应下标
        int w; //邻接节点的下标
        //队列，节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisible[i] = true;
        //加入队列
        queue.addLast(i);
        //队列非空
        while (!queue.isEmpty()) {
            //取出队列头节点下标
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                //是否访问过
                if (!isVisible[w]) {
                    //未访问过输出
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记访问过
                    isVisible[w] = true;
                    //入列
                    queue.addLast(w);
                }
                //如果访问过以u开始节点找w后面的下一个节点
                //体现出广度优先
                w = getNextNeighbor(u, w);
            }
        }
    }

    //深度优先遍历算法 对dfs进行重载，遍历所有的顶点，并进行dfs
    public void dfs() {
        //遍历所有的顶点
        boolean[] isVisible = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisible[i]) {
                dfs(isVisible, i);
            }
        }
    }

    /**
     * i 第一次就是0
     *
     * @param isVisible 用于记录哪些顶点被访问过
     * @param i         第一次就是0
     */
    public void dfs(boolean[] isVisible, int i) {
        //首先访问该节点，输出
        System.out.printf(getValueByIndex(i) + "->");
        //将访问的节点设置为已访问
        isVisible[i] = true;

        //获取当前节点的i的第一个邻接节点
        int w = getFirstNeighbor(i);
        //说明存在邻接节点
        while (w != -1) {
            //如果没有被访问过
            if (!isVisible[w]) {
                dfs(isVisible, w);
            } else {
                //如果被访问过 那么应该去查找邻接节点的下一个节点
                w = getNextNeighbor(i, w);
            }
        }
    }

    /**
     * 得到第一个邻接节点的下标
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            //表示>0表示联通的
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    //从上面getFirstNeighbor拿到了第一个邻接节点的下标后，
    //如第1个：v1=0 v2=1 那么再找他下一个邻接节点就是2
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数量
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i下标对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //获取v1至v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        System.out.print("\t" + Arrays.toString(vertexList.toArray()) + "\n");
        for (int row = 0; row < edges.length; row++) {
            System.out.println(vertexList.get(row) + "\t" + Arrays.toString(edges[row]));
        }
    }

    /**
     * 添加边
     *
     * @param v1     表示点的下标 第几个顶点  "A"-"E" "A"-"0" "B"-1
     * @param v2     表示第二顶点的下标
     * @param weight 表示关联
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

}
