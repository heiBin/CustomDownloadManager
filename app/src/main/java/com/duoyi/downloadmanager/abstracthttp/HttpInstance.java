package com.duoyi.downloadmanager.abstracthttp;

import android.content.Context;


public class HttpInstance {

    public HttpCreate httpCreate;

    public void setHttpCreate(HttpCreate httpCreate){
        this.httpCreate = httpCreate;
    }


    public AbstractHttpClient newHttpClientInstance(String userAgent, Context context){
        if (httpCreate != null){
            return httpCreate.newHttpClientInstance(userAgent,context);
        }
        return null;
    };

    public static HttpInstance httpInstance;

    public static HttpInstance getInstance(){
        if (httpInstance != null){
            return httpInstance;
        }else{
            httpInstance = new HttpInstance();
            return httpInstance;
        }
    }

}
