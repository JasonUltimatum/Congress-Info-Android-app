package zw.hw9;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by et on 11/25/16.
 */

public class DateTransform {
    public static String transform(String input){
        String res =null;
        Date d =null;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("MMM DD,yyyy");
        try{
            d = format1.parse(input);
            res = format2.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }
}
