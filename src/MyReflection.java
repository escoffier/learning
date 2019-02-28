import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyReflection {

    public static void main(String[] args){

        Animal animal = new Animal();

        Class<?> acls = animal.getClass();
        System.out.println(acls.getName());

        try {
            Class<?> animalClass = Class.forName("Animal");
            Constructor<?> constructor = animalClass.getConstructor();

            Animal animal1 = (Animal)constructor.newInstance();
            System.out.println("animal name: " + animal1.getName());

            Method[] methods = animalClass.getMethods();
            for (Method method: methods) {
                System.out.println("method name: " + method.getName());
            }

        } catch (ClassNotFoundException ex) {

            System.out.println(ex.getMessage());

        } catch (NoSuchMethodException ex) {
            System.out.println(ex.getMessage());
        } catch (ReflectiveOperationException ex) {
            System.out.println(ex.getMessage());
        }


    }
}
