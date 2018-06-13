import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class FeeManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		 Bill bill = new Bill();
         bill.setCardNo("jb2012");
         bill.setTotalFee(3);
         FeeManager.BILL_LIST.add(bill);
         assertEquals(true,FeeManager.BILL_LIST.contains(bill));
	}
	
}
