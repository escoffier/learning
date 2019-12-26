package ClassLoaderDemo;

public class SubClass extends SuperClass {

    static {
        System.out.println("SubClass is initialized");
    }
}
