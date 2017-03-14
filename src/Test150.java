import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// Using reflection to show all the methods of a class.
// even if the methods are defined in the base class.
public class Test150 {

    public static void main(String[] args) {
        try {
            Class  c = Class.forName("Test150");
            Test150 test150 = (Test150) c.newInstance();
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();
        } catch (ClassNotFoundException e) {
            System.out.println("No such Class: " + e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}