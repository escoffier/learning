

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class MapTest {

    private static void DataStreamTest() {

        try {

            DataOutputStream out = new DataOutputStream( new BufferedOutputStream(new FileOutputStream("data")));

             final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
             final int[] units = { 12, 8, 13, 29, 50 };
             final String[] descs = {
                    "Java T-shirt",
                    "Java Mug",
                    "Duke Juggling Dolls",
                    "Java Pin",
                    "Java Key Chain"
            };

            for (int i = 0; i < prices.length; i++) {
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descs[i]);
            }

            out.flush();

            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("data")));

            double price;
            int unit;
            String desc;
            double total = 0.0;

            try {
                while (true){
                    price = in.readDouble();
                    unit = in.readInt();
                    desc = in.readUTF();
                    System.out.format("You ordered %d" + " units of %s at $%.2f%n",
                            unit, desc, price);
                    total += price * unit;

                }
            } catch (EOFException ex) {

            }

            out.close();
            in.close();


        } catch (IOException ex) {
            System.out.println("DataOutputStream err");
        }



    }

    public  static void main(String[] argv) {
        Map<String, Integer> mymap = new HashMap<>();
        HashMap<String, Integer> hashMap = (HashMap)mymap;

        mymap.put("robbie", 1);

        String filePath = new String("data.txt");
        try {
            //FileInputStream fileInputStream = new FileInputStream(filePath);

            //BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            //BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, "utf-8"), 1* 1024* 1024);

            BufferedReader reader = new BufferedReader(new FileReader("data.txt"), 1* 1024* 1024);

            String line;
            int lineNO = 1;
            while ( (line = reader.readLine()) != null){

                System.out.printf("line %d: %s \n", lineNO++, line);
            }


            reader.close();
        } catch (FileNotFoundException ex){
            System.out.println("File Not Found");
        } catch (UnsupportedEncodingException ex) {
            System.out.println("UnsupportedEncodingException");
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }

        DataStreamTest();

    }
}
