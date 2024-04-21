package com.lsw.androidutil;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class Handler_parent extends Handler {
    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg.what == 0) {
            Log.e("child thread", "receive msg from main thread");
        }
    }
}
