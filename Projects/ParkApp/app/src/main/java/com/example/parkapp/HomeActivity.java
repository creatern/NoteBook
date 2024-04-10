package com.example.parkapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 园区状态
        ImageButton ibParkState = findViewById(R.id.ib_park_state);
        ibParkState.setOnClickListener(v -> {
            Intent intent = new Intent(this, ParkStateActivity.class);
            startActivity(intent);
        });

        // 质量检测
        ImageButton ibQualityCheck = findViewById(R.id.ib_quality_check);
        ibQualityCheck.setOnClickListener(v -> {
            Intent intent = new Intent(this, QualityCheckActivity.class);
            startActivity(intent);
        });
        // 整备结果
        ImageButton ibHostling = findViewById(R.id.ib_hostling);
        ibHostling.setOnClickListener(v -> {
            Intent intent = new Intent(this, HostlingActivity.class);
            startActivity(intent);
        });

        ImageButton ibTakeStock = findViewById(R.id.ib_take_stock);
        ibTakeStock.setOnClickListener(v -> {
            Intent intent = new Intent(this, TakeStockActivity.class);
            startActivity(intent);
        });
        ImageButton ibOrderCar = findViewById(R.id.ib_get_car);
        ibOrderCar.setOnClickListener(v -> {
            Intent intent = new Intent(this, OrderCarActivity.class);
            startActivity(intent);
        });
    }
}