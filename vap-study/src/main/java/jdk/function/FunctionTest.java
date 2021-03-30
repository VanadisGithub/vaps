package jdk.function;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * @program: van
 * @description:
 * @author: 遥远
 * @create: 2020-05-27 14:47
 */
public class FunctionTest {

    public static void main(String[] args) {

        Interface anInterface = () -> System.out.println(1);
        anInterface.exe();

        //接收T对象，返回R对象
        Function<Integer, Integer> function = i -> i + i;
        function.apply(1);

        //接收T对象，不返回值
        Consumer<Integer> consumer = System.out::println;
        consumer.accept(1);

        //提供T对象（例如工厂），不接收值
        Supplier<Integer> supplier = () -> 1 + 1;
        supplier.get();

        //接收T对象并返回boolean
        Predicate<Integer> predicate = i -> i == 1;
        predicate.test(1);

        //接收T对象，返回T对象
        UnaryOperator<Integer> unaryOperator = i -> i;
        unaryOperator.apply(1);

        BinaryOperator<Integer> binaryOperator = BinaryOperator.maxBy((i1, i2) -> -1);

        System.out.println(binaryOperator.apply(1,2));

    }

    interface Interface {
        void exe();
    }
}
