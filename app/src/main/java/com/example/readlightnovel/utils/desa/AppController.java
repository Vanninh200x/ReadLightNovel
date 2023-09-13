package com.example.readlightnovel.utils.desa;

import android.content.Context;

public class AppController {
    public AppController() {
    }

    public static void setAppTheme(Context context, int theme) {
        DataController.setData(context, "appTheme", theme);
    }

    public static int getAppTheme(Context context) {
        return DataController.getData(context, "appTheme", 0);
    }

    public static boolean isLightTheme(Context context) {
        return getAppTheme(context) == 1;
    }

    public static void setAppColor(Context context, int color) {
        DataController.setData(context, "appColor", color);
    }

    public static int getAppColor(Context context) {
        return DataController.getData(context, "appColor", isLightTheme(context) ? getAppDefaultColor(context) : getAppDefaultColorDark(context));
    }

    public static void setAppDefaultColor(Context context, int color) {
        DataController.setData(context, "appDefaultColor", color);
    }

    public static int getAppDefaultColor(Context context) {
        return DataController.getData(context, "appDefaultColor", ColorUtils.getColor(context, android.R.color.white));
    }

    public static void setAppDefaultColorDark(Context context, int color) {
        DataController.setData(context, "appDefaultColorDark", color);
    }

    public static int getAppDefaultColorDark(Context context) {
        return DataController.getData(context, "appDefaultColorDark", ColorUtils.getColor(context, android.R.color.black));
    }

    public static void setFullScreen(Context context, boolean isEnable) {
        DataController.setData(context, "fullScreen", isEnable);
    }

    public static boolean isFullScreen(Context context) {
        return DataController.getData(context, "fullScreen", false);
    }

    public static void setConfirmExit(Context context, boolean enable) {
        DataController.setData(context, "confirmExit", enable);
    }

    public static boolean isConfirmExit(Context context) {
        return DataController.getData(context, "confirmExit", true);
    }

    public static void setAppPassword(Context context, String password) {
        DataController.setData(context, "appPassword", password);
    }

    public static String getAppPassword(Context context) {
        return DataController.getData(context, "appPassword", "");
    }

    public static void setRatedApp(Context context) {
        DataController.setData(context, "ratedApp", true);
    }

    public static boolean isRatedApp(Context context) {
        return DataController.getData(context, "ratedApp", false);
    }
}
