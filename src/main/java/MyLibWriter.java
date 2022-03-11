import java.io.IOException;

public class MyLibWriter {

    static void writeToFile(Integer integer) throws IOException {
        if(integer < 0){
            throw new IOException("bim");
        }else{
            System.out.println(integer);
        }
    }

}
