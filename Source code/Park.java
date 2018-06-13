import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Park {

	public static int TOTAL_SPACE = 30;
	public static List<ParkingSpace> CAR_SAPCE = new ArrayList<ParkingSpace>();

	static {
		for (int i = 1; i <= TOTAL_SPACE; i++) {
			ParkingSpace parkingSpace = new ParkingSpace();
			parkingSpace.setSpaceNo(i);
			parkingSpace.setPay(false);
			CAR_SAPCE.add(parkingSpace);
		}
	}
	public int parkCar(String cardNo,String nowTime){
		int i = 1;
		for (ParkingSpace parkSpace : Park.CAR_SAPCE) {
            
            if (parkSpace.getUserNo() == null) {
                parkSpace.setUserNo(cardNo);
                parkSpace.setEnterTime(nowTime);
                break;
            }
            i++;
            System.out.println(i);
        }
		if (i == Park.TOTAL_SPACE + 1) {
            JOptionPane.showMessageDialog(null, "The park is full!", "park is full", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
		else{
			return i;
		}
	}
}
