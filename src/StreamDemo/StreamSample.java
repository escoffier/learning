package StreamDemo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static StreamDemo.DishType.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

public class StreamSample {

    private List<Dish> menu;

    public StreamSample() {
        this.menu = new ArrayList<>();
        menu.add(new Dish(1000, "Beef", MEAT));
        menu.add(new Dish(1289, "meat", MEAT));
        menu.add(new Dish(878, "Bread", OTHER));
        menu.add(new Dish(1348, "chicken", MEAT));
        menu.add(new Dish(987, "Rice", OTHER));
        menu.add(new Dish(878, "prawns", FISH));
        menu.add(new Dish(787, "salmon", FISH));
    }

    public void lowCaloriesDishes( ) {
//        List<Dish> menu = new ArrayList<>();
//        menu.add(new Dish(1000, "Beef"));
//        menu.add(new Dish(1289, "meat"));
//        menu.add(new Dish(878, "Bread"));
//        menu.add(new Dish(1348, "chicken"));
//        menu.add(new Dish(987, "Rice"));

        List<String> names = menu.stream()
                .filter(d -> d.getCalories() < 1000)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(names);

        long howManyDishes = menu.stream().collect(Collectors.counting());

        System.out.println("howManyDishes: " + howManyDishes);

        long howManyDishes1 = menu.stream().count();

        System.out.println("howManyDishes1: " + howManyDishes1);
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

    public void ReducingTest() {
        Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6,7).stream();
        //Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6,7).parallelStream();
        List<Integer> numbers = stream.reduce(
                new ArrayList<Integer>(),

                (List<Integer> l , Integer e) -> {
                    l.add(e);
                    return l;
                },

                (List<Integer> l1, List<Integer> l2) -> {
                    System.out.println(l2.toString());
                    l1.addAll(l2);
                    return l1;
                }
        );

        System.out.println("#################");
        for(Integer d : numbers) {
            System.out.println(d);
        }

    }

    public int getTotalCalories( ) {
        //int totalCalories  = menu.stream().map(Dish::getCalories).reduce(0, (i, j) -> i + j);
        //int totalCalories  = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        int totalCalories  = menu.stream().reduce(0,
                                                  (sum, j) -> sum + j.getCalories(),
                                                   Integer::sum);

        return totalCalories;
    }

    public void groupByType( ) {
        Map<DishType, List<Dish>> dishTypeListMap=  menu.stream().collect(groupingBy(Dish::getDishType));
        System.out.println(dishTypeListMap);

    }

    public static void main(String[] args) {
//        List<Dish> menu = new ArrayList<>();
//        menu.add(new Dish(1000, "Beef", MEAT));
//        menu.add(new Dish(1289, "meat", MEAT));
//        menu.add(new Dish(878, "Bread", OTHER));
//        menu.add(new Dish(1348, "chicken", MEAT));
//        menu.add(new Dish(987, "Rice", OTHER));
//        menu.add(new Dish(878, "prawns", FISH));
//        menu.add(new Dish(787, "salmon", FISH));
        StreamSample sample = new StreamSample();

        sample.lowCaloriesDishes();

        sample.ReducingTest();

//        int totalCalories  = menu.stream().reduce(
//                0,
//                Dish::getCalories,
//                (i, j) -> i + j
//        ));

        //System.out.println("totalCalories: " + totalCalories);

        int totalCalories = sample.getTotalCalories();

        System.out.println("totalCalories: " + totalCalories);

        sample.groupByType();

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
