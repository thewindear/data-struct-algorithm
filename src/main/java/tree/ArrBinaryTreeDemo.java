package tree;

//以数组的方式存储二叉树
//访问第n个元素的左子节点：(2*n)+1
//访问第n个元素的右子节点：(2*n)+2
//访问第n个元素的父节点: (n-1)/2
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        //根节点的下标为0
        arrayBinaryTree.postOrder();

    }
}


//ArrayBinaryTree实现顺序存储二叉树
class ArrayBinaryTree {
    private int[] arr; //二叉树存储数据
    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }
    public void infixOrder() {
        preOrder(0);
    }
    public void postOrder() {
        preOrder(0);
    }

    /**
     * 编写一个方法 完成顺序存储二叉树的前序遍历
     * @param index
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前元素 因为是前序遍历所以先输出当前元素
        System.out.println(arr[index]);
        if ((index * 2 + 1) < arr.length) {
            //如果一个树的数据存放于数组中，那么当前节点的左子节点的 下标就是 2 * index + 1
            preOrder(2 * index + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            //如果一个树的数据存储于数组中，那么当前节点的右子节点的下标就是2*index+2
            preOrder(2 * index + 2);
        }
    }

    /**
     * 编写一个方法 完成顺序存储二叉树的中序遍历
     * @param index
     */
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        if ((index * 2 + 1) < arr.length) {
            //如果一个树的数据存放于数组中，那么当前节点的左子节点的 下标就是 2 * index + 1
            preOrder(2 * index + 1);
        }
        //输出当前元素 因为是前序遍历所以先输出当前元素
        System.out.println(arr[index]);
        if ((index * 2 + 2) < arr.length) {
            //如果一个树的数据存储于数组中，那么当前节点的右子节点的下标就是2*index+2
            preOrder(2 * index + 2);
        }
    }

    /**
     * 编写一个方法 完成顺序存储二叉树的后序遍历
     * @param index
     */
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        if ((index * 2 + 1) < arr.length) {
            //如果一个树的数据存放于数组中，那么当前节点的左子节点的 下标就是 2 * index + 1
            preOrder(2 * index + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            //如果一个树的数据存储于数组中，那么当前节点的右子节点的下标就是2*index+2
            preOrder(2 * index + 2);
        }
        //输出当前元素 因为是前序遍历所以先输出当前元素
        System.out.println(arr[index]);
    }
}
