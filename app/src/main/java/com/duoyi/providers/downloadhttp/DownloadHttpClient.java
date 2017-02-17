package com.duoyi.providers.downloadhttp;

import android.content.Context;
import android.util.Pair;

import com.duoyi.downloadmanager.abstracthttp.AbstractHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by duoyi on 2016/10/28.
 */

public class DownloadHttpClient implements AbstractHttpClient {

    private OkHttpClient okHttpClient;
    private Response response;
    public DownloadHttpClient(String userAgent,Context context){
        okHttpClient = new OkHttpClient();
    }
    @Override
    public void execute(String url,ArrayList<Pair<String,String>> headArrayList) throws IOException {
        Request.Builder builder = new Request.Builder().url(url);
        for (Pair<String,String> pair:headArrayList){
            builder.addHeader(pair.first,pair.second);
        }
        response = okHttpClient.newCall(builder.build()).execute();
    }

    @Override
    public InputStream getEntityContent() throws IOException {
        if (response != null){
            return response.body().byteStream();
        }
        return  null;
    }

    @Override
    public int getStatusCode() {
        if (response != null) {
            return response.code();
        }
        return 404;
    }

    @Override
    public String getFirstHeaderValue(String var1) {
        if (response != null){
            return response.header(var1);
        }
        return null;
    }

    @Override
    public void close() {
    }
}
