package mani.itachi.calender.helpers;

import android.content.Context;

import org.joda.time.DateTimeZone;
import org.joda.time.DateTime;

import static mani.itachi.calender.helpers.Constants.getNowSeconds;
import static mani.itachi.calender.helpers.Constants.isNotEmpty;

public class Formatter {

    private static final String DAYCODE_PATTERN = "YYYYMMDD";


    public static String getTodayCode(Context context) {
        return Formatter.getDayCodeFromTS(getNowSeconds());
    }

    private static DateTime getDateTimeFromTS(int ts) {
        return new DateTime(ts * 1000L, DateTimeZone.getDefault());
    }

    private static String getDayCodeFromTS(int ts){
        String dayCode = getDateTimeFromTS(ts).toString(DAYCODE_PATTERN);
        if (isNotEmpty(dayCode)) {
            return dayCode;
        }
        return "0";
    }
}
