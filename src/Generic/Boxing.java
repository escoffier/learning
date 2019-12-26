package Generic;

import java.util.Arrays;
import java.util.List;

public class Boxing {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println(sum);

        Integer a = 1;
        Integer b = 1;
        System.out.println(a);
        System.out.println(a == b);
    }

}
