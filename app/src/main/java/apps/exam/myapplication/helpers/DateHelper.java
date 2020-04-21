package apps.exam.myapplication.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by msaycon on 21,Apr,2020
 */
public class DateHelper {

    public static Date getDate(String publishDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        try {
            return simpleDateFormat.parse(publishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(System.currentTimeMillis());
    }

    public static String getPublishedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy hh:mm a", Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
