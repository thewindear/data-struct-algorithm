package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//赫夫曼树
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffmanNode root = createHuffmanTree(arr);
        root.preOrder();
    }

    //最后就得到一个树
    public static HuffmanNode createHuffmanTree(int[] arr) {
        List<HuffmanNode> list = new ArrayList<>();
        for (int item : arr) {
            list.add(new HuffmanNode(item));
        }
        while (list.size() > 1) {
            //应该还要再进行一次排序
            Collections.sort(list);
            //1、从整个队列中取出两个最小的两个节点进行组合成一个二叉树
            HuffmanNode leftNode = list.get(0);
            HuffmanNode rightNode = list.get(1);

            HuffmanNode parent = new HuffmanNode(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //再将拿出来的两个节点移除
            list.remove(leftNode);
            list.remove(rightNode);
            //再将新的二叉树加入序列中
            list.add(parent);
        }
        return list.get(0);
    }

    public static void createHuffmanTreeProcess(int[] arr) {
        //1、先将所有的数组元素转换成一个个节点
        List<HuffmanNode> list = new ArrayList<>();
        for (int item : arr) {
            list.add(new HuffmanNode(item));
        }
        //2、然后将这个数组进行排序从小大到
        Collections.sort(list);

        System.out.println("排序后的节点： " + list);

        //3、取出根节点权值最小的两个颗二叉树
        HuffmanNode left = list.get(0);
        HuffmanNode right = list.get(1);
        //4、构建新的二叉树
        HuffmanNode parent = new HuffmanNode(left.value + right.value);
        parent.left = left;
        parent.right = right;

        //5、要将取出来的那两个元素从数组中移除
        list.remove(left);
        list.remove(right);

        //6、再将新的parent加入到新数组中
        list.add(parent);
        //7、重复上面步骤后，直到这个数组只有1个节点证明整个赫夫曼树已完成
        System.out.println("第一次处理后得到：" + list);

    }
}

//赫夫曼节点
//为了让两个Node可以直接进行比较需要实现Comparable接口
class HuffmanNode implements Comparable<HuffmanNode> {
    //节点权值
    int value;
    //左子节点
    HuffmanNode left;
    //右子节点
    HuffmanNode right;

    public HuffmanNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node [value=" + this.value + "]";
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.value - o.value;
    }
}