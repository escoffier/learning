import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class App {

    static class MyMethodInterceptor implements MethodInterceptor {
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("Your name is " + Arrays.toString(args));
            return proxy.invokeSuper(obj, args);
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
        enhancer.setSuperclass(PersionSerivce.class);
//        enhancer.setCallback((FixedValue)() -> {
//            System.out.println( "Hello Tom!");
//            //return  "Hello Tom!";
//        });
        enhancer.setCallback(new MyMethodInterceptor());

        PersionSerivce proxy = (PersionSerivce) enhancer.create();

        String res = proxy.sayHello("robbie");
        System.out.println(res);

        int len = proxy.lengthOfName("escoffier");
        System.out.println("len is " + len);

        //beanTest();



    }
}
