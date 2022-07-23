package stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
//        ArrayStack arrayStack = new ArrayStack(10);
//        arrayStack.push(10);
//        arrayStack.push(20);
//        arrayStack.push(30);
//        arrayStack.push(40);

        //这里应该是弹出40
//        arrayStack.pop();
//        arrayStack.pop();
//        System.out.println(arrayStack.pop());
//        arrayStack.list();

        ArrayStack numStack = new ArrayStack(10);
        ArrayStack opStack = new ArrayStack(10);

        //表达式
        String exp = "1+5*8-5";

        while(true) {

        }




    }


}

class ArrayStack {
    //最大容量
    int maxSize;
    //栈顶
    int top = -1;
    //用于保存数据
    int[] stack;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    //栈满了
    public boolean isFull() {
        return this.top == (this.maxSize - 1);
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public void push(int data) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException("stack is full");
        }
        top++;
        this.stack[top] = data;
    }

    public int pop() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        int value = this.stack[top];
        top--;
        return value;
    }

    //遍历 需要从栈顶开始展示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈是空的");
            return;
        }
        for(int i=top;i>-1;i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

}
