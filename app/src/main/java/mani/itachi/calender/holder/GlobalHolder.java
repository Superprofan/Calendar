package mani.itachi.calender.holder;

import android.support.v4.app.Fragment;

public abstract class GlobalHolder extends Fragment {

    abstract void goToToday();

    abstract boolean shouldGoToTodayBeVisible();

    abstract void updateActionBarTitle();

}
