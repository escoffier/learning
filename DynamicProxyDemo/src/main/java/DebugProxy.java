import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static java.lang.reflect.Proxy.newProxyInstance;

public class DebugProxy implements InvocationHandler {

    private Object obj;

    ///@Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object result;
        try {
            System.out.println("before method " + method.getName());
            result = method.invoke(obj, objects);

        } catch (Exception ex) {
            throw new RuntimeException("unexpected invocation exception: " +
                    ex.getMessage());
        } finally {
            System.out.println("after method " + method.getName());
        }
        return result;
    }

    public static Object newInstance(Object obj){
        return newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new DebugProxy(obj));
    }

    private DebugProxy(Object obj) {
        this.obj = obj;
    }

    public interface Foo {

        Object move(Object ob);
        String sayHello();
    }

    static public class FooImpl implements Foo {

        public Object move(Object ob) {
            System.out.println("move: " + ob);
            return null;
        }


        public String sayHello() {
            System.out.println("hello robbie");
            return "hello robbie";
        }
    }


    public static void main(String args[]) {
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Foo foo = (Foo)DebugProxy.newInstance(new FooImpl());
        foo.move("robbie");
        foo.sayHello();
    }

}
