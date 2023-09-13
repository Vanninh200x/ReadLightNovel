package com.example.readlightnovel.utils.desa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Build;

import androidx.fragment.app.FragmentManager;

public class DialogUtils {
    public DialogUtils() {
    }

    @SuppressLint("ResourceType")
    public static Dialog getNewDialog(Activity activity) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, 16974125);
        } else {
            dialog = new Dialog(activity, 16974124);
            if (Build.VERSION.SDK_INT >= 21) {
                dialog.getWindow().addFlags(Integer.MIN_VALUE);
                if (AppController.isLightTheme(activity)) {
                    dialog.getWindow().setStatusBarColor(-1);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(8192);
                } else {
                    dialog.getWindow().setStatusBarColor(AppController.getAppColor(activity));
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = 16973827;
        dialog.getWindow().setSoftInputMode(2);
        return dialog;
    }

    public static Dialog getNewDialog(Activity activity, int animation) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, 16974125);
        } else {
            dialog = new Dialog(activity, 16974124);
            if (Build.VERSION.SDK_INT >= 21) {
                dialog.getWindow().addFlags(Integer.MIN_VALUE);
                if (AppController.isLightTheme(activity)) {
                    dialog.getWindow().setStatusBarColor(-1);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(8192);
                } else {
                    dialog.getWindow().setStatusBarColor(AppController.getAppColor(activity));
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = animation;
        dialog.getWindow().setSoftInputMode(2);
        return dialog;
    }

    public static Dialog getNewDialog(Activity activity, boolean isShowKeyboard) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, 16974125);
        } else {
            dialog = new Dialog(activity, 16974124);
            if (Build.VERSION.SDK_INT >= 21) {
                dialog.getWindow().addFlags(Integer.MIN_VALUE);
                if (AppController.isLightTheme(activity)) {
                    dialog.getWindow().setStatusBarColor(-1);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(8192);
                } else {
                    dialog.getWindow().setStatusBarColor(AppController.getAppColor(activity));
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = 16973827;
        dialog.getWindow().setSoftInputMode(isShowKeyboard ? 4 : 2);
        return dialog;
    }

    public static Dialog getNewDialog(Activity activity, boolean isShowKeyboard, boolean isLightTheme) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, 16974125);
        } else {
            dialog = new Dialog(activity, 16974124);
            if (Build.VERSION.SDK_INT >= 21) {
                dialog.getWindow().addFlags(Integer.MIN_VALUE);
                if (isLightTheme) {
                    dialog.getWindow().setStatusBarColor(-1);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(8192);
                } else {
                    dialog.getWindow().setStatusBarColor(AppController.getAppColor(activity));
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = 16973827;
        dialog.getWindow().setSoftInputMode(isShowKeyboard ? 4 : 2);
        return dialog;
    }

    public static Dialog getNewDialog(Activity activity, boolean isShowKeyboard, int statusBarColor) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, 16974125);
        } else {
            dialog = new Dialog(activity, 16974124);
            if (Build.VERSION.SDK_INT >= 21) {
                dialog.getWindow().addFlags(Integer.MIN_VALUE);
                if (AppController.isLightTheme(activity)) {
                    dialog.getWindow().setStatusBarColor(statusBarColor);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(8192);
                } else {
                    dialog.getWindow().setStatusBarColor(statusBarColor);
                }
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = 16973827;
        dialog.getWindow().setSoftInputMode(isShowKeyboard ? 4 : 2);
        return dialog;
    }

    public static Dialog getNewDialogPhotoVideoBrowser(Activity activity) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, 16974125);
        } else {
            dialog = new Dialog(activity, 16974124);
            if (Build.VERSION.SDK_INT >= 21) {
                dialog.getWindow().addFlags(Integer.MIN_VALUE);
                dialog.getWindow().setStatusBarColor(-16777216);
            }
        }

        dialog.getWindow().getAttributes().windowAnimations = 16973827;
        dialog.getWindow().setSoftInputMode(2);
        return dialog;
    }

    public static Dialog getNewDialogFullScreenDark(Activity activity, int statusBarColor) {
        Dialog dialog = new Dialog(activity, 16974125);
        if (Build.VERSION.SDK_INT >= 21) {
            dialog.getWindow().addFlags(Integer.MIN_VALUE);
            dialog.getWindow().setStatusBarColor(statusBarColor);
        }

        dialog.getWindow().getAttributes().windowAnimations = 16973827;
        dialog.getWindow().setSoftInputMode(2);
        return dialog;
    }

    public static Dialog getNewDialogHideKeyboard(Activity activity) {
        Dialog dialog;
        if (ScreenUtils.isFullScreen(activity)) {
            dialog = new Dialog(activity, 16974125);
        } else {
            dialog = new Dialog(activity, 16974124);
        }

        dialog.getWindow().setSoftInputMode(2);
        return dialog;
    }

    public static boolean enableShowDialogFragment(FragmentManager fragmentManager, String tagDialogFragment) {
        return fragmentManager.findFragmentByTag(tagDialogFragment) == null;
    }
}
