package traininglambda.exceptionHandling;

import java.io.IOException;

public class MyLibWriter {

    public static Integer writeToFile(Integer integer) throws IOException {
        if(integer < 0){
            throw new IOException("bim");
        }
        return integer;
    }

}
