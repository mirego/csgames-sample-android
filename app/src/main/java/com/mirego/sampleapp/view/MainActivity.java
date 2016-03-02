package com.mirego.sampleapp.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.mirego.sampleapp.R;
import com.mirego.sampleapp.data.DataSource;
import com.mirego.sampleapp.data.DataSourceImpl;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    private final long TIMER_INTERVAL = 1000;

    private DataSource dataSource = new DataSourceImpl();
    private Timer timer = new Timer();

    @Bind(R.id.week_day_text_view)
    TextView weekDayTextView;

    @Bind(R.id.day_text_view)
    TextView dayTextView;

    @Bind(R.id.month_text_view)
    TextView monthTextView;

    @Bind(R.id.time_text_view)
    TextView timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        dataSource.registerObserver(new DataSource.Observer() {
            @Override
            public void didRefresh() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        displayDate(dataSource.getCurrentDate());
                    }
                });
            }
        });

        startTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        cancelTimer();
    }

    @Override
    public void onResume() {
        super.onResume();
        startTimer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    private void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                dataSource.refreshData();
            }
        }, 0, TIMER_INTERVAL);
    }

    private void cancelTimer() {
        timer.cancel();
        timer.purge();
    }

    private void displayDate(Date date) {
        weekDayTextView.setText(DateFormat.format("EEEE", date));
        dayTextView.setText(DateFormat.format("dd", date));
        monthTextView.setText(DateFormat.format("MMMM", date));
        timeTextView.setText(DateFormat.format("HH'h' mm'm' ss's'", date));
    }
}
