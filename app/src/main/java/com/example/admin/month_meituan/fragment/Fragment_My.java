package com.example.admin.month_meituan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.month_meituan.R;
import com.example.admin.month_meituan.my.login.view.LoginActivity;

public class Fragment_My extends Fragment implements View.OnClickListener {
    private final int REQUEST_CODE = 1;
    private View view;
    private ImageView my_photo;
    private TextView my_login,my_reg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my, null);
        initViews();
        return view;
    }

    private void initViews() {
        my_photo = view.findViewById(R.id.my_photo);
        my_login = view.findViewById(R.id.my_login);
        my_reg = view.findViewById(R.id.my_reg);
        my_login.setOnClickListener(this);
        my_reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_login:
                startActivityForResult(new Intent(getActivity(),LoginActivity.class), REQUEST_CODE);
                break;

            case R.id.my_reg:

                break;
        }
    }
}
