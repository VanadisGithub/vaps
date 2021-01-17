package jdk.lombda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-05-27 14:47
 */
public class LombdaTest {

    public static void main(String[] args) {

        Interface anInterface = () -> System.out.println(1);
        anInterface.exe();

        Function<Integer, Integer> function = i -> i + i;
        function.apply(1);

        Consumer<Integer> consumer = System.out::println;
        consumer.accept(1);

        Supplier<Integer> supplier = () -> 1 + 1;
        supplier.get();

        Predicate<Integer> predicate = inter -> inter == 1;
        predicate.test(1);

    }

    interface Interface {
        void exe();
    }
}
