

public class PersonService {
    public String sayHello(String name) {
        sayHi();

        String str = "Hello " + name;
        System.out.println("sayHello: " + str );
        return str;
    }

    public void sayHi() {
        System.out.println("hi");
    }

    public int lengthOfName(String name) {
        return name.length();
    }
}
