package com.example.readlightnovel.utils.desa;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import androidx.annotation.IntRange;

public class BitmapEffectUtils {
    public BitmapEffectUtils() {
    }

    public static Bitmap getBitmapEffect(Context context, Bitmap bitmapOriginal, int effect) {
        if (effect == 0) {
            return bitmapOriginal;
        } else if (effect == 1) {
            return toBlackWhite(bitmapOriginal);
        } else if (effect == 5) {
            return toSepia(bitmapOriginal);
        } else {
            return toBlur(context, bitmapOriginal);
        }
    }

    public static Bitmap toBlackWhite(Bitmap bitmapOriginal) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0F);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Bitmap bitmap = Bitmap.createBitmap(bitmapOriginal.getWidth(), bitmapOriginal.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bitmapOriginal, 0.0F, 0.0F, paint);
        return bitmap;
    }

    public static Bitmap toBlur(Context context, Bitmap bitmapOriginal, @IntRange(from = 1L, to = 25L) int blurRadius) {
        Bitmap bitmap = Bitmap.createBitmap(bitmapOriginal.getWidth(), bitmapOriginal.getHeight(), Bitmap.Config.ARGB_8888);
        bitmap.setDensity(bitmapOriginal.getDensity());
        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation allIn = Allocation.createFromBitmap(rs, bitmapOriginal);
        Allocation allOut = Allocation.createFromBitmap(rs, bitmap);
        blurScript.setRadius((float) blurRadius);
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);
        allOut.copyTo(bitmap);
        rs.destroy();
        return bitmap;
    }

    public static Bitmap toBlur(Context context, Bitmap bitmapOriginal) {
        return toBlur(context, bitmapOriginal, 20);
    }


    public static Bitmap toSepia(Bitmap bitmapOriginal) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0F);
        ColorMatrix colorScale = new ColorMatrix();
        colorScale.setScale(1.0F, 1.0F, 0.8F, 1.0F);
        colorMatrix.postConcat(colorScale);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        Bitmap bitmap = Bitmap.createBitmap(bitmapOriginal.getWidth(), bitmapOriginal.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bitmapOriginal, 0.0F, 0.0F, paint);
        return bitmap;
    }


    public static Bitmap toNegative(Bitmap bitmap) {
        float[] negative_colour_matrix = new float[]{-1.0F, 0.0F, 0.0F, 0.0F, 255.0F, 0.0F, -1.0F, 0.0F, 0.0F, 255.0F, 0.0F, 0.0F, -1.0F, 0.0F, 255.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F};
        ColorMatrix colorMatrix = new ColorMatrix(negative_colour_matrix);
        return getBitmapFromColorMatrix(colorMatrix, bitmap);
    }

    private static Bitmap getBitmapFromColorMatrix(ColorMatrix colorMatrix, Bitmap bitmap) {
        Bitmap ret = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(ret);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint);
        return ret;
    }

    public static Bitmap toColor(Bitmap bitmapOriginal, int color) {
        Paint paint = new Paint();
        PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        paint.setColorFilter(colorFilter);
        Bitmap bitmap = Bitmap.createBitmap(bitmapOriginal.getWidth(), bitmapOriginal.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bitmapOriginal, 0.0F, 0.0F, paint);
        return bitmap;
    }
}
