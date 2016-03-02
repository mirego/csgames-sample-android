package com.mirego.sampleapp.data;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private RefreshCallback refreshCallback = null;

    @Override
    public void refreshData() {
        Request request = new Request.Builder()
                .url(TIME_API_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                currentDate = null;
                refreshCallback();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    currentDate = dateFormat.parse(response.body().string());
                } catch(ParseException e) {
                    currentDate = null;
                }
                refreshCallback();
            }
        });
    }

    @Override
    public Date getCurrentDate() {
        return currentDate;
    }

    @Override
    public void setRefreshCallback(RefreshCallback refreshCallback)
    {
        this.refreshCallback = refreshCallback;
    }

    private void refreshCallback()
    {
        if (refreshCallback != null) {
            refreshCallback.onRefreshFinished();
        }
    }
}
