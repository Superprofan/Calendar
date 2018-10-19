package mani.itachi.calender.helpers;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;

public class CalendarHelper {

    public static Date convertDateTimeToDate(DateTime dateTime) {
        int year = dateTime.getYear();
        int datetimeMonth = dateTime.getMonthOfYear();
        int day = dateTime.getDayOfMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        calendar.set(year, datetimeMonth - 1, day);

        return calendar.getTime();
    }

}
