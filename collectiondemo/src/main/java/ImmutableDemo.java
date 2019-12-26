import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImmutableDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "robbie", "name");
        System.out.println(list);
        List<String> umList = Collections.unmodifiableList(list);
        List<String> stringList = Collections.singletonList("escoffier");

        List<String> list1 = Collections.emptyList();
        list1.add("abc");


    }
}
