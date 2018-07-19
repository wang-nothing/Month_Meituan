package com.example.admin.month_meituan.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.month_meituan.R;

public class MyTitle extends LinearLayout{
    private ImageView image_sss;
    private TextView et_text;
    public MyTitle(Context context) {
        this(context, null);
    }

    public MyTitle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTitle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.mysearchview,this);
        initViews();
    }

    private void initViews() {
        image_sss = findViewById(R.id.image_sss);
        et_text = findViewById(R.id.et_text);
    }

}
