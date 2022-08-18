package algorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//骑士周游问题
public class HorseDemo {

    private static int X = 8; //棋盘的列
    private static int Y = 8; //棋盘的行
    //创建一个数组，标记棋盘的各个位置是否被访问过 这个应该是 X*Y的长度
    private static boolean[] visited;
    //使用一个属性，标记是否棋盘的所有位置都被访问过
    private static boolean finished; //如果为true表示成功

    public static void main(String[] args) {
        int row = 3;    //马的初始位置的行
        int column = 2; //马的初始位置的列
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y];

        long start = System.currentTimeMillis();
        traversalChessboard(chessBoard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();

        System.out.println(end - start);

        for (int[] rows : chessBoard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 核心算法
     *
     * @param board  棋盘
     * @param row    当前马儿所在行 从0
     * @param column 当前马儿所在列 从0
     * @param step   当前马儿走的是第几步 从1开始
     */
    public static void traversalChessboard(int[][] board, int row, int column, int step) {
        //表示row column 是走了step步完成的
        board[row][column] = step;
        //1维数组来表示是否访问过 如：row = 4 X = 8 column = 4
        //那么这个index = 4 * 8 + 4 = 36 相当于是第36这个位置  因为 8x8的棋盘上有 64个位置从0开始那就是要-1
        visited[row * X + column] = true;
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps数据进行排序，排序的规则就是对ps的所有的Point对象的下一步的位置数目进行非递减排序
        sort(ps);

        //循环遍历ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0); //取出下一个可以走的位置
            //判断是否被访问过
            if (!visited[p.y * X + p.x]) {
                //然后递归
                traversalChessboard(board, p.y, p.x, step + 1);
            }
        }
        //判断马儿是否完成任务，使用step和应该走的步数比较，如果没有达到数量则表示没有完成任务，将整个棋盘置0
        //说明任务没完成
        //回溯，如果没完成或者走不通那么应该将 通过这个点调用的这个方法
        //board[row][column] = 0 visited[row*X+column] = false 重置

        /**
         * 1、棋盘到目前位置，仍然没有走完
         * 2、棋盘处于一个回溯过程
         */
        if (step < X * Y && !finished) {
            board[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前点计算其他点
     * 将当前位置设置为已访问，然后根据当前位置，计算马儿还能走哪些位置，并放入到一个集合中
     * （ArrayList）最多8个位置，每走一步，就使用step+1
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {

        ArrayList<Point> ps = new ArrayList<>();

        //创建Point
        Point p1 = new Point();
        //判断 向左移动2格并向上移动1格，是否可以走，档当前马的坐标
        //x-2 >= 0 && y-1 >= 0 表示可以走
        //因为按正常一个马是走日字格的，如果按正常他的所有方向都能走通
        //那么他接下来最多可以走8个位置
        //
        //下面是这8个位置的判断
        //走位置5
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1.x, p1.y));
        }
        //走位置6
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1.x, p1.y));
        }
        //走位置7
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1.x, p1.y));
        }
        //位置0
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1.x, p1.y));
        }
        //位置1
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1.x, p1.y));
        }
        //位置2
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1.x, p1.y));
        }
        //位置3
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1.x, p1.y));
        }
        //位置4
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1.x, p1.y));
        }

        return ps;
    }

    /**
     * 根据当前这个一步的所有的下一步的选择位置，进行非递减排序，减少回溯的次数
     * 越前面的下一步可能性越少
     *
     * @param ps
     */
    public static void sort(ArrayList<Point> ps) {
        ps.sort((o1, o2) -> {
            int count1 = next(o1).size();
            int count2 = next(o2).size();
            if (count1 < count2) {
                return -1;
            } else if (count1 == count2) {
                return 0;
            } else {
                return 1;
            }
        });
    }

}


