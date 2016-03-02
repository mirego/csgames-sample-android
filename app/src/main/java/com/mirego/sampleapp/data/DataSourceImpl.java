package com.mirego.sampleapp.data;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataSourceImpl implements DataSource {

    private final String TIME_API_URL = "http://www.timeapi.org/utc/now";
    private final String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";

    private DateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
    private OkHttpClient client = new OkHttpClient();

    private Date currentDate = null;
    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void refreshData() {
        Request request = new Request.Builder()
                .url(TIME_API_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                currentDate = null;
                notifyObservers();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    currentDate = dateFormat.parse(response.body().string());
                } catch(ParseException e) {
                    currentDate = null;
                }
                notifyObservers();
            }
        });
    }

    @Override
    public Date getCurrentDate() {
        return currentDate;
    }

    @Override
    public void registerObserver(Observer observer)
    {
        observerList.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer)
    {
        observerList.remove(observer);
    }

    private void notifyObservers()
    {
        for (Observer observer : observerList) {
            observer.didRefresh();
        }
    }
}
