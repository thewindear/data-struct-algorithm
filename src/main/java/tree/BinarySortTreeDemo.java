package tree;

//二叉排序树
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
//        int[] arr = {1, 3, 12, 3424, 53, 87, 333121};
        BinarySortTree tree = new BinarySortTree();
        for (int item : arr) {
            tree.add(new BinarySortNode(item));
        }
        tree.delete(7);
        tree.infixOrder();
    }
}

//二叉排序树
class BinarySortTree {
    BinarySortNode root;

    public void add(BinarySortNode node) {
        if (this.root != null) {
            this.root.add(node);
        } else {
            this.root = node;
        }
    }

    public BinarySortNode search(int val) {
        if (root != null) {
            return this.root.search(val);
        }
        return null;
    }

    public BinarySortNode searchParent(int val) {
        if (root != null) {
            return this.root.searchParent(val);
        }
        return null;
    }

    /**
     * 找到以node为根节点的最小的节点
     * 1、返回以node为根节点的二叉排序树的最小节点的值
     * 2、删除node为根节点的二叉排序树的最小节点
     *
     * @param node 传入的节点当做二叉排序树的根节点
     * @return 返回一个以node为根节点的
     */
    public int delRightTreeMin(BinarySortNode node) {
        BinarySortNode tmp = node;
        //循环查找左节点就可以查找到最小节点
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        //这时tmp就指向了最小的节点
        delete(tmp.val);
        return tmp.val;
    }

    //删除节点
    public void delete(int val) {
        if (root == null) {
            return;
        }
        //1.要找到删除的节点targetNode
        BinarySortNode targetNode = this.search(val);
        //没有要删除的节点
        if (targetNode == null) {
            return;
        }
        //判断根节点是否存在子颗如果左右子树都为空，那么表示当前排序二叉只存在一个根节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        //2.要找到删除节点的父节点
        BinarySortNode parent = this.searchParent(val);
        //判断是否为叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            //targetNode是父节点的左子节点还是右子节点
            if (parent.left != null && parent.left.val == targetNode.val) {
                parent.left = null;
            } else if (parent.right != null && parent.right.val == targetNode.val) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            //当要删除的节点有两颗子树
            //从右子树找最小的值并删除他，并返回那个值，替换当前targetNode的值
            targetNode.val = delRightTreeMin(targetNode.right);
        } else {
            //其他情况就是当要删除的节点只有一颗子树
            //1、如果targetNode 存在左子节点
            // 1.ⅰ. 如果targetNode是parent的左子节点  parent.left = targetNode.left
            // 1.ⅱ. 如果targetNode是parent的右子节点 parent.right = targetNode.left
            if (targetNode.left != null) {
                if (parent != null) {
                    if (parent.left.val == val) {
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                } else {
                    root = targetNode.left;
                }
            } else {
                //2、如果targetNode 存在右子节点
                // 2.ⅲ. 如果 targetNode是右子节点 targetNode是node的左子节点parent.left = targetNode.right
                // 2.ⅳ. 如果targetNode是parent的右子节点parent.right = targetNode.right
                if (parent != null) {
                    if (parent.left.val == val) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                } else {
                    root = targetNode.right;
                }
            }

        }

    }

    //添加通过这种递归方式添加完之后，再使用中序遍历，那么得到的就是一个有序的序列
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        }
    }
}

//二叉排序树节点
class BinarySortNode {
    int val;
    BinarySortNode left;
    BinarySortNode right;

    public BinarySortNode(int val) {
        this.val = val;
    }

    /**
     * 查找要删除的节点
     *
     * @param val 要删除的节点的值
     * @return
     */
    public BinarySortNode search(int val) {
        //表示找到了节点
        if (this.val == val) {
            return this;
        } else if (val < this.val) { //如果查找的节点小于当前节点，向左子树递归查找
            if (this.left != null) {
                return this.left.search(val);
            }
            return null;
        } else {
            if (this.right != null) { //如果查找的节点大于等于当前节点，向右子树递归查找
                return this.right.search(val);
            }
            return null;
        }
    }

    /**
     * @param val 要查找节点的值
     * @return 返回要删除的节点的父节点，如果没有就返回null`
     */
    public BinarySortNode searchParent(int val) {
        //如果当前节点就是要删除节点的父节点就直接返回
        if ((this.left != null && this.left.val == val) || (this.right != null && this.right.val == val)
        ) {
            return this;
        } else {
            //如果查找的值小于当前节点的值。，并且当前节点的左子节点不为空
            if (val < this.val && this.left != null) {
                return this.left.searchParent(val); //向左子树递归查找
            } else if (val >= this.val && this.right != null) {
                return this.right.searchParent(val); //向右子树递归查找
            } else {
                return null; //没有找到父节点
            }
        }
    }

    /**
     * 递归添加节点，在添加节点的时候判断，和当前节点比较如果比当前节点小
     * 那么向左遍历，并添加到相应位置上，如果比当前节点大那么向右遍历并添加到
     * 相应位置上，是通过递归的方式去找对应的位置，如果left或right为空
     * 那么直接将其赋值node
     *
     * @param node
     */
    public void add(BinarySortNode node) {
        //递归的形式添加二叉排序树的要求
        if (node != null) {
            //判断传入的节点的值和当前子树的节点的值关系
            if (node.val < this.val) {
                //如果当前节点左子节点为空直接赋值
                if (this.left == null) {
                    this.left = node;
                } else {
                    //如果不为空就递归向左子树添加
                    this.left.add(node);
                }
            } else {
                //如果是大于等于当前节点，那么赋值在右边，
                //如果右边不子节点不为空那么递归添加
                if (this.right == null) {
                    this.right = node;
                } else {
                    //如果不为空就递归向左子树添加
                    this.right.add(node);
                }
            }
        }
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

    public String toString() {
        return "Node [val=" + this.val + "]";
    }
}
