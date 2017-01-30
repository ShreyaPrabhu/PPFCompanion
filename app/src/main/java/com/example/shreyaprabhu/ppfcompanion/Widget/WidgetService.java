package com.example.shreyaprabhu.ppfcompanion.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Shreya Prabhu on 30-01-2017.
 */

public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {

        WidgetDataProvider widgetDataProvider = new WidgetDataProvider(
                getApplicationContext(), intent);
        return widgetDataProvider;
    }

}
