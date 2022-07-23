package recursion;

public class RecursionDemo {
    public static void main(String[] args) {
        //test(10);
        System.out.println(factorial(10));
    }

    public static void test(int n) {
        if (n>2) {
            test(n-1);
        }
        System.out.println("n=" + n);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return n;
        } else {
            return factorial(n-1) * n;
        }
    }

}
