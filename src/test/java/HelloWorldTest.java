import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldTest {

    HelloWorld h;

    @Before
    public void setup() {
        h = new HelloWorld();
    }

    @After
    public void teardown() {
        h = null;
    }

    @Test
    public void getHello_whenTrue_returnHello(){
        String s = h.getHello(true);
        assertEquals(s, "Hello World!");
    }

    @Test
    public void getHello_whenFalse_returnNull() {
        String s = h.getHello(false);
        assertNull(s);
    }

}
