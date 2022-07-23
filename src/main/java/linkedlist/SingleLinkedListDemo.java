package linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList s = new SingleLinkedList();
        s.addByOrder(new Node(2, "卢俊义", "玉麒麟"));
        s.addByOrder(new Node(1, "宋江", "及时雨"));
        s.addByOrder(new Node(4, "林冲", "豹子头"));
        s.addByOrder(new Node(3, "吴用", "智多星"));
        s.list();
        System.out.println();

        s.update(new Node(3, "吴用2", "智多星2"));
        s.list();

        System.out.println();


        s.remove(4);
        s.addByOrder(new Node(5, "吴用3", "智多星3"));


        s.list();

        System.out.println(SingleLinkedList.getLength(s.getHead()));

        System.out.println(SingleLinkedList.findLastIndexNode(s.getHead(), 4));

        System.out.println("反转：");

        SingleLinkedList.singleLinkListReverse(s.getHead());

        s.list();

        System.out.println("倒序打印：");

        SingleLinkedList.reversePrintLinkedList(s.getHead());

    }
}

//单链表
class SingleLinkedList {
    //先初始化一个头节点
    //不保存数据
    private Node head = new Node(0, "", "");

    public Node getHead() {
        return head;
    }

    // 删除节点，找到当前要删除的节点的前一个节点
    public void remove(int no) {
        Node tmp = head;
        if (tmp.next == null) {
            System.out.println("链表为空");
            return;
        }
        //是否找到
        boolean flag = false;
        while(true) {
            if (tmp.next == null) {
                break;
            }
            if (tmp.next.no == no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            tmp.next = tmp.next.next;
        } else {
            System.out.println("没有找到要删除的元素");
        }

    }

    // 要找到最后一个节点将最后一个节点的next指向添加的这个节点
    public void add(Node node) {
        Node tmp = head;
        while(true) {
            //表示这是链表的最后一个元素
            if (tmp.next == null) {
                break;
            }
            //如果没有找到最后，将tmp后移
            tmp = tmp.next;
        }
        //当退出循环时tmp已是最后一个节点
        //那么再将他的next指向新添加的节点
        tmp.next = node;
    }

    //修改节点信息。根据no编号来修改即no编号不能修改
    public void update(Node node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到要修改的节点
        Node tmp = head.next;
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

    //添加时排序 这样就是通过排序来添加节点
    public void addByOrder(Node node) {
        //因为头节点不能动，因此仍然通过一个临时变量来帮助找到添加的位置
        //
        Node tmp = head;
        //如果添加的编号已存在报错
        boolean flag = false;
        while(true) {
            if (tmp.next == null) {
                break;
            }
            //就在这个节点后面插入
            if (tmp.next.no > node.no) {
                break;
            } else if (tmp.next.no == node.no) {
                //说明no已经存在
                flag = true;
                break;
            }
            //后移遍历 链表
            tmp = tmp.next;
        }
        if (flag) {
            //不能添加
            System.out.println("插入的值已存在无法插入");
        } else {
            node.next = tmp.next;
            tmp.next = node;
        }
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
        } else {
            //头节点不能动所以需要一个临时变量
            Node tmp = head.next;
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

    //1、获取单链表有效节点个数（可以使用遍历）
    public static int getLength(Node head) {
        int count = 0;
        if (head.next == null) {
            return count;
        }
        Node cur = head.next;
        while(cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    /**
     * 查询节点的倒数第index个节点
     * @param head
     * @param index
     * @return
     */
    public static Node findLastIndexNode(Node head, int index) {
        if (head == null || head.next == null) {
            return null;
        }
        //先得到链表长度
        var size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        Node cur = head.next;
        for(int i=0; i<size-index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //单链表的反转
    public static void singleLinkListReverse(Node head) {
        //不进行反转只有一个或者为空
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个辅助的指针，帮助遍历原来的链表
        Node cur = head.next;
        Node next = null;
        Node reverseHeadNode = new Node(0, "", "");
        //1、先定义一个反转后的头节点
        //2、从头到尾遍历一次链表，每遍历一个节点，并放在新链表的最前面
        while(cur != null) {
            //先暂时保存当前节点的下一个节点，因为后面需要使用
            next = cur.next;
            //将cur的下一个节点指向新的链表最前端
            cur.next = reverseHeadNode.next;
            //再将反转头节点的next指向当前节点
            reverseHeadNode.next = cur;
            //后移
            cur = next;
        }
        //7、将原链表的head的next指向这个新链表即可
        head.next = reverseHeadNode.next;
    }

    //1、利用栈将节点压入，然后再打印
    public static void reversePrintLinkedList(Node head) {
        Stack<Node> stack = new Stack<>();
        if (head == null || head.next == null) {
            return;
        }
        Node cur = head.next;
        while(cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        //倒序打印
        while(stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}

//定义一个Node每个Node对象就是一个节点
class Node {
    public int no;
    public String name;
    public String nickname;
    public Node next;
    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Node [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}
