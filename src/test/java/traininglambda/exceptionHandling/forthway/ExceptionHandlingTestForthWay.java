package traininglambda.exceptionHandling.forthway;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import traininglambda.exceptionHandling.MyLibWriter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Cette 4ième approche permet de wrapper les exceptions de façon générique et de poursuivre le traitement
 *  - soit uniquement sur les exceptions
 *  - soit uniquement sur les résultats valides
 *  - ou les 2
 *
 *  Ex :
 *  - Je réalise avec plusieurs stream / lambdas des validations de format et je souhaite fournir l'ensemble des erreurs à l'utilisateur
 *  - Je souhaite poursuivre le traitement même en cas d'exceptions et fournir les erreurs dans les logs
 *
 *
 */
public class ExceptionHandlingTestForthWay {

    @Test
    void forthWay() {
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, -1, 20);
        List<Either<Integer, Throwable>> collect = integers.stream()
                .map(FunctionEitherWrapper.wrap(MyLibWriter::writeToFile))
                .collect(Collectors.toList());
        System.out.println(collect);
    }


}
