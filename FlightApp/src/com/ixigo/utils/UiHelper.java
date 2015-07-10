package com.ixigo.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.widget.TextView;

public class UiHelper {

	private static UiHelper sUiHelper;
	private static Context sContext;
	private CustomTypeFace mRuppeSpan;
	private Typeface mRupeeFont;

	private UiHelper() {
        initializeFont();
    }
	
	private void initializeFont() {
		mRupeeFont = Typeface.createFromAsset(sContext.getAssets(), "font/ITFRupee.ttf");
        mRuppeSpan = new CustomTypeFace(mRupeeFont);
	}

	public static UiHelper getInstance() {
        if (sUiHelper == null) {
            sUiHelper = new UiHelper();
        }
        return sUiHelper;
    }
	
	public void applyRupeeFont(TextView textView, int position) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(textView.getText().toString()).insert(
                position, "R ");
        stringBuilder.setSpan(mRuppeSpan, position, position + 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(stringBuilder);
    }

	public static void initContext(Context context) {
		sContext = context;
	}
}
