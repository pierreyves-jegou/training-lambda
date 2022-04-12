package traininglambda.lambda;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FunctionalInterfaceTest {

    @Test
    void myFunctionalInterface(){
        LongFunction longFunction = x -> x * 2;
        IntFunction intFunction  = x -> x * 2;
        MyFirstFunctionalInterface myFirstFunctionalInterface = x -> "toto" + x;
    }

    @Test
    void myAlmostFunctionalInterfaceTest(){
        MyAlmostFunctionalInterface myAlmostFunctionalInterface = x -> "sirop";
    }

}
