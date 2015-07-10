package com.ixigo.utils;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class CustomTypeFace extends TypefaceSpan {

    private final Typeface newTypeFace;

    public CustomTypeFace(Typeface type) {
        super("");
        newTypeFace = type;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        applyCustomTypeFace(ds, newTypeFace);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeFace(paint, newTypeFace);
    }

    private static void applyCustomTypeFace(Paint paint, Typeface newTypeFace) {
        int oldStyle;
        Typeface oldTypeFace = paint.getTypeface();
        if (oldTypeFace == null) {
            oldStyle = 0;
        } else {
            oldStyle = oldTypeFace.getStyle();
        }

        int fake = oldStyle & ~newTypeFace.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }
        paint.setTypeface(newTypeFace);
    }
}