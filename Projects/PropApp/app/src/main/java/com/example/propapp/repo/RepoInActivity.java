package com.example.propapp.repo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.propapp.R;
import com.example.propapp.repo.repoin.RecevieStandardActivity;

public class RepoInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_in);

        ImageButton ibReceiveStandard = findViewById(R.id.ib_repo_in_recevie_standard);
        ibReceiveStandard.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecevieStandardActivity.class);
            startActivity(intent);
        });
    }
}