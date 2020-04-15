package com.disruption.cookcentral.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class FavsWidgetRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FavsWidgetRemoteViewsFactory(getApplicationContext());
    }
}
