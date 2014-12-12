package com.luckypines.android.custombuttontwitterlogin;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;


/**
 * Created by fumiaki on 2014/12/11.
 */
public class MyApplication extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private final static String TWITTER_KEY = "YOUR TWITTER CONSUMER KEY";
    private final static String TWITTER_SECRET = "YOUR TWITTER CONSUMER SECRET";
    @Override
    public void onCreate() {
        super.onCreate();

        final TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new TwitterCore(authConfig));
    }
}
