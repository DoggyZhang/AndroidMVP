package com.example.androidmvp.utils.common;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.androidmvp.app.App;

public class ToastUtils {

    public static void text(String text) {
        text(text, Toast.LENGTH_SHORT);
    }

    public static void text(String text, int duration) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Toast.makeText(App.getInstance(), text, duration).show();
    }

}
