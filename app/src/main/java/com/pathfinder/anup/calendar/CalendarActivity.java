package com.pathfinder.anup.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.pathfinder.anup.dailyaction.DailyActivityScreen;
import com.pathfinder.anup.imptodo.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by Anup on 2/13/2017.
 */

public class CalendarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);

        HashSet<Date> event = new HashSet<>();

        event.add(new Date());

        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(event);

        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(CalendarActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });

        cv.setClickHandler(new CalendarView.GridClickHandler() {
            @Override
            public void onGridClickListener(Date date) {
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(CalendarActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CalendarActivity.this, DailyActivityScreen.class));
               //
            }
        });


    }
}
