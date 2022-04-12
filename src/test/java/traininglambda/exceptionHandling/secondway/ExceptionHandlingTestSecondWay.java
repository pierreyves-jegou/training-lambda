package traininglambda.exceptionHandling.secondway;

import org.junit.jupiter.api.Test;
import traininglambda.exceptionHandling.MyLibWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.*;

/**
 * La deuxième approche encapsule la traitement. L'expression dans le stream redevient plus lisible
 */
public class ExceptionHandlingTestSecondWay {

    @Test
    void secondWay(){
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, -1, 20);

        assertThatThrownBy(() ->

                integers.stream()
                .map(ExceptionHandlingTestSecondWay::doSomething)
                .collect(Collectors.toList())

        ).isInstanceOf(RuntimeException.class);
    }

    private static Integer doSomething(Integer i){
        try {
            return MyLibWriter.writeToFile(i);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }





}
