import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.tools.jcore.ClassFilter;

public class ClassDumpFilter implements ClassFilter {

    public boolean canInclude(InstanceKlass instanceKlass) {
        return instanceKlass.getName().asString().startsWith("com/sun/proxy/$Proxy0");
    }
}
