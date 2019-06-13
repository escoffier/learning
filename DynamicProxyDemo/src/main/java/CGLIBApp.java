import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CGLIBApp {

    static class MyMethodInterceptor implements MethodInterceptor {
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("method args: " + Arrays.toString(args));
            System.out.println("method name: " + method.getName());
            return proxy.invokeSuper(obj, args);
            //return proxy.invoke(obj, args);
        }
    }

//    @Test
//    static void beanTest() throws Exception {
//        BeanGenerator beanGenerator = new BeanGenerator();
//        beanGenerator.addProperty("name", String.class);
//        Object myBean = beanGenerator.create();
//        Method setter = myBean.getClass().getMethod("setName", String.class);
//        setter.invoke(myBean, "some string value set by cglib");
//
//        Method getter = myBean.getClass().getMethod("getName");
//        System.out.println(getter.invoke(myBean));
//        //assertEquals()
//        assertEquals("some string value set by cglib", getter.invoke(myBean));
//
//    }

    public static void main(String[] args) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
//        enhancer.setCallback((FixedValue)() -> {
//            System.out.println( "Hello Tom!");
//            //return  "Hello Tom!";
//        });
        enhancer.setCallback(new MyMethodInterceptor());

        PersonService personService = (PersonService) enhancer.create(new Class[]{String.class}, new Object[]{new String("kobe")});

        String res = personService.sayHello("robbie");

        System.out.println("*************************");
        System.out.println("return value: " + res);

//        int len = personService.lengthOfName("escoffier");
//        System.out.println("len is " + len);
//
        System.out.println("*************************");
        personService.sayHi();

    }
}
