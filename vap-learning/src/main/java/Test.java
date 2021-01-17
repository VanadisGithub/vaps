import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * Test
 *
 * @author yy287502@alibaba-inc.com
 * @date 2020/12/15 10:28 上午
 */
public class Test {

    public static void main(String[] args) {
        Integer a = null;
        List<Integer> list = Lists.newArrayList(a);
        if(list.isEmpty()){
            System.out.println(1);
        }
        System.out.println();
        System.out.println(list.get(0));

        List<Integer> list1 = null;
        list1.stream().collect(Collectors.toList());
    }
}
