package com.example.androidmvp.utils.common;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


/**
 * Created by 阿飞 on 2017/4/10.
 */

public class ActivityUtil {
    public static void startActivity(Context context, Class<?> activity) {
        startActivity(context, activity, null);
    }

    public static void startActivity(Context context, Class<?> activity, Bundle extras) {
        try {
            if (context == null) {
                throw new RuntimeException("Start Activity fail, context is null");
            }
            if (activity == null) {
                throw new ActivityNotFoundException("Start Activity fail, target Activity is null");
            }
            Intent intent = new Intent();
            intent.setClass(context, activity);
            if (extras != null) {
                intent.putExtras(extras);
            }

            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "Open Fail.", Toast.LENGTH_SHORT).show();
            if (LogUtil.isDebug) {
                LogUtil.e(e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
