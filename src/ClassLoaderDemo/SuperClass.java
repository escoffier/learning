package ClassLoaderDemo;

public class SuperClass {

    static int value = 10;
    String name = "robbie";

    static {
        System.out.println("SuperClass is initialized");
    }
}
