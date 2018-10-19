package mani.itachi.calender.infiniteviewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class InfiniteViewPager extends ViewPager {

    public static final int OFFSET = 1000;

    private ArrayList<DateTime> datesInMonth;
    private boolean enabled = true;
    private boolean sixWeeksInCalendar = true;
    private int rowHeight = 0;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSixWeeksInCalendar() {
        return sixWeeksInCalendar;
    }

    public ArrayList<DateTime> getDatesInMonth() {
        return datesInMonth;
    }

    public void setDatesInMonth(ArrayList<DateTime> datesInMonth) {
        this.datesInMonth = datesInMonth;
    }

    public void setSixWeeksInCalendar(boolean sixWeeksInCalendar) {
        this.sixWeeksInCalendar = sixWeeksInCalendar;
        rowHeight = 0;
    }

    public InfiniteViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InfiniteViewPager(Context context) {
        super(context);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        setCurrentItem(OFFSET);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
       return enabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return enabled && super.onInterceptTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int rows = datesInMonth.size() / 7;

        if (getChildCount() > 0 && rowHeight == 0) {
            View firstChild = getChildAt(0);
            int width = getMeasuredWidth();

            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,
                    MeasureSpec.EXACTLY);


            firstChild.measure(widthMeasureSpec, MeasureSpec
                    .makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

            rowHeight = firstChild.getMeasuredHeight();
        }

        int calHeight;

        if (sixWeeksInCalendar) {
            calHeight = rowHeight * 6;
        } else {
            calHeight = rowHeight * rows;
        }

        calHeight -= 12;
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(calHeight, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}