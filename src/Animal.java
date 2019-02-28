public class Animal {

    private String name = "None";

    public static void printName()
    {
        System.out.println("Animal name");
    }

    public void testInstanceMethod() {
        System.out.println("The instance method in Animal");
    }


    public String getName(){
        return name;
    }
}


