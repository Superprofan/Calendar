package mani.itachi.calender.holder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import mani.itachi.calender.helpers.Formatter;
import mani.itachi.calender.interfaces.NavigationListner;

import static mani.itachi.calender.helpers.Constants.DAY_CODE;

public class MonthFragmentHolder extends GlobalHolder implements NavigationListner {

    private int PREFILLED_MONTHS = 251;

    private ViewPager viewPager;
    private int defaultMonthlyPage = 0;
    private String todayDayCode = "";
    private String currentDayCode = "";
    private boolean isGoToTodayVisible = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDayCode=(getArguments()!=null)?getArguments().getString(DAY_CODE):"";
        todayDayCode = Formatter.getTodayCode(getContext());
    }

    @Override
    void goToToday() {

    }

    @Override
    boolean shouldGoToTodayBeVisible() {
        return false;
    }

    @Override
    void updateActionBarTitle() {

    }

    @Override
    public void goLeft() {

    }

    @Override
    public void goRight() {

    }

    @Override
    public void gotoDateTime() {

    }
}
