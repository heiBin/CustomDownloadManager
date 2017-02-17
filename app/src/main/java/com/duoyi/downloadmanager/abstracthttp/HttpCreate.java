package com.duoyi.downloadmanager.abstracthttp;

import android.content.Context;


public interface HttpCreate {
    AbstractHttpClient newHttpClientInstance(String userAgent, Context context);
}
