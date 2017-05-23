package gustavo.brilhante.listusers.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Gustavo on 05/05/17.
 */

public class DateUtils {

    public static String getCurrentDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static boolean checkDifferenceTimeInHours(String dateStoredStr, int timeHour){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date dateStored = dateFormat.parse(dateStoredStr);
            Date currentTime = new Date();
            long passedTime = (currentTime.getTime() - dateStored.getTime())/1000;
            long passedMinutes = passedTime/60;
            long passedHours = passedTime/360;
            if(passedHours>=timeHour)return true;
            else return false;

        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean checkDifferenceTimeInMinutes(String dateStoredStr, int timeMinute){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date dateStored = dateFormat.parse(dateStoredStr);
            Date currentTime = new Date();
            long passedTime = (currentTime.getTime() - dateStored.getTime())/1000;
            long passedMinutes = passedTime/60;
            if(passedMinutes>=timeMinute)return true;
            else return false;

        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static String getAgeFromDate(String bornDateStr){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        try {
            dob.setTime(dateFormat.parse(bornDateStr));
            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                age--;
            }

            Integer ageInt = new Integer(age);
            String ageS = ageInt.toString();
            return ageS;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";

    }

    public static String getFormattedDateStr(String date){
        String result = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat resultFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dateResult = dateFormat.parse(date);
            result = resultFormat.format(dateResult);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
