package com.example.myapplication.view.main;

import android.content.Intent;
import android.os.Bundle;
import com.example.myapplication.R;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, loginn.class);
            startActivity(intent);
        });

    }
}