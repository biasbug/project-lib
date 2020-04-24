package utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateUtils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String dateToString(Date date){
        return simpleDateFormat.format(date);
    }
}
