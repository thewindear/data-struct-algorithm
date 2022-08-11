package algorithm;

import java.util.*;
import java.util.stream.Collectors;

//贪心算法，解决选择最少覆盖最多地区的广播站问题
public class GreedyDemo {
    public static void main(String[] args) {
        //生成广播台
        HashMap<String, Set<String>> broadCasts = makeData();
        //保存所有地区不重复
        Set<String> allAreas = broadCasts.values().stream().flatMap(Set::stream).collect(Collectors.toSet());
        //创建ArrayList存放选择的电台集合
        ArrayList<String> selected = new ArrayList<>();

        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前没有覆盖的地区的交集
        Set<String> tmpSet = new HashSet<>();

        //用于保存在一次遍历过程中能够覆盖最多没有覆盖的地区对应的电台的key
        //如果maxKey不等于空会加入到selected中
        String maxKey = "";

        //用来保存上一次for循环的交集数
        int prevEachDiffLen;

        //因为allAreas会不停的减少所以这里是当他的size不等于0时一直循环
        while (allAreas.size() != 0) {
            prevEachDiffLen = 0;
            maxKey = null;
            //不停的进行遍历所有的广播和对应覆盖地区，然后拿到这一轮谁的覆盖地区多就用哪个key
            //相当于和allAreas取一个交集
            for (String key : broadCasts.keySet()) {
                //当选中后直接跳过
                if (selected.contains(key)) {
                    continue;
                }
                //这里当前选择的集合和所有集合拿交集
                tmpSet.clear();
                tmpSet.addAll(broadCasts.get(key));
                //求出两个set集合的交集
                tmpSet.retainAll(allAreas);
                //当前集合和上次的交集集合比较如果比上次大就使用上次的
                //并赋值maxKey和重置上次交集差
                if (tmpSet.size() > prevEachDiffLen) {
                    maxKey = key;
                    prevEachDiffLen = tmpSet.size();
                }
            }
            //prevEachDiffLen不为0表示有选中一个key
            //这key是覆盖地区最大的key
            if (maxKey != null) {
                allAreas.removeAll(broadCasts.get(maxKey));
                selected.add(maxKey);
            }
        }
        System.out.println(Arrays.toString(selected.toArray()));
    }

    public static HashMap<String, Set<String>> makeData() {
        HashMap<String, Set<String>> broadCasts = new HashMap<>();
        Set<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");
        Set<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");
        Set<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");
        Set<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");
        Set<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");
        broadCasts.put("K1", set1);
        broadCasts.put("K2", set2);
        broadCasts.put("K3", set3);
        broadCasts.put("K4", set4);
        broadCasts.put("K5", set5);
        return broadCasts;
    }
}
