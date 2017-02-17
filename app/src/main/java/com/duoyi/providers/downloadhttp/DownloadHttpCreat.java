package com.duoyi.providers.downloadhttp;

import android.content.Context;

import com.duoyi.downloadmanager.abstracthttp.AbstractHttpClient;
import com.duoyi.downloadmanager.abstracthttp.HttpCreate;

/**
 * Created by duoyi on 2016/10/31.
 */

public class DownloadHttpCreat implements HttpCreate {
    @Override
    public AbstractHttpClient newHttpClientInstance(String userAgent, Context context) {
        return new DownloadHttpClient(userAgent,context);
    }
}
