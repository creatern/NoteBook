package com.example.propapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.example.propapp.entity.User;

public class TabHostActivity extends TabActivity implements View.OnClickListener {

    private TabHost myTabHost;
    private LinearLayout first_linear, second_linear;
    private String TAG1 = "home";
    private String TAG2 = "info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        // 获取接收到的Intent
        Intent receivedIntent = getIntent();

        // 从receivedIntent中提取用户对象user
        User user = (User) receivedIntent.getSerializableExtra("user");

        first_linear = findViewById(R.id.first_linear);
        first_linear.setOnClickListener(this);
        second_linear = findViewById(R.id.second_linear);
        second_linear.setOnClickListener(this);
        myTabHost = getTabHost();
        myTabHost.addTab(myTabHost.newTabSpec(TAG1).setIndicator(getString(R.string.first_tab), getResources().getDrawable(R.drawable.tab_selector_home)).setContent(new Intent(this, HomeActivity.class)));
        myTabHost.addTab(myTabHost.newTabSpec(TAG2).setIndicator(getString(R.string.second_tab), getResources().getDrawable(R.drawable.tab_selector_info)).setContent((new Intent(this, InfoActivity.class)).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        setTabView(first_linear);
    }

    @Override
    public void onClick(View view) {
        setTabView(view);
    }

    private void setTabView(View v) {
        first_linear.setSelected(false);
        second_linear.setSelected(false);
        v.setSelected(true);
        if (v == first_linear) {
            myTabHost.setCurrentTabByTag(TAG1);
        } else if (v == second_linear) {
            myTabHost.setCurrentTabByTag(TAG2);
        }
    }


}