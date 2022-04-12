package traininglambda.exceptionHandling.thirdway;

import io.vavr.CheckedFunction1;
import org.junit.jupiter.api.Test;
import traininglambda.exceptionHandling.MyLibWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Même philosophie que la 2ième approche mais utilisation :
 * - d'une fonction plus générique
 * - d'une librairie externe
 */
public class ExceptionHandlingTestThirdWay {

    @Test
    void thirdWay() {
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, -1, 20);
        assertThatThrownBy(() ->

                integers.stream()
                .map(wrapper(MyLibWriter::writeToFile))
                .collect(Collectors.toList())

        ).isInstanceOf(RuntimeException.class);
    }

    @Test
    void thirdWayWithVarv() {
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, -1, 20);
        assertThatThrownBy(() ->

                integers.stream()
                .map(CheckedFunction1.of(MyLibWriter::writeToFile).unchecked())
                .collect(Collectors.toList())

        ).isInstanceOf(IOException.class);
    }

    private <T, R, E extends Exception> Function<T, R> wrapper(FunctionWithException<T, R, E> functionWithException) {
        return arg -> {
            try {
                return functionWithException.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }


}
