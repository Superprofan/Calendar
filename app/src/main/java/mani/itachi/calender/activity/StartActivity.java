package mani.itachi.calender.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import mani.itachi.calender.R;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
}
