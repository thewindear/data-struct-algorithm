package recursion;

public class MiGongDemo {
    public static void main(String[] args) {
        int line = 8;
        int column = 7;

        int[][] migong = makeMiGong(line, column);
        printMiGong(migong, line, column);
        //迷宫起始位置，1，1肯定是0那么会在这里点上设置为2
        setWay(migong, 1, 1);
        System.out.println();
        printMiGong(migong, line, column);
    }

    /**
     * 1、i,j 从地图哪个位置开始
     * 2、如果小球能到map[6][5]表示通路了
     * 3、当map[i][j]等于0时该点没有走过，当为1表示墙，当为2时通路，当为3时该位置已走过但是不通
     * 4、需要确定一个策略，下->右->上->左 当下走不能再依次走这些顺序,如果走不通再回溯
     * @param map 地图
     * @param i   从哪个位置 开始找
     * @param j
     * @return    如果找到了就返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        //终点是否为2
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] ==0) {
                //先会到这个位置，将这个坐标的值设置为2表示已到了
                //然后再去尝试 从当前坐标，下、右、上、左这几个方向移动
                //如果使用setWay返回true那么将当前坐标没问题值为2再继续递归
                //如果走不通那么为设置为3

                map[i][j] = 2; //假定该点可以走通，如果不通下面会设置为3

                //当前坐标设置为2表示通过，那么再尝试，从下、右、上、左的规则来跑
                //看看还有没有通路，也就是看看有没有坐标等于0的坐标，如果有再继续递归，
                //再在那个点上再尝试 从下、右、上、左的规则 来跑，一直这样递归递归
                //直到走到规则的6,5这个坐标为止
                if (setWay(map, i+1, j)) { //向下走通过了就返回true
                    return true;
                } else if (setWay(map, i, j+1)) { //向右走 通过 了就返回true
                    return true;
                } else if (setWay(map, i-1, j)) { //向上走
                    return true;
                } else if (setWay(map, i, j-1)) { //向左走
                    return true;
                } else { //四个方向都不通就返回false
                    map[i][j] = 3;
                    return false;
                }
            } else { //如果map[i][j] != 0
                return false;
            }
        }
    }

    public static void printMiGong(int[][] migong, int line, int column) {
        for(int i=0;i<line;i++) {
            for(int j=0;j<column;j++) {
                System.out.printf(migong[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public static int[][] makeMiGong(int line, int column) {
        //创建一个二维数组，模拟迷宫
        int[][] map = new int[line][column];
        //使用1表示墙
        //先把上全部设置为1
        for(int i=0;i<column;i++) {
            map[0][i] = 1;
            map[column][i] = 1;
        }
        for(int i=0;i<line;i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置档板
        map[3][1] = 1;
        map[3][2] = 1;
        map[2][2] = 1;
        return map;
    }
}
