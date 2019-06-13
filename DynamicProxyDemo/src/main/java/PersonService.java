

public class PersonService {
    String firstName;

    public PersonService(String firstName) {
        this.firstName = firstName;
    }

    public String sayHello(String name) {
        sayHi();

        String str = new StringBuilder("Hello ")
                .append(firstName)
                .append(" ")
                .append(name)
                .toString();

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
