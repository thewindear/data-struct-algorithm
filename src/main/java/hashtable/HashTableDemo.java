package hashtable;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable h = new HashTable(5);
        h.add(new Emp(1, "张三"));
        h.add(new Emp(2, "李四"));
        h.add(new Emp(3, "王五"));
        h.add(new Emp(11, "张三2"));

        System.out.println("打印：");
        h.list();

        Emp emp = h.findByEmp(11);
        if (emp == null) {
            System.out.println("没有找到");
            return;
        }
        System.out.println("emp: " + emp.id + " " + emp.name);


    }

    //遍历链表信息
    public static void list(LinkedList linkedList) {
        if (linkedList == null || linkedList.head == null) {
            //说明链表为空
            System.out.println("当前链表为空");
            return;
        }
        Node<Emp> curEmp = linkedList.head;
        System.out.println("当前链表的信息为:");
        while(true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.data.id, curEmp.data.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println("遍历结束");
    }
}


//每个节点类型
class Emp {
    public int id;
    public String name;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Node<T> {
    public T data;
    public Node<T> next;
    public Node(T data) {
        this.data = data;
    }
}

//创建以Emp为类型的链表
class LinkedList {
    //这里的头指针是指向第一个节点的不是之前链表中的头节点
    Node head; //默认为空

    //添加数据到链表
    //1、假定、当添加数据时id是自增的，即id的分配总是从小到大
    //   因此将该数据添加到最后
    public void add(Node emp) {
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个数据，则使用一个辅助指针，帮助定位到最后
        Node curEmp = head;
        while(true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        //退出时直接将emp加入到最后
        curEmp.next = emp;
    }

    public Emp findById(int id) {
        if (head == null) {
            return null;
        }
        //如果不是第一个数据，则使用一个辅助指针，帮助定位到最后
        Node<Emp> curEmp = head;
        while(true) {
            if (curEmp.data.id == id) {
                return curEmp.data;
            }
            if (curEmp.next == null) {
                return null;
            }
            curEmp = curEmp.next;
        }
    }

}

//创建以Emp为类型的Hashtable
class HashTable {
    //有多少个链表
    int size;
    private LinkedList[] linkedListArray;
    //构造器
    public HashTable(int size) {
        this.size = size;
        linkedListArray = new LinkedList[size];
    }

    //遍历链表
    public void list() {
        for (int i=0;i<size;i++) {
            System.out.println("link no:" + i);
            HashTableDemo.list(linkedListArray[i]);
        }
    }

    public void add(Emp emp) {
        //根据员工的id 得到该员工 应该添加到哪个链表中
        int empLinkedListNo = hashFun(emp.id);
        if (linkedListArray[empLinkedListNo] == null) {
            linkedListArray[empLinkedListNo] = new LinkedList();
        }
        linkedListArray[empLinkedListNo].add(new Node(emp));
    }

    //编写散列函数，使用简单取模法
    public int hashFun(int id) {
        return id % size;
    }

    //通过id查询
    public Emp findByEmp(int id) {
        //先将要查找的id 通过 hash方法得到一个hashid
        int hashCode = hashFun(id);
        if (linkedListArray[hashCode] == null) {
            return null;
        }
        return linkedListArray[hashCode].findById(id);
    }
}