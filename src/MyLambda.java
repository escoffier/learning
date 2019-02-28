import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyLambda {

    public static String processFile(BufferReaderProcessor p)  throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            //return br.readLine();
            return p.process(br);

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        return "error";
    }

    public static void main(String[] args) {
        try {
            String resut = processFile((BufferedReader br) -> br.readLine() + "\n" + br.readLine() );
            System.out.println(resut);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }
}
