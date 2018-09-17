package mani.itachi.calender;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

public class CalenderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
