public class PersionSerivce {
    public String sayHello(String name) {

        String str = "Hello " + name;
        System.out.println("sayHello: " + str );
        return str;
    }

    public int lengthOfName(String name) {
        return name.length();
    }
}
