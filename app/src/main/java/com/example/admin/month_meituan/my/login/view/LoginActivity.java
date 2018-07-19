package com.example.admin.month_meituan.my.login.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.admin.month_meituan.R;

public class LoginActivity extends AppCompatActivity implements Ilogin_Iview, View.OnClickListener {
    private EditText login_mobile, login_password;
    private Button login_btn_login;
    private ProgressDialog dialog;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void view_onSuccess(String mobile, String password) {

    }

    @Override
    public void view_onFail(int code) {

    }
}
