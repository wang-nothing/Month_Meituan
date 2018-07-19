package com.example.admin.month_meituan;

import android.Manifest;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.admin.month_meituan.fragment.Fragment_Home;
import com.example.admin.month_meituan.fragment.Fragment_My;
import com.example.admin.month_meituan.fragment.Fragment_Order;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private FrameLayout main_fragme;
    private RadioGroup main_group;
    private List<Fragment> fragments;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        initViews();
        initFragmens();
        initFragmeLayout();

    }

    private void initFragmeLayout() {
        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.main_fragme, fragments.get(0)).commit();
        main_group.check(R.id.radio_home);
    }

    private void initFragmens() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment_Home());
        fragments.add(new Fragment_Order());
        fragments.add(new Fragment_My());
    }

    private void initViews() {
        main_fragme = findViewById(R.id.main_fragme);
        main_group = findViewById(R.id.main_group);
        main_group.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radio_home:
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_fragme, fragments.get(0)).commit();
                break;

            case R.id.radio_order:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragme, fragments.get(1)).commit();
                break;

            case R.id.radio_my:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragme, fragments.get(2)).commit();
                break;
        }
    }
}
