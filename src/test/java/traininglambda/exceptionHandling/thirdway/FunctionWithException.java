package traininglambda.exceptionHandling.thirdway;

@FunctionalInterface
public interface FunctionWithException<T, R, E extends Exception> {

    R apply(T var1) throws E;

}
