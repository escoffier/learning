package ClassLoaderDemo;

public class ClassLoader1 {

//    static class B {
//        static int value = 10;
//
//        static {
//            System.out.println("Class B is initialized");
//        }
//    }
//
//    static class A extends B {
//        static {
//            System.out.println("Class A is initialized."); // 不会输出
//        }
//    }

    public static void main(String[] args) {
        System.out.println(SuperClass.value);

        ClassLoader loader = ClassLoader1.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }

        SubClass a = new SubClass();
        a.getClass().getClassLoader();
        ClassLoader cLoader = SuperClass.class.getClassLoader();
        while (cLoader != null) {
            System.out.println(cLoader.toString());
            cLoader = cLoader.getParent();
        }
    }

    }
