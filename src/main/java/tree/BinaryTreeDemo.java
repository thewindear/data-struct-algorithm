package tree;

//二叉树
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        //创建节点
        BinaryNode root = new BinaryNode(1, "宋江");
        BinaryNode node2 = new BinaryNode(2, "吴用");
        BinaryNode node3 = new BinaryNode(3, "卢俊义");
        BinaryNode node4 = new BinaryNode(4, "林冲");
        BinaryNode node5 = new BinaryNode(5, "关圣");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);

        System.out.println("前序遍历：");
        System.out.println(binaryTree.preSearch(5));
        System.out.println("中序遍历：");
        System.out.println(binaryTree.infixSearch(5));
        //后序查找次数比较少
        System.out.println("后序遍历：");
        System.out.println(binaryTree.postSearch(5));


        //System.out.println(binaryTree.postSearch(4));



        System.out.println("删除节点前:");
        System.out.println("前序遍历：");
        binaryTree.preOrder();
        if (binaryTree.simpleDelete(3)) {
            System.out.println("删除后：");
            binaryTree.preOrder();
        } else {
            System.out.println("没有找到要删除的节点");
        }


        // 1 2 3 5 4
        //前序遍历 先输出 [1,2,3,4] 先输出当前值，再向左子树前序遍历，然后是右子树前序遍历
//        System.out.println("前序遍历:");
//        binaryTree.preOrder();

        //2,1,5,3,4
        //中序遍历 输出 [2,1,3,4] 先向左子树遍历，再输出节点，再向右子树遍历
//        System.out.println("中序遍历:");
//        binaryTree.infixOrder();

        //2,5,4,3,1
        //后序遍历 输出 [2，4，3，1] 先向左子树遍历，再输出节点，再向右子树遍历
//        System.out.println("后序遍历:");
//        binaryTree.postOrder();

    }
}

//二叉树只需要一个根节点即可以完成整个遍历
class BinaryTree {
    private BinaryNode root;
    public void setRoot(BinaryNode node) {
        this.root = node;
    }

    //先判断根节点是否为空以及是不是要删除根节点，然后再进行递归
    public boolean simpleDelete(int no) {
        if (this.root == null) {
            return false;
        }
        if (this.root.getNo() == no) {
            this.root = null;
            return true;
        }
        return this.root.simpleDelete(no);
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空无法遍历");
        }
    }

    //前序查找
    public BinaryNode preSearch(int no) {
        if (this.root == null) {
            return null;
        }
        return this.root.preSearch(no);
    }
    //中序查找
    public BinaryNode infixSearch(int no) {
        if (this.root == null) {
            return null;
        }
        return this.root.infixSearch(no);
    }

    //后序查找
    public BinaryNode postSearch(int no) {
        if (this.root == null) {
            return null;
        }
        return this.root.postSearch(no);
    }

    //前序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空无法遍历");
        }
    }

    //前序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空无法遍历");
        }
    }

}

class BinaryNode {
    private int no;
    private String name;

    //二叉树左子节点
    private BinaryNode left;
    //二叉树右子节点
    private BinaryNode right;

    public BinaryNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public void setLeft(BinaryNode node) {
        this.left = node;
    }

    public void setRight(BinaryNode node) {
        this.right = node;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public BinaryNode getRight() {
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

    //前序遍历 先输出当前节点，如果左子树不为空左子树前序遍历，
    //如果右子树不为空左子树前序遍历
    public void preOrder() {
        //先输出父节点
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //前序查找，先比较当前节点是否和要查找的内容一致如果一致直接返回否则
    //继续前序遍历
    public BinaryNode preSearch(int no) {
        System.out.println("进入前序遍历查找");
        //当前节点是否等于要找的内容如果是直接返回
        if (this.no == no) {
            return this;
        }
        BinaryNode findedNode = null;
        //1、左子节点不为空左子节点前序递归查找
        if (this.left != null) {
            findedNode = this.left.preSearch(no);
        }
        //2、如果左子节点前序查找没找到，那么向右子节点前序递查找
        if (findedNode == null && this.right != null) {
            findedNode = this.right.preSearch(no);
        }
        return findedNode;
    }


    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //中序递归遍历查找
    public BinaryNode infixSearch(int no) {
        BinaryNode findedNode = null;
        //1、左子节点不为空左子节点前序递归查找
        if (this.left != null) {
            findedNode = this.left.infixSearch(no);
        }
        if (findedNode != null) {
            return findedNode;
        }
        System.out.println("进入中序遍历查找");
        //2、中序查找，当前节点是否等于要找的内容如果是直接返回
        if (this.no == no) {
            return this;
        }
        //3、如果左子节点前序查找没找到，那么向右子节点前序递查找
        if (this.right != null) {
            findedNode = this.right.infixSearch(no);
        }
        return findedNode;
    }

    //后序遍历，如果左子树不为空后序遍历，如果右子树不为空后序遍历，再输出当前节点
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //后序递归查找
    public BinaryNode postSearch(int no) {
        BinaryNode findedNode = null;
        //1、左子节点不为空左子节点后序递归查找
        if (this.left != null) {
            findedNode = this.left.postSearch(no);
        }
        if (findedNode != null) {
            return findedNode;
        }
        //2、如果左子节点前序查找没找到，那么向右子节点后序递查找
        if (this.right != null) {
            findedNode = this.right.postSearch(no);
        }
        if (findedNode != null) {
            return findedNode;
        }
        System.out.println("进入后序遍历查找");
        //2、最后才是比对
        if (this.no == no) {
            return this;
        }
        return null;
    }

    //简单删除节点，不考虑排序和替换
    public boolean simpleDelete(int no) {
        //先判断左子节点是否为要删除的
        if (this.left != null && this.left.getNo() == no) {
            this.left = null;
            return true;
        }
        //再判断右子节点是否为要删除的
        if (this.right != null && this.right.getNo() == no) {
            this.right = null;
            return true;
        }
        boolean isFind = false;
        //如果上面都没有删除，那么向左子树进行递归删除
        if (this.left != null) {
            isFind = this.left.simpleDelete(no);
        }
        //如果左子树进行递归删除还是没有删除，再向右子树进行递归删除
        if (!isFind && this.right != null) {
            isFind = this.right.simpleDelete(no);
        }
        return isFind;
    }

}
