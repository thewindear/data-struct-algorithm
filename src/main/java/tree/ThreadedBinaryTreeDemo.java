package tree;

//线索二叉树
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //
        ThreadedBinaryNode root = new ThreadedBinaryNode(1, "Tom");
        ThreadedBinaryNode node2 = new ThreadedBinaryNode(3, "Jack");
        ThreadedBinaryNode node3 = new ThreadedBinaryNode(6, "Smith");
        ThreadedBinaryNode node4 = new ThreadedBinaryNode(8, "mary");
        ThreadedBinaryNode node5 = new ThreadedBinaryNode(10, "king");
        ThreadedBinaryNode node6 = new ThreadedBinaryNode(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadNodes();

        //线索化后，node5的后继节点应该是1（root节点）
//        System.out.println(node5.getRight().getNo());
        //线索化后，node5的后继节点应该是3（node2）
//        System.out.println(node5.getLeft().getNo());


    }
}

//实现线索二叉树
class ThreadedBinaryTree {
    private ThreadedBinaryNode root;

    //为了实现线索化需要创建一个指向当前节点的前驱节点的指针
    //在递归线索化时，总是保留当前节点的前一个节点
    private ThreadedBinaryNode pre = null;

    public void setRoot(ThreadedBinaryNode node) {
        this.root = node;
    }

    public void threadNodes() {
        this.threadNodes(this.root);
    }

    /**
     * 中序线索化的方法
     * @param node 就是当前需要线索化的节点
     */
    public void threadNodes(ThreadedBinaryNode node) {
        if (node == null) {
            return;
        }
        //1、线索化左子树
        threadNodes(node.getLeft());
        //2、线索化当前节点
        //2.1 先处理当前节点前驱节点
        if (node.getLeft() == null) {
            //就让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点左指针的类型 指向的是前驱节点而不是左子树
            node.setLeftType(1);
        }

        //处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前节点，成为下一个节点的前驱节点
        pre = node;
        //3、线索化右子树
        threadNodes(node.getRight());
    }

    //遍历线索化二叉树的方法 使用的也是中序遍历
    public void threadList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        ThreadedBinaryNode node = root;
        while(node != null) {
            //循环找到leftType == 1 的节点
            //后面随着遍历而变化，因为当leftType == 1时，说明该节点是按照线索化处理后的有效节点
            while(node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node.getNo());
            //如果当前节点的右指针是不是后继节点，就一直输出
            while(node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node.getNo());
            }
            //替换遍历的节点
            node = node.getRight();
        }
    }

    public void preThreadList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        ThreadedBinaryNode node = root;
        while(node != null) {
            while (node.getLeftType() == 0) {
                System.out.println(node.getNo());
                node = node.getLeft();
            }

            while (node.getRightType() == 1) {
                System.out.println(node.getNo());
                node = node.getRight();
            }

            node = node.getRight();

        }
    }

}

//创建一个节点
class ThreadedBinaryNode {
    private int no;
    private String name;

    //左子节点类型 lefType 0 = 左子树 1 = 前驱节点
    private int leftType;
    //右子节点类型 rightType 0 = 右子树 1 = 后继节点
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    //二叉树左子节点
    private ThreadedBinaryNode left = null;
    //二叉树右子节点
    private ThreadedBinaryNode right = null;

    public ThreadedBinaryNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public void setLeft(ThreadedBinaryNode node) {
        this.left = node;
    }

    public void setRight(ThreadedBinaryNode node) {
        this.right = node;
    }

    public ThreadedBinaryNode getLeft() {
        return left;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public ThreadedBinaryNode getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
