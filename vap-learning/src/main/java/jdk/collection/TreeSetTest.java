package jdk.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-06-01 21:10
 */
public class TreeSetTest {

    public static void main(String[] args) {
        Map map;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(2);
        set.add(1);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
