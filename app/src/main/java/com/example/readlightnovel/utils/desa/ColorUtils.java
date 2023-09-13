package com.example.readlightnovel.utils.desa;

import android.content.Context;
import android.os.Build;

public class ColorUtils {
    public ColorUtils() {
    }

    public static int getColor(Context context, int resourceId) {
        return Build.VERSION.SDK_INT >= 23 ? context.getColor(resourceId) : context.getResources().getColor(resourceId);
    }
}
