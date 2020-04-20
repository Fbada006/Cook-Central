package com.disruption.cookcentral.widget

import android.content.Intent
import android.widget.RemoteViewsService

class FavsWidgetRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return FavsWidgetRemoteViewsFactory(applicationContext)
    }
}