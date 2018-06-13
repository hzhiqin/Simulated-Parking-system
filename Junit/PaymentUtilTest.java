import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PaymentUtilTest {
	private static PaymentUtil pa = new PaymentUtil();
	Park par = new Park();

	@Before
	public void setUp() throws Exception {
	}

	@SuppressWarnings("static-access")
	@Test
	public void testCalPassTime() {
		assertEquals(781,pa.CalPassTime("10", "37", "23", "38"),0.0);
	}
	
	@Test
	public void testPark() {
		par.parkCar("1","1");
		par.parkCar("2","2");
		int i = 0;
		for (ParkingSpace parkingSpace : Park.CAR_SAPCE) {
            if (null == parkingSpace.getUserNo()) {
                i++;
            }
        }
		assertEquals(28,i,0.0);
	}

	@SuppressWarnings({ "static-access" })
	@Test
	public void testCountFee() {
		assertEquals(0.5,pa.countFee(1),0.0);
	}
	@SuppressWarnings("static-access")
	@Test
	public void testCountFee1(){
		assertEquals(1,pa.countFee(3),0.0);
	}
	@SuppressWarnings("static-access")
	@Test
	public void testCountFee2(){
		assertEquals(2,pa.countFee(6),0.0);
	}
	@SuppressWarnings("static-access")
	@Test
	public void testCountFee3(){
		assertEquals(3,pa.countFee(10),0.0);
	}
	@SuppressWarnings("static-access")
	@Test
	public void testCountFee4(){
		assertEquals(5,pa.countFee(15),0.0);
	}

}
