package algorithm;

//分治算法 解决汉诺塔问题
public class DivideAndConquerDemo {
    public static void main(String[] args) {
        HanoiTower.hanoiTower(5, 'a', 'b', 'c');
    }
}


class HanoiTower {
    //汉诺塔移动方法
    //使用分治算法来解决
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            //如果num大于等于2 总是看成两个盘，最上边的盘和最下边的般
            //1、先把上面的盘a->b 移动过程会使用到c
            hanoiTower(num - 1, a, c, b);
            //2、把最下边的盘a->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //3、把b塔的所有盘从b->c
            hanoiTower(num - 1, b, a, c);
        }
    }
}
