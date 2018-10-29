package mani.itachi.calender.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.RemoteViews;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import mani.itachi.calender.R;

public class MonthCalendarWidget extends AppWidgetProvider {

    private static final String ACTION_PREVIOUS_MONTH = "mani.itachi.ACTION_PREVIOUS_MONTH";
    private static final String ACTION_NEXT_MONTH = "mani.itachi.ACTION_NEXT_MONTH";
    private static final String ACTION_RESET_MONTH = "mani.itachi.ACTION_RESET_MONTH";

    private static final String PREF_MONTH = "month";
    private static final String PREF_YEAR = "year";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        final String action = intent.getAction();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        if (ACTION_PREVIOUS_MONTH.equalsIgnoreCase(action)) {
            Calendar calendar = Calendar.getInstance();
            int currMonth = sp.getInt(PREF_MONTH, calendar.get(Calendar.MONTH));
            int currYear = sp.getInt(PREF_YEAR, calendar.get(Calendar.YEAR));
            //Set year month and date
            calendar.set(currYear, currMonth, 1);
            calendar.add(Calendar.MONTH, -1);
            sp.edit()
                    .putInt(PREF_MONTH, calendar.get(Calendar.MONTH))
                    .putInt(PREF_YEAR, calendar.get(Calendar.YEAR))
                    .apply();
            redrawWidgets(context);
        } else if (ACTION_NEXT_MONTH.equalsIgnoreCase(action)) {
            Calendar calendar = Calendar.getInstance();
            int currMonth = sp.getInt(PREF_MONTH, calendar.get(Calendar.MONTH));
            int currYear = sp.getInt(PREF_YEAR, calendar.get(Calendar.YEAR));
            //Set year month and date
            calendar.set(currYear, currMonth, 1);
            calendar.add(Calendar.MONTH, 1);
            sp.edit()
                    .putInt(PREF_MONTH, calendar.get(Calendar.MONTH))
                    .putInt(PREF_YEAR, calendar.get(Calendar.YEAR))
                    .apply();
            redrawWidgets(context);
        } else if (ACTION_RESET_MONTH.equalsIgnoreCase(action)) {
            sp.edit().remove(PREF_MONTH).remove(PREF_YEAR).apply();
            redrawWidgets(context);
        }

    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int appWidgetId : appWidgetIds) {
            drawWidget(context, appWidgetId);
        }

    }

    private void drawWidget(Context context, int appWidgetId) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        Resources res = context.getResources();
        Bundle widgetOptions = appWidgetManager.getAppWidgetOptions(appWidgetId);
        boolean shortMonthName = false;
        boolean mini = false;
        int numWeeks = 6;
        if (widgetOptions != null) {
            int minWidthDp = widgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
            int minHeightDp = widgetOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
            shortMonthName = minWidthDp <= res.getInteger(R.integer.max_width_short_month_label_dp);
            mini = minHeightDp <= res.getInteger(R.integer.max_height_mini_view_dp);
            if (mini) {
                numWeeks = minHeightDp <= res.getInteger(R.integer.max_height_mini_view_1_row_dp) ? 1 : 2;
            }
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.month_widget);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);
        int month;
        if (!mini) {
            month = sp.getInt(PREF_MONTH, calendar.get(Calendar.MONTH));
            calendar.set(sp.getInt(PREF_YEAR, calendar.get(Calendar.YEAR)), month, 1);
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        rv.setTextViewText(R.id.month_label, DateFormat.format(shortMonthName ? "MMM yy" : "MMMM yyyy", calendar));

        if (!mini) {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, 1 - calendar.get(Calendar.DAY_OF_WEEK));
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, 1 - calendar.get(Calendar.DAY_OF_WEEK));
        }

        rv.removeAllViews(R.id.calendar);

        RemoteViews headerRowRv = new RemoteViews(context.getPackageName(), R.layout.row_header);

        DateFormatSymbols dfs = DateFormatSymbols.getInstance();
        String[] weekdays = dfs.getShortWeekdays();
        for (int _day = Calendar.SUNDAY; _day <= Calendar.SATURDAY; _day++) {
            RemoteViews dayRv = new RemoteViews(context.getPackageName(), R.layout.cell_header);
            dayRv.setTextViewText(R.id.text1, weekdays[_day]);
            headerRowRv.addView(R.id.row_container, dayRv);
        }
        rv.addView(R.id.calendar, headerRowRv);

        for (int week = 0; week < numWeeks; week++) {
            RemoteViews rowRv = new RemoteViews(context.getPackageName(), R.layout.row_week);
            for (int _day = 0; _day < 7; _day++) {
                boolean inMonth = calendar.get(Calendar.MONTH) == month;
                boolean inYear = calendar.get(Calendar.YEAR) == year;
                boolean isToday = inYear && inMonth && (calendar.get(Calendar.DAY_OF_YEAR) == _day);

                boolean isFirstOfMonth = calendar.get(Calendar.DAY_OF_MONTH) == 1;
                int cellLayoutResId = R.layout.cell_day;
//                if (isToday) {
//                    cellLayoutResId = R.layout.cell_today;
//                } else if (inMonth) {
//                    cellLayoutResId = R.layout.cell_day_this_month;
//                }
                RemoteViews cellRv = new RemoteViews(context.getPackageName(), cellLayoutResId);
                cellRv.setTextViewText(R.id.text1,
                        Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
                if (isFirstOfMonth) {
                    cellRv.setTextViewText(R.id.month_label, DateFormat.format("MMM", calendar));
                }
                rowRv.addView(R.id.row_container, cellRv);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            rv.addView(R.id.calendar, rowRv);
        }

        rv.setViewVisibility(R.id.prev_month_button, mini ? View.GONE : View.VISIBLE);
        rv.setOnClickPendingIntent(R.id.prev_month_button,
                PendingIntent.getBroadcast(context, 0,
                        new Intent(context, MonthCalendarWidget.class)
                                .setAction(ACTION_PREVIOUS_MONTH), PendingIntent.FLAG_UPDATE_CURRENT));

        rv.setViewVisibility(R.id.next_month_button, mini ? View.GONE : View.VISIBLE);
        rv.setOnClickPendingIntent(R.id.next_month_button,
                PendingIntent.getBroadcast(context, 0,
                        new Intent(context, MonthCalendarWidget.class)
                                .setAction(ACTION_NEXT_MONTH),
                        PendingIntent.FLAG_UPDATE_CURRENT));

        rv.setOnClickPendingIntent(R.id.month_label,
                PendingIntent.getBroadcast(context, 0,
                        new Intent(context, MonthCalendarWidget.class)
                                .setAction(ACTION_RESET_MONTH),
                        PendingIntent.FLAG_UPDATE_CURRENT));
        rv.setViewVisibility(R.id.month_bar, numWeeks <= 1 ? View.GONE : View.VISIBLE);

        appWidgetManager.updateAppWidget(appWidgetId, rv);

    }

    private void redrawWidgets(Context context) {
        int[] appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(
                new ComponentName(context, MonthCalendarWidget.class));
        for (int appWidgetId : appWidgetIds) {
            drawWidget(context, appWidgetId);
        }
    }

}
