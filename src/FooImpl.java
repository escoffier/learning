public class FooImpl implements Foo {
    @Override
    public Object move(Object ob) {
        System.out.println("move: " + ob);
        return null;
    }
}
