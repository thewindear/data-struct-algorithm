package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList d = new DoubleLinkedList();
//        d.add(new Node2(1, "宋江", "及时雨"));
//        d.add(new Node2(100, "有用", "超多钱"));
//        d.add(new Node2(2, "吴用", "智多星"));
//
//        d.del(100);
//
//        d.add(new Node2(200, "有用", "超多钱"));
//
//        d.del(1);
//
//        d.list();

        d.addByOrder(new Node2(999,"吴用", "智多星"));

        d.addByOrder(new Node2(2,"卢俊义", "玉麒麟"));
        d.addByOrder(new Node2(1,"宋江", "及时雨"));
        d.addByOrder(new Node2(3,"吴用", "智多星"));

        d.list();
    }
}

class DoubleLinkedList {
    Node2 head = new Node2(0,"", "");
    public Node2 getHead() {
        return head;
    }

    public void addByOrder(Node2 node) {
        Node2 tmp = head;
        boolean flag = false;
        while(true) {
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next;
            if (tmp.no >= node.no) {
                flag = true;
                break;
            }
        }
        if (flag) {
            node.pre = tmp.pre;
            tmp.pre.next = node;
            node.next = tmp;
            tmp.pre = node;
        } else {
            node.pre = tmp;
            tmp.next = node;
        }
    }

    //双向链表添加操作
    public void add(Node2 node) {
        Node2 tmp = head;
        while(true) {
            //next为空表示是最后一个节点
            if (tmp.next == null) {
                //那么当前需要添加的节点的pre上一个节点
                //就等于当前节点
                node.pre = tmp;
                tmp.next = node;
                break;
            } else {
                tmp = tmp.next;
            }
        }
    }

    //删除一个节点
    public void del(int no) {
        Node2 tmp = head;
        while(true) {
            //双向链表删除，只需要找到要删除的那个节点，将 tmp.pre.next = tmp.next 即可
            if (tmp.no == no) {
                tmp.pre.next = tmp.next;
                break;
            }
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next;
        }
    }

    public void update(Node2 node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到要修改的节点
        Node2 tmp = head.next;
        //是否找到了
        boolean flag = false;
        while(true) {
            if (tmp == null) {
                break; //链表已经遍历 完了
            }
            if (tmp.no == node.no) {
                //找到了
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            tmp.name = node.name;
            tmp.nickname = node.nickname;
        } else {
            System.out.println("没有找到节点");
        }
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
        } else {
            //头节点不能动所以需要一个临时变量
            Node2 tmp = head.next;
            while(true) {
                //表示链表遍历 到最后一个节点
                if (tmp == null) {
                    break;
                }
                //输出节点信息
                System.out.println(tmp);
                //将节点后移
                tmp = tmp.next;
            }
        }
    }
}


//定义一个Node每个Node对象就是一个节点
class Node2 {
    public int no;
    public String name;
    public String nickname;
    //上一个节点
    public Node2 pre;
    //下一个节点
    public Node2 next;
    public Node2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Node2 [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}
