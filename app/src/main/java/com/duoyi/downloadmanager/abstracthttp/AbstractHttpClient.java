package com.duoyi.downloadmanager.abstracthttp;


import android.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public interface AbstractHttpClient {

    void execute(String url, ArrayList<Pair<String, String>> headArrayList) throws IOException;
    void close();

    int getStatusCode();
    String getFirstHeaderValue(String var1);
    InputStream getEntityContent() throws IOException;

}
