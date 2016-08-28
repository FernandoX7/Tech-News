package com.ramirez.fernando.technews.util;

import com.firebase.client.Firebase;

/**
 * Created by fernandoramirez on 8/28/16.
 */

public class MyApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Firebase with the application context. This must happen before the client is used.
        Firebase.setAndroidContext(this);

    }
}
