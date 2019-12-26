package Generic;

import java.util.Arrays;
import java.util.List;

public class GenericTypes {

    public static void method(List<String> stringList) {
        System.out.println("invoke method(List<String> stringList) -- " + stringList.toString());
    }

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("hello", " robbie");
        System.out.println(stringList.get(0));
        method(stringList);
    }
}
