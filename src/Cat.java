
public class Cat extends Animal {
    public static void printName() {
        System.out.println("The static method in Cat");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Cat");
    }

    public static void main(String[] args) {
        Cat myCat = new Cat();
        Animal myAnimal = myCat;
        Animal.printName();
        myAnimal.testInstanceMethod();
        myAnimal.printName();
        myCat.printName();
    }
}