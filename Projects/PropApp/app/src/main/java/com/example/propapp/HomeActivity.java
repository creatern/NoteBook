package com.example.propapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.propapp.repo.RepoInActivity;
import com.example.propapp.repo.SendActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 入库管理
        ImageButton ibRepoIn = findViewById(R.id.ib_repo_in);
        ibRepoIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RepoInActivity.class);
            startActivity(intent);
        });

        // 出库管理
        ImageButton ibRepoOut = findViewById(R.id.ib_repo_out);
        ibRepoOut.setOnClickListener(v -> {
            Intent intent = new Intent(this, RepoOutActivity.class);
            startActivity(intent);
        });
        // 库存管理
        ImageButton ibRepoManage = findViewById(R.id.ib_repo_manage);
        // 防错校验
        ImageButton ibRepoCheck = findViewById(R.id.ib_repo_check);
        // 领料管理
        ImageButton ibRepoGet = findViewById(R.id.ib_repo_get);
        // 调拨管理
        ImageButton ibRepoAllocation = findViewById(R.id.ib_repo_allocation);
        ibRepoAllocation.setOnClickListener(v -> {
            Intent intent = new Intent(this, LocationActivity.class);
            startActivity(intent);
        });
        // 包装管理
        ImageButton ibRepoPut = findViewById(R.id.ib_repo_put);
        // 配送管理
        ImageButton ibRepoSend = findViewById(R.id.ib_repo_send);
        ibRepoSend.setOnClickListener(v -> {
            Intent intent = new Intent(this, SendActivity.class);
            startActivity(intent);
        });

    }
}