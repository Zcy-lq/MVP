package com.bawei.mvp.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.zhaochenyu1709a.R;

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean check, int l, int t, int r, int b) {

        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        //拿到子数量
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                childAt.measure(0, 0);

                int measuredHeight = childAt.getMeasuredHeight();
                int measuredWidth = childAt.getMeasuredWidth();

                right = left + measuredWidth;
                if (right > getMeasuredWidth()) {
                    left = 0;
                    right = left + measuredWidth;
                    top = bottom + 20;
                }

                bottom = top + measuredHeight;
                childAt.layout(left, top, right, bottom);
                left += measuredWidth + 20;

            }
        }

    }

    public void addChildView(String title) {
        TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(8, 0, 8, 0);
        textView.setTextColor(Color.BLACK);
        textView.setText(title);
        addView(textView);
    }


}
