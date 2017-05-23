package com.project.verbosetech.bustracker;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by a_man on 5/23/2017.
 */

public class BusTrackerApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
