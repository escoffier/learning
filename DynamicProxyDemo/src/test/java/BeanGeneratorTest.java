import net.sf.cglib.beans.BeanGenerator;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class BeanGeneratorTest {

    @Test
     public void beanTest() throws Exception {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("name", String.class);
        Object myBean = beanGenerator.create();
        Method setter = myBean.getClass().getMethod("setName", String.class);
        setter.invoke(myBean, "some string value set by cglib");

        Method getter = myBean.getClass().getMethod("getName");
        System.out.println(getter.invoke(myBean));
        //assertEquals()
        assertEquals("some string value set by cglib", getter.invoke(myBean));

    }
}
