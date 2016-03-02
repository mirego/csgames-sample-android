package com.mirego.sampleapp.data;

import java.util.Date;

public interface DataSource {

    interface Observer {
        void didRefresh();
    }

    void refreshData();

    Date getCurrentDate();

    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
}
