package tree;

import java.sql.Array;
import java.util.*;

public class HuffmanCodeDemo {

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("生成的赫夫曼编码后的byte：" + Arrays.toString(huffmanCodeBytes));


        System.out.println("==== decode ====");
        byte[] decodeByte = decode(huffmanCode, huffmanCodeBytes);

        System.out.println(new String(decodeByte));


    }


    /**
     * 要将 [99, -12, 126, -113, -44, 89, -121, -89, 49, -6, 81, 12] 这个解压成字符人串
     * 1、先要将他转为一个赫夫曼的二进制字符串
     * 011000111111010001111110100011111101010001011001100001111010011100110001111110100101000101100
     * 2、赫夫曼编码对应的二进制的字符串 对照 赫夫曼编码 转为 普通字符串
     *
     * @param bytes
     * @return
     */

    /**
     * 解码
     *
     * @param huffmanCode  赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码后压缩的字符节
     * @return 返回原来的字答串对应的字节数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCode, byte[] huffmanBytes) {
        //1、先得到huffmanBytes对应的二进制的字符串如：100110101...
        StringBuilder sb = new StringBuilder();
        //将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            sb.append(byteToBitString(!flag, huffmanBytes[i]));
        }

        //2、将这字答串按照指定赫夫曼编码进行解码
        //2.1、 需要将 huffman编码反射查询，因为现在的huffmanCode变量中保存的是字符对应的赫夫曼表，现在要赫夫曼对应字符串
        Map<String, Byte> huffmanCodeReverse = new HashMap<>();
        for (Map.Entry<Byte, String> item : huffmanCode.entrySet()) {
            huffmanCodeReverse.put(item.getValue(), item.getKey());
        }
        System.out.println(huffmanCode);
        System.out.println(huffmanCodeReverse);
        System.out.println(sb);

        List<Byte> decodes = new ArrayList<>();
        //2.3开始遍历这个二进制字串
        for (int i = 0; i < sb.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            //相当每 10010010
            //      x   count x至count位进行查询是否存在对应的huffmanCode如果存在就拿出来并结束内部循环
            //继续下次循环
            while (flag) {
                b = huffmanCodeReverse.get(sb.substring(i, i + count));
                //找到了对应的赫夫码
                if (b == null) { //没匹配到继续向后匹配
                    count++;
                } else {
                    //匹配到了停止内层循环
                    flag = false;
                }
            }
            //将找到的编码放入数组中
            decodes.add(b);
            //当找到对应的编码后，i下次应该从 i + count开始遍历
            i += count;
        }

        byte[] b = new byte[decodes.size()];
        for (int i = 0; i < decodes.size(); i++) {
            b[i] = decodes.get(i);
        }
        return b;
    }


    /**
     * 将一个byte转为一个二进制字符串
     * 取最后8位即可
     *
     * @param flag 是否需要补位
     * @param b
     * @return
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        }
        return str;
    }


    private static byte[] huffmanZip(byte[] bytes) {
        //生成树节点
        List<HuffmanCodeNode> list = getNodes(bytes);
        //生成赫夫曼树
        HuffmanCodeNode root = createHuffmanTree(list);
        //是否生成了对应的赫夫曼编码
        getCodes(root);
        //将赫夫曼编码压缩成byte数组
        return zip(bytes, huffmanCode);
    }

    /**
     * 将转为赫夫曼编码的数据和赫夫曼表进行压缩成赫夫曼编码
     * 将其转为一个赫夫曼编码后还没有结束，因为他还只是一个字符串，还需要将其转为一个byte[]字节数组这样
     * 才算完成压缩
     * <p>
     * 会返回：011000111111010001111110100011111101010001011001100001111010011100110001111110100101000101100
     * 每一个字节对应的是HuffmanCode编码表中的对应编码
     * <p>
     * 还需要将他们转为Byte
     * huffmanCodeBytes[0] = 10101000(补码) => byte [推导 10101000 => 10101000 - 1 => 10100111(反码)
     * => 11011000 = -88
     *
     * @param bytes
     * @param huffmanCodes
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1、利用HuffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(huffmanCodes.get(b));
        }
        //用于计算将二进制转成一个byte数组的长度
        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] by = new byte[len];

        int index = 0;
        //因为是每8位对应一个byte所以步长为8
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte = null;
            if (i + 8 > sb.length()) {
                strByte = sb.substring(i);
            } else {
                //从之前的赫夫曼编码字符串每次取8位放入byte数组中
                strByte = sb.substring(i, i + 8);
            }
            by[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return by;
    }

    //用于保存拼接叶子节点路径
    static StringBuilder sb = new StringBuilder();
    static Map<Byte, String> huffmanCode = new HashMap<>();


    private static Map<Byte, String> getCodes(HuffmanCodeNode node) {
        if (node == null) {
            return null;
        }
        getCodes(node.left, "0", sb);
        getCodes(node.right, "1", sb);
        return huffmanCode;
    }

    /**
     * 将传入的node节点的所有叶子节点的赫夫曼编码得到，并放入到HuffmanCode变量中
     * 拿到 每个字符对应的赫夫曼编码
     *
     * @param node 传入的节点
     * @param code 左子节点为0 右子节点为1
     * @param sb   用于拼接路径
     */
    private static void getCodes(HuffmanCodeNode node, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);
        if (node != null) { //如果node等于null相当于空节点那么就不处理
            //非叶子节点
            if (node.data == null) {
                //递归处理
                //向左递归
                getCodes(node.left, "0", sb2);
                //向右递归
                getCodes(node.right, "1", sb2);
            } else {
                //表示找到了某个叶子节点
                huffmanCode.put(node.data, sb2.toString());
            }
        }
    }


    //创建赫夫曼树
    private static HuffmanCodeNode createHuffmanTree(List<HuffmanCodeNode> list) {
        while (list.size() > 1) {
            //排序从小到大排序
            Collections.sort(list);
            //取出第一颗最小的二叉树
            HuffmanCodeNode leftNode = list.get(0);
            HuffmanCodeNode rightNode = list.get(1);

            //创建出来的非叶子节点都没有data而只有weight权值 (左子节点权值+右子节点权值）
            HuffmanCodeNode parent = new HuffmanCodeNode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理过的两颗二叉树删除
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
        }
        return list.get(0);
    }

    //统计字符串每个字符出现的次数以及对应的节点对象list
    private static List<HuffmanCodeNode> getNodes(byte[] bytes) {
        List<HuffmanCodeNode> nodes = new ArrayList<>();
        //存储每个byte出现的次数使用map 使用map来统计
        Map<Byte, Integer> map = new HashMap<>();
        //可以得到每个字符出现的次数
        for (Byte b : bytes) {
            map.merge(b, 1, Integer::sum);
        }
        //构建成了一个包含字符和字符出现次数的list
        for (Map.Entry<Byte, Integer> item : map.entrySet()) {
            nodes.add(new HuffmanCodeNode(item.getKey(), item.getValue()));
        }
        return nodes;
    }

}

//创建一个带权值的node
class HuffmanCodeNode implements Comparable<HuffmanCodeNode> {
    Byte data; //保存字符
    int weight; //保存字符出现的次数
    HuffmanCodeNode left;
    HuffmanCodeNode right;

    public HuffmanCodeNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(HuffmanCodeNode node) {
        return this.weight - node.weight;
    }

    @Override
    public String toString() {
        return "Node [data=" + this.data + ", weight=" + this.weight + "]";
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
}