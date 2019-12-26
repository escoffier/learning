import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;

public class NIOExample {

    public static void main(String[] args) {
        //testBuffer();
        if (args.length < 1) {
            System.out.println("Need file name");
            return;
        }
        copyfile(args[0]);
        //testMappedBuffer();
    }

    public static void directBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
    }

    public static void testMappedBuffer() {
        try {
            FileInputStream inputStream = new FileInputStream("data");
            MappedByteBuffer buffer = inputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, inputStream.available());

            byte[] data = new byte[300];

            while (true) {
                buffer.position();
                buffer.get(data);

                System.out.println("get data: " + new String(data));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");

        } catch (BufferUnderflowException ex) {
            System.out.println("BufferUnderflowException");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void testBuffer() {
        try (FileInputStream inputStream = new FileInputStream("data")) {

            //inputStream.available();
            int bufferSize = inputStream.available();
            System.out.println("file size: " + bufferSize);
            ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
            //byteBuffer.flip();
            int readBytes = inputStream.getChannel().read(byteBuffer);
            System.out.println("read " + readBytes + " from FileInputStream");

            ByteBuffer newByteBuffer = ByteBuffer.allocate(byteBuffer.capacity() * 2);
            //newByteBuffer.flip();
            byteBuffer.flip();
            newByteBuffer.put(byteBuffer);
            System.out.println("newByteBuffer position: " + newByteBuffer.position());

            byteBuffer.rewind();
            byte[] data = new byte[byteBuffer.limit()];
            System.out.println("remaining size: " + byteBuffer.remaining());
            byteBuffer.get(data, 0, byteBuffer.limit());

            String str = new String(data, StandardCharsets.UTF_8);
            System.out.println("data: " + str);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void copyfile(String fileName) {
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            FileChannel fileChannel = inputStream.getChannel();

            FileOutputStream outputStream = new FileOutputStream("out_" + fileName);
            FileChannel fileChannel1 = outputStream.getChannel();

            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }


            fileChannel.transferTo(0, fileChannel.size(), fileChannel1);
            fileChannel1.close();
            outputStream.close();

//            try {
//                Thread.sleep(40*1000);
//            }  catch (InterruptedException ex) {
//                System.out.println(ex.getMessage());
//            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
