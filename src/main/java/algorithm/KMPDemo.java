package algorithm;

import java.util.Arrays;

public class KMPDemo {
    public static void main(String[] args) {
        //System.out.println(violenceMatch("BBC ABCDAB ABCDABCDABDE", "ABCDABD"));
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        //首先拿到一个字符串（子串的部分匹配表）
        System.out.println(Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index + " + index);

    }

    //kmp搜索算法

    /**
     * @param str1 原字符串
     * @param str2 要搜索的字符串
     * @param next kmp表
     * @return 返回查找到的下标 -1 否则返回第一个匹配的下标
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理str1.charAt(i) != str2.charAt(j) 去调整j的大小
            //kmp算法核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            //如果j遍历到了str2的长度一样证明找到了
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        //如果字符中长度为0那么他的next就一定是0
        next[0] = 0; //如果字符串长度1部分匹配表肯定为0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //要判断当dest.charAt(i) != dest.charAt(j) 不相等的情况下 需要向前重新获取[j-1]直接到找到
            //dest.charAt(i) == dest.charAt(j)才退出 一直找不找那么j为0
            //相当于将j回溯
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //当这个条件满足时 部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }


//    public static int kMPMatch() {
//
//    }

    //暴力匹配
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0; //相当于索引指向s1
        int j = 0; //索引指向s2
        //匹配不越界
        while (i < s1Len && j < s2Len) {
            //匹配字符成功
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i + (j - 1);
                j = 0;
            }
        }
        if (j == s2Len) {
            return i - j;
        }
        return -1;
    }

}
