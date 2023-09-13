package com.example.readlightnovel.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.readlightnovel.R;
import com.example.readlightnovel.utils.Const;

public class CircleTextView extends AppCompatImageView {
    private Paint circlePaint;
    private Paint textPaint;
    private String text;

    public CircleTextView(Context context) {
        super(context);
        init(context);
    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // Initialize the paints for circle and text
        circlePaint = new Paint();
        circlePaint.setColor(context.getColor(R.color.color_sign_left));
        circlePaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(150f);

        // Set default text
        text = "va";
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Set the dimensions to ensure a circular shape
        int size = Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Calculate the radius of the circle
        int radius = Math.min(width, height) / 3;

        // Calculate the center coordinates
        int centerX = width / 2;
        int centerY = height / 2;

        // Draw the circle
        canvas.drawCircle(centerX, centerY, radius, circlePaint);

        // Calculate the text bounds
        Rect textBounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), textBounds);

        // Calculate the text coordinates to center it within the circle
        int textX = centerX - textBounds.width() / 2;
        int textY = centerY + textBounds.height() / 2;

        // Draw the text
        canvas.drawText(text, textX, textY, textPaint);
    }

    public void setText(String text) {
        this.text = text;
        invalidate(); // Trigger a redraw of the view
    }
}
