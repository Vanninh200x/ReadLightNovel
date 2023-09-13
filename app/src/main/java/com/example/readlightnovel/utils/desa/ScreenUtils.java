package com.example.readlightnovel.utils.desa;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;

public class ScreenUtils {
    public ScreenUtils() {
    }

    public static boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) != 0;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
        return displaymetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
        return displaymetrics.heightPixels;
    }

    public static int getRealScreenWidth(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17) {
            Point size = new Point();
            activity.getWindowManager().getDefaultDisplay().getRealSize(size);
            return size.x;
        } else {
            DisplayMetrics displaymetrics = activity.getResources().getDisplayMetrics();
            return displaymetrics.widthPixels;
        }
    }

    public static int getRealScreenHeight(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17) {
            Point size = new Point();
            activity.getWindowManager().getDefaultDisplay().getRealSize(size);
            return size.y;
        } else {
            DisplayMetrics displaymetrics = activity.getResources().getDisplayMetrics();
            return displaymetrics.heightPixels;
        }
    }
}
