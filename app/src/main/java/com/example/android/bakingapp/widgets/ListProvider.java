package com.example.android.bakingapp.widgets;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dell on 6/19/2017.
 */
public class ListProvider implements RemoteViewsService.RemoteViewsFactory {
    private Set<String> listItemList;
    private Context context = null;

    public ListProvider(Context context) {
        this.context = context;
    }



    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
       listItemList = prefs.getStringSet(context.getString(R.string.pref_ingredients_key), null);
 }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (listItemList != null)
            return listItemList.size();
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /*
    *Similar to getView of Adapter where instead of View
    *we return RemoteViews
    *
    */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.widget_list_item);
        List<String> list = new ArrayList<>(listItemList);
        String listItem = list.get(position);
        remoteView.setTextViewText(R.id.ing_tv, listItem);


        return (remoteView);
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}