//import sun.jvm.hotspot.tools.jcore.ClassFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);

    //private static List<Integer> arrayList =new ArrayList<Integer>(1,2,3,4,5);
    //private static List<Integer> integerList = Collections.emptyList();
    public static void main(String[] args) {
        //ClassFilter classFilter = new ClassDumpFilter();
        System.out.println("Hello World!");
        int index = findElement(1);
        System.out.println(index);
        int find = Collections.binarySearch(integerList, 4);
        System.out.println(find);

        int a = 0, b = 9;
        int mid = (a + b) >> 1;
        System.out.println(mid);

        System.out.println(integerList instanceof ArrayList);
        //System.out.println(integerList instanceof );

        //integerList = Arrays.asList(1,2,3,4,5);//new ArrayList<>(5);
    }

    public static int findElement(Integer element) {
        int index = 0;

        while (!(element.equals(integerList.get(index))) && integerList.size() > ++index) ;

        return index < integerList.size() ? index : -1;
    }
}
