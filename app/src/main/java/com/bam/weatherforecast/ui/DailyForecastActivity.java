package com.bam.weatherforecast.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bam.weatherforecast.R;
import com.bam.weatherforecast.adapters.DayAdapter;
import com.bam.weatherforecast.weather.Day;

import java.util.Arrays;

/* Custom List View */
public class DailyForecastActivity extends ListActivity {

    private Day[] mDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

        DayAdapter adapter = new DayAdapter(this, mDays);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String dayOfTheWeek = mDays[position].getDayOfTheWeek();
        String condition = mDays[position].getSummary();
        String highTemp = String.valueOf(mDays[position].getTemperatureMax());
        String message = String.format("On %s the hight will be %s and it will be %s",
                dayOfTheWeek, highTemp, condition);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
