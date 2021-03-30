package jdk.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-05-09 09:07
 */
public class ListTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list.subList(0, 5));
        System.out.println(list.subList(5, list.size()));
    }

    //public static void main(String[] args) {
    //    List<String> list = Lists.newArrayList("a", "b", "c");
    //    for (Iterator iter = list.iterator(); iter.hasNext(); ) {
    //        String str = (String)iter.next();
    //        iter.remove();
    //    }
    //    System.out.println(list);
    //
    //    for (int i = 0; i < list.size(); i++) {
    //        System.out.println(list.get(i));
    //        list.remove(list.get(i));
    //        System.out.println(list.get(i));
    //    }
    //    for (String s : list) {
    //        list.remove(s);
    //    }
    //    list.forEach(list::remove);
    //    System.out.println(list.toString());
    //}
}
