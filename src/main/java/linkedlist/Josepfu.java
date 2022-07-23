package linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList s = new CircleSingleLinkedList();
        s.count(1, 2, 5);
    }
}

//环形单向链表
class CircleSingleLinkedList {
    //创建一个first节点
    Node3 first = new Node3(-1);

    //添加节点构建成环形节点
    public void add(int nums) {
        if (nums < 1) {
            System.out.println("添加的数字有问题");
            return;
        }
        //辅助指针 全用保存当前遍历的最后一个节点
        Node3 cur = null;
        //创建环境形链表
        for(int i=1;i<=nums;i++) {
            Node3 node = new Node3(i);
            //如果是第一个节点
            if (i == 1) {
                first = node;
                first.next = first; //构成环形
                cur = first;        // 第1个节点
            } else {
                cur.next = node;    //
                node.next = first;  //构成环形
                cur = node;         //当前遍历的节点等于最新的一个节点
            }

        }
    }

    //遍历环形链表
    //需要一个辅助指针，当遍历cur.next == first表示遍历结束
    public void each() {
        if (first == null || first.next == null) {
            System.out.println("空链表");
        }
        Node3 cur = first;
        while(true) {
            System.out.printf("Node: %d\n", cur.no);
            if (cur.next == first) {
                System.out.println("Done!");
                break;
            }
            cur = cur.next;
        }
    }


    //根据输入，计算节点出圈的顺序
    public void count(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数错误");
            return;
        }
        this.add(5);
        //先让辅助指针指向第一个节点
        Node3 helper = first;
        //需要遍历一次helper让他在最后一个节点 helper的位置在 first的后一个位置上 因为是单向环形链表
        //当要将某个节点的数据删除时，需要在那个节点之后
        while(true) {
            if (helper.next == first) {
                //说明已经指向最后一个节点
                break;
            }
            helper = helper.next;
        }
        //报数前，先让first和helper移动 k-1次
        //定位helper和first开始的位置
        for(int j=0;j<startNo-1;j++) {
            first = first.next;
            helper = helper.next;
        }

        //开发报数，让frist和helper都移动至countNum-1次然后开始出圈
        while(true) {
            //说明圈中只有一个节点
            if (first == helper) {
                break;
            }
            //将first移动到指定的数上
            //helper是在first之后的一个数上
            for(int j=0;j<countNum-1;j++) {
                first = first.next;
                helper = helper.next;
            }
            //first指向的就是要出圈的节点
            System.out.printf("出圈Node: %d\n", first.no);
            first = first.next;
            helper.next = first;
        }
        System.out.printf("最后出圈Node: %d\n", helper.no);
    }

}

class Node3 {
    int no;
    Node3 next;

    public Node3(int no) {
        this.no = no;
    }
}
