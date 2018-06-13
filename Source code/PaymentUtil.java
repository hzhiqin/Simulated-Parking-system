import java.text.SimpleDateFormat;
import java.util.Date;


public class PaymentUtil {

	/**
	 * count parking minutes
	 * 
	 */
	public static int parkTimes(String userNo){
		for(ParkingSpace parkingSpace : Park.CAR_SAPCE){
			if(userNo.equals(parkingSpace.getUserNo())){
				String startTime = parkingSpace.getEnterTime();
				String[] need = startTime.split("/");
				String[]timeAttribute = need[1].split(":");
				String hour = timeAttribute[0];
				String minute = timeAttribute[1];
				Date now = new Date(); 
		    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		    	String nowTime = sdf.format(now);
		    	String[] nowTimeAttr = nowTime.split(":");
		    	return CalPassTime(hour,minute,nowTimeAttr[0],nowTimeAttr[1]);
			}
		}
		return 0;
	}
	
	public static int CalPassTime(String hour,String minute,String x, String y){
		int startTimeNumber = Integer.parseInt(hour) * 60 + Integer.parseInt(minute);
    	int nowTimeNumber = Integer.parseInt(x) * 60 + Integer.parseInt(y);
    	int passTime = nowTimeNumber - startTimeNumber;
    	return passTime;
	}
	
	/**
	 * count the fee custom need pay
	 */
	public static float countFee(int hours){
		if(hours <= 2){
			return (float) 0.5;
		}
		if(hours > 2 && hours <= 4){
			return 1;
		}
		if(hours > 4 && hours <= 8){
			return 2;
		}
		if(hours > 8 && hours <= 12){
			return 3;
		}
		if(hours >12 && hours <= 24){
			return 5;
		}
		return 0;
	}
}