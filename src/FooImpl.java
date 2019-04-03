public class FooImpl implements Foo {
    @Override
    public Object move(Object ob) {
        System.out.println("move: " + ob);
        return null;
    }

    @Override
    public String sayHello() {
        System.out.println("hello robbie");
        return "hello robbie";
    }
}
