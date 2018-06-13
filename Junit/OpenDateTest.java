
import java.util.Calendar;
import java.util.Date;
import junit.framework.TestCase;
public class OpenDateTest extends TestCase {
    
    public OpenDateTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsPermit() {
        System.out.println("isNowPermit");
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        boolean expResult = true;
        boolean result = OpenDate.isPermit(now);
        assertEquals(expResult, result);
    }
    
    public void testIsPermitSec() {
        System.out.println("isPubHoliPermit");
        System.out.println("Take '02-14' as a example");
        Date pub = new Date(1994,1,14);
        boolean expResult = true;
        boolean result = OpenDate.isPermit(pub);
        assertEquals(expResult, result);
    }
}
