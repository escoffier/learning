package ClassLoaderDemo;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";

                    InputStream is = getClass().getResourceAsStream(filename);
                    if (is == null) {
                        throw new ClassNotFoundException(name);
                        //return super.loadClass(name);
                    }

                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException ex) {
                    throw new ClassNotFoundException(name);
                }
                //return super.findClass(name);
            }

//            @Override
//            public Class<?> loadClass(String name) throws ClassNotFoundException {
//                try {
//                    String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
//
//                    InputStream is = getClass().getResourceAsStream(filename);
//                    if (is == null) {
//                        return super.loadClass(name);
//                    }
//
//                    byte[] bytes = new byte[is.available()];
//                    is.read(bytes);
//                    return defineClass(name, bytes, 0 , bytes.length);
//                } catch (IOException ex ) {
//                    throw new ClassNotFoundException(name);
//                }
//            }
        };

        Object object = myLoader.loadClass("ClassLoaderDemo.SuperClass");
        System.out.println(object.getClass().getName());
        System.out.println(object instanceof ClassLoaderDemo.SuperClass);
        ClassLoader classLoader = object.getClass().getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader.toString());
            classLoader = classLoader.getParent();
        }

        SuperClass superClass = (SuperClass) object;
        System.out.println("############################");

//        Object superClass1 = new SuperClass();
//        ClassLoader classLoader1 = superClass1.getClass().getClassLoader();
//        while (classLoader1 != null) {
//            System.out.println(classLoader1.toString());
//            classLoader1 = classLoader1.getParent();
//        }

//        Thread thread = new Thread(()->{
//            final ClassLoader classLoader2 = Thread.currentThread().getContextClassLoader();
//            try {
//                Thread.currentThread().setContextClassLoader(myLoader);
//                SuperClass sc = new SuperClass();
//                ClassLoader classLoader3 = sc.getClass().getClassLoader();
//                while (classLoader3 != null) {
//                    System.out.println(classLoader3.toString());
//                    classLoader3 = classLoader3.getParent();
//                }
//            } finally {
//                Thread.currentThread().setContextClassLoader(classLoader2);
//            }
//        });
//        thread.start();
//        thread.join();


        System.out.println(SuperClass.value);
    }
}
