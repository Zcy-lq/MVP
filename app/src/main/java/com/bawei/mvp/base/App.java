package com.bawei.mvp.base;

import android.app.Application;
import android.content.Context;

import com.bawei.mvp.utils.CrashHandle;


public class App extends Application {
    public static  Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext=getApplicationContext();
        CrashHandle.getInstance().init();
    }
    public static Context getAppContext(){
        return sContext;
    }
}
