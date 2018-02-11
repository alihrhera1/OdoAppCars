package com.Inspira.odo.mainLuncher;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.Inspira.odo.data.Model.Response;
import com.Inspira.odo.helper.LocaleHelper;

import java.util.ArrayList;

public class MyApplication extends Application {

     @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
        MultiDex.install(newBase);
    }




    @Override
    public void onCreate() {
        super.onCreate();
    }
}
