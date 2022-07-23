package stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PolandNotationDemo {
    public static void main(String[] args) {

        //将中缀表达式转为后缀表达式
        String expression = "1+((2+3)*4)-5";

        // [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] 会得到这么一个结果
        List<String> expressions = toInfixExpressionList(expression);
        System.out.println(expressions);
        List<String> suffixExpressionList = parseSuffixExpressionList(expressions);
        System.out.println(suffixExpressionList);
        int res = calc(suffixExpressionList);
        System.out.println(res);


        //先定义逆波兰表达式
//        String suffixExpression = "3 4 + 5 * 6 -";
//        String suffixExpression = "30 40 + 5 * 6 -";
        // 4 * 5 - 8 + 60 + 8 / 2
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
//        //先将suffixExpression保存成一个ArrayList
//        List<String> rpnList = getListString(suffixExpression);
//        int res = calc(rpnList);
//        System.out.println(res);
    }

    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>(); //符号栈
        //因为s2这个栈没有pop操作，所以可以直接List
        List<String> s2 = new ArrayList<>();

        //遍历ls
        for(String item:ls) {
            //如果是一个数字的话加压入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号则依次弹出s1栈顶的运算符并压入s2直到遇到左括号为止，然后丢弃这一对括号
                while(!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //将 ( 弹出s1栈的 消除小括号
            } else {
                //当item的优先级小于等于s1栈顶的运算符，将s1栈顶的运算符弹出并加入到s2中
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1中剩余的加入到s2中
        while(s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }

    //将中缀表达式转一个list
    public static List<String> toInfixExpressionList(String s) {
        //先定义一个list用来存放对应数据
        List<String> ls = new ArrayList<>();
        int i = 0;  //指针，用于遍历中缀表达式
        String str; //对多位数的拼接
        char c;     //每遍历一个字符，就放入到c中
        do {
            //如果是一个非数字 需要加入到ls ascii 码
            if ((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57) {
                ls.add(""+c);
                i++;
            } else {  //考虑多位数
                //不停的后移拼接数字
                str = "";
                while(i<s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
                    str += String.valueOf(c);
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    public static List<String> getListString(String suffixExpression) {
        var lists = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        Collections.addAll(list, lists);
        return list;
    }

    /**
     * 1、从左至右扫描，如果是数压入栈
     * 2、如果是
     * @param list
     * @return
     */
    public static int calc(List<String> list) {
        //创建一个stack 只需要一个栈
        Stack<String> stack = new Stack<>();
        for(String item:list) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = switch (item) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num1 / num2;
                    default -> throw new RuntimeException("operation not ok");
                };
                stack.push(String.valueOf(res));
            }
        }
        //得到结果
        return Integer.parseInt(stack.pop());
    }
}

//操作符优先级
class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    public static int getValue(String operation) {
        return switch(operation) {
            case "+" -> ADD;
            case "-" -> SUB;
            case "*" -> MUL;
            case "/" -> DIV;
            default -> 0;
        };
    }

}
