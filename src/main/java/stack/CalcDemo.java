package stack;

public class CalcDemo {
    public static void main(String[] args) {
        String exps = "1+20*5-5";
        //创建两个栈一个数字栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //扫描索引
        int index = 0;
        int num1;
        int num2;
        int oper;
        int res;
        String keepNums = new String();
        //每次扫描得到的char保存至char
        char ch = ' ';

        while(true) {
            //取一个字符
            ch = exps.substring(index, index+1).charAt(0);
            //如果是运算符
            if (operStack.isOper(ch)) {
                //判断栈是否为空 直接入栈
                if (!operStack.isEmpty()) {
                    //如果有操作符比较栈顶的符号的优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //如果满足条件，那么要将数栈中弹出两个数进行计算
                        //弹出两个数字
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        //再弹出一个操作符
                        oper = operStack.pop();
                        //计算结果
                        res = numStack.cal(num1, num2, oper);
                        //将结果压入数字栈
                        numStack.push(res);
                        //再将新的符号压入符号栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            } else {
                //如果是数字就直接入数栈
                //1.当处理多位数时，不能发现是一个数字就立即入栈，还需要向后扫描
                keepNums += String.valueOf(ch);

                //如果ch已经是最后一个字符就直接入栈
                if (index == exps.length() - 1) {
                    numStack.push(Integer.parseInt(keepNums));
                } else {
                    //numStack.push(ch - 48);
                    if (operStack.isOper(exps.substring(index+1, index+2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNums));
                        keepNums = "";
                    }
                }
            }
            //让index++ 并判断且是否扫描到最后
            index++;
            if (index >= exps.length()) {
                break;
            }
        }

        while(true) {
            if (operStack.isEmpty()) {
                break;
            }
            oper = operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        System.out.printf("最终结果: %s= %d", exps, numStack.pop());

    }
}

class ArrayStack2 {
    //最大容量
    int maxSize;
    //栈顶
    int top = -1;
    //用于保存数据
    int[] stack;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    public int peek() {
        return stack[top];
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

    //获取优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public int cal(int num1, int num2, int oper) {
        int res = switch (oper) {
            case '+' -> num1 + num2;
            case '-' -> num2 - num1;
            case '/' -> num2 / num1;
            case '*' -> num1 * num2;
            default -> 0;
        };
        return res;
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