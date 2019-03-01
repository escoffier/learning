import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

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

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer w : list) {
            result.add(f.apply(w));
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            String resut = processFile((BufferedReader br) -> br.readLine() + "\n" + br.readLine() );
            System.out.println(resut);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        List<String> stringList = Arrays.asList("a", "b", "A", "B");
        //stringList.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        stringList.sort(String::compareToIgnoreCase);
        System.out.println(stringList);


        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();
        System.out.println(a1);

        Function<Integer, Apple> c2 = Apple::new;
        Apple a2 = c2.apply(56);
        System.out.println(a2);

        List<Integer> weights = Arrays.asList(12,34,56,21,63);
        List<Apple> appleList = map(weights, Apple::new);
        System.out.println(appleList);

        Comparator<Apple> c = Comparator.comparing(Apple::getWeight).reversed();
        appleList.sort(c);
        System.out.println("------After sorted list");
        System.out.println(appleList);

        BiFunction<String, Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply("red", 77);
        System.out.println(a3);



    }
}
