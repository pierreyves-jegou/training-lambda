package traininglambda.exceptionHandling.firstway;

import org.junit.jupiter.api.Test;
import traininglambda.exceptionHandling.MyLibWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Cet premiere approche n'est pas idéale car l'expression lambda devient vite complexe à lire
 */
public class ExceptionHandlingTestFirstWay {

    @Test
    void firstWay(){
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, -1, 20);
        assertThatThrownBy(() ->

        integers.stream().map(i -> {
            try {
                return MyLibWriter.writeToFile(i);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())


        ).isInstanceOf(RuntimeException.class);
    }
}
