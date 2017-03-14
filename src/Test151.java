import java.lang.reflect.*;

interface Interface {
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements Interface {
    public static int countSomething = 0;
    public static int countElse = 0;
    public void doSomething(){
        System.out.println( "doSomething");
        countSomething++;
    }
    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
        countElse++;
    }
}

class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;
    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }
    public Object
    invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() +
                ". method: " + method + ", args: " + args);
        //System.out.println(proxy.toString());
        if(args != null)
            for(Object arg : args)
                System.out.println(" " + arg);
        return method.invoke(proxied, args);
    }
}

public class Test151 {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);

        Interface proxy = (Interface)Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{ Interface.class },
                new DynamicProxyHandler(real));
        consumer(proxy);
        System.out.println("doSomething调用了" + RealObject.countSomething);
        System.out.println("somethingElse调用了" + RealObject.countElse);
    }
}