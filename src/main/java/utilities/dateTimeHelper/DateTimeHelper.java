package utilities.dateTimeHelper;



import org.apache.commons.exec.OS;

import java.time.LocalDateTime;

public class DateTimeHelper {

    public static String getCurrentDateTimeByNopcommerceFormat(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String dayOfWeek = capitalizeString(localDateTime.minusHours(13).getDayOfWeek().toString());
        String month = capitalizeString(localDateTime.minusHours(13).getMonth().toString());
        int dayOfMonth = localDateTime.minusHours(13).getDayOfMonth();
        String dayOfMonthString = convertIntDayToString(dayOfMonth);
        int year = localDateTime.minusHours(13).getYear();
        return dayOfWeek + ", " + month + " " + dayOfMonthString + ", " + year;
    }

    public static String getMonth(String date){
        String number = date.replaceAll("\\D", "");
        String m = "";
        String mm = number.substring(2, 4);
        if (mm.charAt(0) == '0'){
            m = mm.substring(1);
        } else {m = mm;}
        return m;
    }

    public static String getDay(String date){
        String number = date.replaceAll("\\D", "");
        String d = "";
        String dd = number.substring(0, 2);
        if (dd.charAt(0) == '0'){
            d = dd.substring(1);
        } else {d = dd;}
        return d;
    }

    public static String convertToUSDateFormat(String date){
        String number = date.replaceAll("\\D", "");
        String m = "";
        String d = "";
        String dd = number.substring(0, 2);
        if (dd.charAt(0) == '0'){
            d = dd.substring(1);
        } else {d = dd;};
        String mm = number.substring(2, 4);
        if (mm.charAt(0) == '0'){
            m = mm.substring(1);
        } else {m = mm;}
        String yyyy = number.substring(4);

        return m+"/"+d+"/"+yyyy;
    }

    private static String convertIntDayToString(int day){
        if(day <= 9){
            return "0" + day;
        } else
            return String.valueOf(day);
    }

    private static String capitalizeString(String str){
        return str.charAt(0) + str.toLowerCase().substring(1);
    }

    public static void main(String[] args) {
        System.out.println(convertToUSDateFormat("06/01/1992"));
    }


}
