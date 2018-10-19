package mani.itachi.calender.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import mani.itachi.calender.R;
import mani.itachi.calender.adapter.WeekdayArrayAdapter;
import mani.itachi.calender.helpers.CalendarHelper;
import mani.itachi.calender.infiniteviewpager.InfiniteViewPager;

public class MonthFragment extends Fragment {


    public final static int NUMBER_OF_PAGES = 4;
    public static int
            SUNDAY = 1,
            MONDAY = 2,
            TUESDAY = 3,
            WEDNESDAY = 4,
            THURSDAY = 5,
            FRIDAY = 6,
            SATURDAY = 7;
    private Button leftArrowButton;
    private Button rightArrowButton;
    private TextView monthTitleTextView;
    private GridView weekdayGridView;
    private InfiniteViewPager dateViewPager;
    public MonthFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month_holder, container, false);
        initViews(view);
        setMonthAndYear();
        setWeekDayGridVuew();
        return view;
    }

    void initViews(View view) {
        leftArrowButton = view.findViewById(R.id.calendar_left_arrow);
        rightArrowButton = view.findViewById(R.id.calendar_right_arrow);
        weekdayGridView = view.findViewById(R.id.weekday_gridview);
        monthTitleTextView = view.findViewById(R.id.calendar_month_year_textview);
    }

    private void setWeekDayGridVuew() {

        WeekdayArrayAdapter weekdaysAdapter = getNewWeekdayAdapter();
        weekdayGridView.setAdapter(weekdaysAdapter);

    }

    private WeekdayArrayAdapter getNewWeekdayAdapter() {
            return new WeekdayArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, getDaysOfWeek());
    }

    private ArrayList<String> getDaysOfWeek() {
        ArrayList<String> list = new ArrayList<String>();

        SimpleDateFormat fmt = new SimpleDateFormat("EEE", Locale.getDefault());

        /*
            28th Oct, 2018 is sunday
            My birthday :p
         */
        DateTime nextDay = new DateTime(2018, 10, 28, 0, 0, 0, 0);

        for (int i = 0; i < 7; i++) {
            Date date = CalendarHelper.convertDateTimeToDate(nextDay);
            list.add(fmt.format(date).toUpperCase());
            nextDay = nextDay.plusDays(1);
        }

        return list;
    }

    private void setMonthAndYear() {

        DateTime dateTime = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM, YYYY");
        monthTitleTextView.setText(formatter.print(dateTime));

    }

}
