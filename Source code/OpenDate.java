
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OpenDate {

    private static String[] PUBLIC_HOLIDAYS = {"01-01", "14-02", "17-03", "21-03", "01-04", "21-04", "01-05", "24-05", "10-06", "29-09", "31-10", "01-11", "05-11", "25-12", "28-12", "15-06", "15-08"};

    /**
     * Whether it is open to the public
     *
     * @param now Date
     * @return true permit custom to park ,false custom do not have access
     */
    public static boolean isPermit(Date now) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int w = cal.get(Calendar.DAY_OF_WEEK);
        if (w == 1 || w == 7) {
            return true;
        }

        @SuppressWarnings("deprecation")
        String year = String.valueOf(now.getYear() + 1900);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String nowTime = sdf.format(now);
        for (String holiday : PUBLIC_HOLIDAYS) {
            holiday += "-";
            holiday += year;
            if (holiday.equals(nowTime)) {
                return true;
            }
        }
        return false;

    }
}
