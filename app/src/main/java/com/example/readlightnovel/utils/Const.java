package com.example.readlightnovel.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

public class Const {

    public static void applyContrast(View view, int value) {
        String colorCode = "ee";
        switch (value) {
            case 0:
                colorCode = "ee";
                break;
            case 1:
                colorCode = "dd";
                break;
            case 3:
                colorCode = "cc";
                break;
            case 4:
                colorCode = "bb";
                break;
            case 5:
                colorCode = "aa";
                break;
            case 6:
                colorCode = "99";
                break;
            case 7:
                colorCode = "88";
                break;
            case 8:
                colorCode = "77";
                break;
            case 9:
                colorCode = "66";
                break;
            case 10:
                colorCode = "55";
                break;
            case 11:
                colorCode = "44";
                break;
            case 12:
                colorCode = "33";
                break;
            case 13:
                colorCode = "22";
                break;
            case 14:
                colorCode = "11";
                break;
            case 15:
                colorCode = "00";
                break;
        }
        view.setBackgroundColor(Color.parseColor("#" + colorCode + "000000"));
    }

}
