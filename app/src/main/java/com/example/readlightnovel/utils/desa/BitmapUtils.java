package com.example.readlightnovel.utils.desa;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.FloatRange;
import java.io.IOException;

public class BitmapUtils {
    public BitmapUtils() {
    }

    public static Bitmap createBitmapSquare(Bitmap srcBmp) {
        Bitmap dstBmp;
        if (srcBmp.getWidth() >= srcBmp.getHeight()) {
            dstBmp = Bitmap.createBitmap(srcBmp, srcBmp.getWidth() / 2 - srcBmp.getHeight() / 2, 0, srcBmp.getHeight(), srcBmp.getHeight());
        } else {
            dstBmp = Bitmap.createBitmap(srcBmp, 0, srcBmp.getHeight() / 2 - srcBmp.getWidth() / 2, srcBmp.getWidth(), srcBmp.getWidth());
        }

        return dstBmp;
    }

    public static Bitmap createBitmapSquare(Activity activity, Bitmap bitmapOrigin, int divideScreenWidth) {
        Bitmap newBitmap = ThumbnailUtils.extractThumbnail(bitmapOrigin, ScreenUtils.getScreenWidth(activity) / divideScreenWidth, ScreenUtils.getScreenWidth(activity) / divideScreenWidth);
        return newBitmap;
    }

    public static Bitmap createBitmapCircle(Bitmap srcBmp) {
        Bitmap bitmapOrigin = createBitmapSquare(srcBmp);
        Bitmap bitmap = ThumbnailUtils.extractThumbnail(bitmapOrigin, bitmapOrigin.getWidth(), bitmapOrigin.getHeight());
        Bitmap output;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);
        int color = -12434878;
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        float r = 0.0F;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = (float)(bitmap.getHeight() / 2);
        } else {
            r = (float)(bitmap.getWidth() / 2);
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap createBitmapCircle(Activity activity, Bitmap bitmapOrigin, int divideScreenWidth) {
        Bitmap bitmap = ThumbnailUtils.extractThumbnail(bitmapOrigin, ScreenUtils.getScreenWidth(activity) / divideScreenWidth, ScreenUtils.getScreenWidth(activity) / divideScreenWidth);
        Bitmap output;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);
        int color = -12434878;
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        float r = 0.0F;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = (float)(bitmap.getHeight() / 2);
        } else {
            r = (float)(bitmap.getWidth() / 2);
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap createBitmapCircle(Bitmap bitmapOrigin, int width, int height) {
        Bitmap bitmap = ThumbnailUtils.extractThumbnail(bitmapOrigin, width, height);
        Bitmap output;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);
        int color = -12434878;
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        float r = 0.0F;
        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = (float)(bitmap.getHeight() / 2);
        } else {
            r = (float)(bitmap.getWidth() / 2);
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap createBitmapWithNewSize(Context context, int resourceId, int newSize) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inInputShareable = true;
            options.inPurgeable = true;
            BitmapFactory.decodeResource(context.getResources(), resourceId, options);
            if (options.outWidth != -1 && options.outHeight != -1) {
                int originalSize = options.outHeight > options.outWidth ? options.outHeight : options.outWidth;
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = originalSize / newSize;
                return BitmapFactory.decodeResource(context.getResources(), resourceId, opts);
            }

            return null;
        } catch (Exception var6) {
            var6.printStackTrace();
        } catch (OutOfMemoryError var7) {
            var7.printStackTrace();
        }

        return null;
    }

    public static Bitmap createBitmapRightOrientation(String imgPath) {
        try {
            Bitmap originalBitmap = BitmapFactory.decodeFile(imgPath);
            int rotation = getExifOrientation(imgPath);
            if (rotation != 0) {
                Matrix matrix = new Matrix();
                matrix.postRotate((float)rotation);
                Bitmap exifBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true);
                originalBitmap.recycle();
                return exifBitmap;
            }

            return originalBitmap;
        } catch (Exception var5) {
            var5.printStackTrace();
        } catch (OutOfMemoryError var6) {
            var6.printStackTrace();
        }

        return null;
    }

    public static Bitmap createBitmapRightOrientation(String imagePath, int newSize) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inInputShareable = true;
            options.inPurgeable = true;
            BitmapFactory.decodeFile(imagePath, options);
            if (options.outWidth != -1 && options.outHeight != -1) {
                int originalSize = options.outHeight > options.outWidth ? options.outHeight : options.outWidth;
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = originalSize / newSize;
                Bitmap originalBitmap = BitmapFactory.decodeFile(imagePath, opts);
                int rotation = getExifOrientation(imagePath);
                if (rotation != 0) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate((float)rotation);
                    Bitmap exifBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true);
                    originalBitmap.recycle();
                    return exifBitmap;
                }

                return originalBitmap;
            }

            return null;
        } catch (Exception var9) {
            var9.printStackTrace();
        } catch (OutOfMemoryError var10) {
            var10.printStackTrace();
        }

        return null;
    }

    public static int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;

        try {
            exif = new ExifInterface(filepath);
        } catch (IOException var4) {
        }

        if (exif != null) {
            int orientation = exif.getAttributeInt("Orientation", -1);
            if (orientation != -1) {
                switch (orientation) {
                    case 3:
                        degree = 180;
                        break;
                    case 6:
                        degree = 90;
                        break;
                    case 8:
                        degree = 270;
                }
            }
        }

        return degree;
    }

    public static Bitmap createBitmapFromDrawable(Drawable drawable) {
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() > 0 && drawable.getIntrinsicHeight() > 0) {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int width, int height) {
        return ThumbnailUtils.extractThumbnail(bitmap, width, height);
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        view.draw(canvas);
        return bitmap;
    }

    public static Bitmap getBitmapFromImageView(ImageView imageView) {
        return ((BitmapDrawable)imageView.getDrawable()).getBitmap();
    }

    public static Bitmap decodeResource(Context context, int resourceId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        return BitmapFactory.decodeResource(context.getResources(), resourceId, options);
    }

    public static Bitmap flipBitmap(Bitmap bitmap, boolean isHorizontal) {
        Matrix matrix = new Matrix();
        if (isHorizontal) {
            matrix.setScale(-1.0F, 1.0F);
            matrix.postTranslate((float)bitmap.getWidth(), 0.0F);
        } else {
            matrix.setScale(1.0F, -1.0F);
            matrix.postTranslate(0.0F, (float)bitmap.getHeight());
        }

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap overlayBitmapToCenter(Bitmap bitmap1, Bitmap bitmap2) {
        int bitmap1Width = bitmap1.getWidth();
        int bitmap1Height = bitmap1.getHeight();
        int bitmap2Width = bitmap2.getWidth();
        int bitmap2Height = bitmap2.getHeight();
        float marginLeft = (float)((double)bitmap1Width * 0.5 - (double)bitmap2Width * 0.5);
        float marginTop = (float)((double)bitmap1Height * 0.5 - (double)bitmap2Height * 0.5);
        Bitmap overlayBitmap = Bitmap.createBitmap(bitmap1Width, bitmap1Height, bitmap1.getConfig());
        Canvas canvas = new Canvas(overlayBitmap);
        canvas.drawBitmap(bitmap1, new Matrix(), (Paint)null);
        canvas.drawBitmap(bitmap2, marginLeft, marginTop, (Paint)null);
        return overlayBitmap;
    }

    public static Bitmap addGradient(Bitmap src, int color1, int color2) {
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0.0F, 0.0F, (Paint)null);
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0.0F, 0.0F, 0.0F, (float)h, color1, color2, TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawRect(0.0F, 0.0F, (float)w, (float)h, paint);
        return result;
    }

    public static Bitmap changeBitmapContrastBrightness(Bitmap bmp, @FloatRange(from = 0.0,to = 10.0) float contrast, @FloatRange(from = -255.0,to = 255.0) float brightness) {
        ColorMatrix cm = new ColorMatrix(new float[]{contrast, 0.0F, 0.0F, 0.0F, brightness, 0.0F, contrast, 0.0F, 0.0F, brightness, 0.0F, 0.0F, contrast, 0.0F, brightness, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F});
        Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        Canvas canvas = new Canvas(ret);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bmp, 0.0F, 0.0F, paint);
        return ret;
    }

    public static Bitmap scaleBitmapKeepAspectRatio(Bitmap bitmap, int width) {
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();
        float newHeight = (float)imageHeight * (float)width / (float)imageWidth;
        return Bitmap.createScaledBitmap(bitmap, width, (int)newHeight, false);
    }

    public static Bitmap changeOpacity(Bitmap bitmap, int alpha) {
        Bitmap transBmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(transBmp);
        Paint paint = new Paint();
        paint.setAlpha(alpha);
        canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint);
        return transBmp;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        } else {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }
    }
}
