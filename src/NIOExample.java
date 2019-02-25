import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
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
            RandomAccessFile randomAccessFile = new RandomAccessFile("test.txt", "rw");

            FileChannel fileChannel = randomAccessFile.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int bytesRead = fileChannel.read(buffer);

            while (bytesRead > 0) {
                buffer.flip();

                byte[] dataArr = new byte[bytesRead];
                buffer.get(dataArr);
                System.out.println("readed data: " + new String(dataArr));
                bytesRead = fileChannel.read(buffer);

            }

        } catch (FileNotFoundException ex) {
            System.out.println("not found file");
        } catch (IOException ex)
        {
            System.out.println("IOException: " + ex.getStackTrace());
        }



    }
}
