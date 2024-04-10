package com.example.propapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.propapp.entity.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btn_login);
        EditText edAccount = findViewById(R.id.ed_account);
        EditText edPasswd = findViewById(R.id.ed_passwd);
        btnLogin.setOnClickListener(v -> {
            User user = new User(edAccount.getText().toString(), edPasswd.getText().toString());
            Intent sendIntent = new Intent(this, TabHostActivity.class);
            sendIntent.putExtra("user", user);
            startActivity(sendIntent);
        });
    }
}