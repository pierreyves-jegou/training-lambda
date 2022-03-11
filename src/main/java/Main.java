import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20);
        integers.forEach(i -> MyLibWriter.writeToFile(i));
    }



}
