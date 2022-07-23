package queue;

public class CircleArrayQueueDemo {
    public static void main(String[] args) throws Exception {
        CircleArrayQueue queue = new CircleArrayQueue(3);
        queue.add(100);
        queue.add(200);
        queue.add(300);
        System.out.println(queue.remove());
        queue.add(400);
        System.out.println(queue.remove());
    }
}

class CircleArrayQueue {
    //队列尾下标
    int rear;
    //队列头下标
    int front;
    //队列最大容量
    int maxSize;
    //队列内容
    int[] items;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize + 1;
        this.items = new int[this.maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void add(int item) throws Exception {
        if (this.isFull()) {
            throw new Exception("queue is full");
        }
        this.items[rear] = item;
        //将rear后移
        rear = (rear + 1) % maxSize;
    }

    public int size() {
        //队尾下标 + 总大小 - 队列头下标 再和 总大小 取模
        //可以得到实际这个队列的大小，因为是环形队列所以需要取模
        return (rear + maxSize - front) % maxSize;
    }

    public int remove() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("queue is empty");
        }
        int value = items[front];
        //当移除一个元素的时候，队头下标  队头下标 + 1 再和 总大小 取模，
        //可以得到新的队头下标的值，当front + 1 == maxSize的时候就变成成了0
        //那么就可以循环使用之前下标的值
        front = (front + 1) % maxSize;
        return value;
    }

}
