package com.example.readlightnovel.utils.desa;

import android.util.Size;

public class SizeUtils {
    public SizeUtils() {
    }

    public static Size getSizeFitParent(Size parent, Size child) {
        float parentWidth = (float)parent.getWidth();
        float parentHeight = (float)parent.getHeight();
        float childWidth = (float)child.getWidth();
        float childHeight = (float)child.getHeight();
        float parentRatio = parentWidth / parentHeight;
        float childRatio = childWidth / childHeight;
        float newWidth;
        if (parentRatio < childRatio) {
            newWidth = childHeight * (parentWidth / childWidth);
            return new Size((int)parentWidth, (int)newWidth);
        } else {
            newWidth = childWidth * (parentHeight / childHeight);
            return new Size((int)newWidth, (int)parentHeight);
        }
    }
}
