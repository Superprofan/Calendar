package mani.itachi.calender.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mani.itachi.calender.R;

public class WeekdayArrayAdapter extends ArrayAdapter<String> {
    LayoutInflater mLayoutInflater;

    public WeekdayArrayAdapter(Context context, int textViewResourceId,
                               List<String> object) {
        super(context, textViewResourceId, object);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /*
        TODO: Update for getting colors through preferences
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) mLayoutInflater.inflate(R.layout.weekday_title, null);
        String item = getItem(position);
        textView.setText(item);
        return textView;
    }

}
