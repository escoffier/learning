import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

public class NIOExample {

    public static void main(String[] args)
    {
//        try {
//            Selector selector = Selector.open();
//
//        } catch (IOException e) {
//
//        }

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




    }
}
