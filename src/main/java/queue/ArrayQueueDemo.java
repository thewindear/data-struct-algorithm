package queue;

import java.util.Queue;

public class ArrayQueueDemo {

    public static void main(String[] args) throws Exception {
        ArrayQueue aq = new ArrayQueue(3);
        aq.add(100);
        aq.add(200);
        aq.add(300);

        System.out.println(aq.remove());
    }
}

class ArrayQueue {
    //队列尾下标
    int rear;
    //队列头下标
    int front;
    //队列最大容量
    int maxSize;
    //队列内容
    int[] items;

    //创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        items = new int[maxSize];
        front = -1; //指向队列头的前一个位置
        rear = -1;  //指向队列的尾指向队列尾的数据 队列最后一个下标
    }

    //判断队列是否满
    public boolean isFull() {
        return rear == this.maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据
    public void add(int item) throws Exception {
        //队列是否满如果满了抛出异常
        if (this.isFull()) {
            throw new Exception("queue is full");
        }
        rear++;
        this.items[rear] = item;
    }

    public int len() {
        return this.items.length;
    }

    //出队列
    public int remove() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("queue is empty");
        }
        front++;
        return this.items[front];
    }
}
