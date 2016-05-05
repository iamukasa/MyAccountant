package com.myaccountant.myaccountant.ChatBot;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;


/**
 * Created by marco.granatiero on 03/10/2014.
 */
public class ChatBotApplication extends Application {

    private static BrainService mService;
    private static boolean mBound = false;

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BrainService.LocalBinder binder = (BrainService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        BrainLogger.setup(this);

        Intent brainIntent = new Intent(this, BrainService.class);
        brainIntent.setAction(BrainService.ACTION_START);
        startService(brainIntent);

        // Bind to LocalService
        bindService(brainIntent, mConnection, Context.BIND_AUTO_CREATE);

Parse.initialize(this, "FHb3jTxKjmhRrNypHc2WLoJFxEvNBtAT5e2giabr", "AcFM7Tbjc8MO51DhduHglicIFf2v4GdaoyVVGWDa");
    }
public static void updateParseInstallation(ParseUser user){
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("userId", user.getObjectId());
        installation.saveInBackground();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    public static boolean isBrainLoaded(){
        if (mBound) {
            return mService.isBrainLoaded();
        }
        return false;
    }
}
