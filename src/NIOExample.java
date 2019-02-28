import java.io.IOException;
import java.nio.channels.Selector;

public class NIOExample {

    public static void main(String[] args)
    {
        try {
            FileInputStream inputStream = new FileInputStream("data.txt");
            MappedByteBuffer buffer = inputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, inputStream.available());

            byte[] data = new byte[30];

            while (true) {
                buffer.get(data);

                System.out.println("get data: "  + new String(data));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");

        } catch (BufferUnderflowException ex){
            System.out.println("BufferUnderflowException");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try {

        }



    }
}
