package algorithm;

import java.util.Arrays;

public class DijkstraDemo2 {
    final static int INF = 65535;

    public static void main(String[] args) {
        // 所有顶点
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        //初始化邻接矩阵
        //                    a    b  c  d    e    f    g
        matrix[0] = new int[]{INF, 5, 7, INF, INF, INF, 2};     //a
        matrix[1] = new int[]{5, INF, INF, 9, INF, INF, 3};     //b
        matrix[2] = new int[]{7, INF, INF, INF, 8, INF, INF};   //c
        matrix[3] = new int[]{INF, 9, INF, INF, INF, 4, INF};   //d
        matrix[4] = new int[]{INF, INF, 8, INF, INF, 5, 4};     //e
        matrix[5] = new int[]{INF, INF, INF, 4, 5, INF, 6};     //f
        matrix[6] = new int[]{2, 3, INF, INF, 4, 6, INF};       //g
        //图
        DGraph graph = new DGraph(vertex, matrix);
        graph.showGraph();
        graph.dsj(6);
        graph.showDsj();
    }
}

//记录已经访问的顶点
class VisitedVertex2 {

    //用于保存已访问顶点的标记
    //表示以某个顶点为超始点是否走过一次广度优先
    //这里比如以g点起始点，那么第一次就是[0,0,0,0,0,1]
    int[] alreadyArr;

    //用于保存某个顶点的前驱顶点的下标
    //对应当前遍历的顶点，他相邻顶点的前驱顶点
    //比如，从g这个顶点开始遍历，因为是广度优先，他会先遍历所有和g可以直接联通的顶点
    //那么就是 a,b,e,f 这四个顶点可以得他们的前驱顶点也就是g, 所以第1次遍历得到
    //[6,6,0,0,6,6,0]这个数组  因为 a的前驱节点是g也就是6，b的前驱节点是g也就是6,...
    int[] preVisited;

    //用于保存当前处理顶点和其他顶点的距离 下标为0 表示'a' 以此类推
    //而dis以当前为启点的顶点到各顶点的距离 g能直接到a,b,e,f 所以
    //在第一次遍历得到了[2,3,65536,65535,4,6,0]
    int[] dis;

    /**
     * 构造器，
     *
     * @param length 顶点的个数 上面的alreadyArr和preVisited的长度都是根据顶点的个数来创建的还有dis也是
     * @param index  出发顶点下标，比如g顶点下标为：6
     */
    public VisitedVertex2(int length, int index) {
        //初始化已经访问顶点长度
        this.alreadyArr = new int[length];
        //记录前一个顶点下标数组长度
        this.preVisited = new int[length];
        this.dis = new int[length];
        //先将dis全部填充为65535，默认不可访问，然后依次遍历更新这个数
        Arrays.fill(dis, DijkstraDemo2.INF);
        //将自己的下标定义为0表示距离为0
        this.alreadyArr[index] = 1; //出发顶点已被访问过
        this.dis[index] = 0;
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

    /**
     * 判断index顶点是否被访问过
     *
     * @param index
     * @return
     */
    public boolean in(int index) {
        //alreadyArr是记录是否被访问过，如果下标被访问过那么他的值为1
        return alreadyArr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     *
     * @param index 下标
     * @param len   距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre这个顶点的前驱顶点为index
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
     * 继续选择并返回新的访问顶点，比如从g开始
     * 之后就是从a开始访问顶点，遍历所有顶点就可以得到 这一步是广度优先拿到下个开始遍历的顶点下标
     * <p>
     * 比如：上面是从g开始 alreadyArr 第一次遍历得到 [0,0,0,0,0,0,1] dis = [6,6,0,0,6,6,0]
     * 那么在这个方法运行后会得到 index会得到0 min会变成6 也就是a
     *
     * @return
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            /// i 不能被访问过，并且dis[i]小于65535
            //
            if (alreadyArr[i] == 0 && dis[i] < min) {
                //那么将最小的距离等于dis[i]
                min = dis[i];
                //index等于那个顶点
                index = i;
            }
        }
        //更新index顶点被访问过
        alreadyArr[index] = 1;
        return index;
    }

}

class DGraph {
    char[] vertex; //保存顶点数组
    int[][] matrix; //邻接矩阵

    VisitedVertex2 vv; //已访问顶点的集合

    public DGraph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showDsj() {
        vv.show();
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

    /**
     * 迪杰斯特拉算法
     *
     * @param index 出发点的顶点下标
     */
    public void dsj(int index) {
        vv = new VisitedVertex2(vertex.length, index);
        //更新index顶点到周围顶点的距离和前驱顶点
        update(index);
        //继续选择并返回新的访问顶点，比如从g开始
        // 之后就是从a开始访问顶点，遍历所有顶点就可以得到
        for (int j = 1; j < vertex.length; j++) {
            //一这步就是依次遍历
            index = vv.updateArr();
            update(index); //更新距离和前驱节点
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱
    private void update(int index) {
        int len = 0;
        //根据遍历邻接矩阵 matrix[index]这行
        for (int j = 0; j < matrix.length; j++) {
            //因为是广度优先，这里是获取index的距离 + 邻接矩阵的距离
            // len： 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
            //     出发顶点dis  +  index到j顶点的距离
            // 比如 出发顶点为g他到a这个顶点的距离就是 0 + 2 然后再是 g 到 b 也就是
            len = vv.getDis(index) + matrix[index][j];
            //如果j顶点没有被访问，并且len小于出发顶点到j顶点的距离就需要更新
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index); //如果条件成立那么j的前驱节点就是index
                vv.updateDis(j, len);   //并更新j顶点的长度
            }
        }
    }
}
