import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSample {

    public static void lowCaloriesDishes() {
        List<Dish> menu = new ArrayList<>();
        menu.add(new Dish(1000, "Beef"));
        menu.add(new Dish(1289, "meat"));
        menu.add(new Dish(878, "Bread"));
        menu.add(new Dish(1348, "chicken"));
        menu.add(new Dish(987, "Rice"));

        List<String> names = menu.stream()
                .filter(d -> d.getCalories() < 1000)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(names);
    }

//    public static <T> List<? extends Cloneable> deepCopyList(List<? extends Cloneable > src)
//    {
//        List<T> result = new ArrayList<>();
//        for (T e : src) {
//            result.add(e.clone());
//
//        }
//
//    }

    public static void deepCopy() {
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish(1001, "Beef"));
        dishes.add(new Dish(1282, "meat"));
        dishes.add(new Dish(874, "Bread"));
        dishes.add(new Dish(1338, "chicken"));
        dishes.add(new Dish(982, "Rice"));

        List<Dish> cloneList = dishes.stream().map(Dish::clone).collect(Collectors.toList());
        System.out.println(cloneList);
    }

    public static void streamTest() {
        String[] words = {"Hello", "World"};

    }

    public static void main(String[] args) {
        lowCaloriesDishes();

        //copy test
        Dish d1 = new Dish(1111, "Eggs");
        System.out.println("d1 is " + d1);

        //Dish d2 = d1;
        Dish d2 = (Dish) d1.clone();
        d2.setCalories(1222);
        System.out.println("d1 is " + d1);
        System.out.println("d2 is " + d2);


        deepCopy();
        //Dish d3 = (Dish) d1.clone();
    }
}
