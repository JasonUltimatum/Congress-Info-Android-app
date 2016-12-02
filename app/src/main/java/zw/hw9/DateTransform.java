package zw.hw9;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by et on 11/25/16.
 */

public class DateTransform {
    public static String transform(String input){

        Date d;
        String old ="yyyy-MM-dd";
        String after = "MMM dd,yyyy";
        SimpleDateFormat format1 = new SimpleDateFormat(old);
        SimpleDateFormat format2 = new SimpleDateFormat(after);
        String res =null;
        try{
            d = format1.parse(input);
            res = format2.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }
}
