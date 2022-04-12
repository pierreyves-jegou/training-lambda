package traininglambda.exceptionHandling.forthway;

import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.control.Either;

import java.util.function.Function;

public class FunctionEitherWrapper {

    public static <T, R> Function<T, Either<R, Throwable>> wrap(CheckedFunction1<T, R> checkedFunction1){
        return x -> {
            try {
                R result = checkedFunction1.apply(x);
                return Either.left(result);
            } catch (Throwable e) {
                return Either.right(e);
            }
        };
    }

}
