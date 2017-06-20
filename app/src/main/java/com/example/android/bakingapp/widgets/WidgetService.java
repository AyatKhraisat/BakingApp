package com.example.android.bakingapp.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by dell on 6/19/2017.
 */

public class WidgetService extends RemoteViewsService {
/*
* So pretty simple just defining the Adapter of the listview
* here Adapter is ListProvider
* */

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return (new ListProvider(this.getApplicationContext()));
    }

}