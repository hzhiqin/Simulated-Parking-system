
import junit.framework.TestCase;

public class ParkingSpaceTest extends TestCase {
    
    public ParkingSpaceTest(String PS) {
        super(PS);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSetSpaceNo() {
        System.out.println("setSpaceNo");
        int spaceNo = 2012212812;
        ParkingSpace instance = new ParkingSpace();
        instance.setSpaceNo(spaceNo);
        assertEquals(spaceNo,instance.getSpaceNo()); 
    }

    public void testSetUserNo() {
        System.out.println("setUserNo");
        String userNo = "120720776";
        ParkingSpace instance = new ParkingSpace();
        instance.setUserNo(userNo);
        assertEquals(userNo,instance.getUserNo());
    }

    public void testSetEnterTime() {
        System.out.println("setEnterTime");
        String enterTime = "1994-03-19";
        ParkingSpace instance = new ParkingSpace();
        instance.setEnterTime(enterTime);
        assertEquals(enterTime,instance.getEnterTime());
    }

    public void testIsPay() {
        System.out.println("isPay");
        ParkingSpace instance = new ParkingSpace();
        instance.setPay(true);
        boolean expResult = true;
        boolean result = instance.isPay();
        assertEquals(expResult, result);
    }

    public void testSetPay() {
        System.out.println("setPay");
        ParkingSpace instance = new ParkingSpace();
        boolean isPay = true;
        instance.setPay(isPay);
        assertEquals(true,instance.isPay());
    }
    
}
