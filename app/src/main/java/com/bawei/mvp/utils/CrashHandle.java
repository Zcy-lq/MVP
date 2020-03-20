package com.bawei.mvp.utils;

import androidx.annotation.NonNull;

public class CrashHandle implements Thread.UncaughtExceptionHandler {
    public CrashHandle() {
    }
    private static class SingleInstance{
        private static final CrashHandle INSTANCE=new CrashHandle();
    }
    public static CrashHandle getInstance(){
        return SingleInstance.INSTANCE;
    }
    public void init(){
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        e.printStackTrace();
    }
}
