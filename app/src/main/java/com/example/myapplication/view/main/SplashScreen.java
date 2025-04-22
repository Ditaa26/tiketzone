package com.example.myapplication.view.main;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.view.history.HistoryActivity;
import com.example.myapplication.view.input.DataKapalActivity;
import com.example.myapplication.view.input.DataKeretaActivity;
import com.example.myapplication.view.input.DataPesawatActivity;
import com.example.myapplication.view.input.DataMobilActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua);
        findViewById(R.id.imageProfile).setOnClickListener(v -> {
            Intent intent = new Intent(SplashScreen.this, HistoryActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.cvKapal).setOnClickListener(v -> {
            Intent intent = new Intent(SplashScreen.this, DataKapalActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.cvPesawat).setOnClickListener(v -> {
            Intent intent = new Intent(SplashScreen.this, DataPesawatActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.cvKereta).setOnClickListener(v -> {
            Intent intent = new Intent(SplashScreen.this, DataKeretaActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.cvHotel).setOnClickListener(v -> {
            Intent intent = new Intent(SplashScreen.this, DataMobilActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.cvprofile).setOnClickListener(v -> {
            Intent intent = new Intent(SplashScreen.this, profill.class);
            startActivity(intent);
        });
    }
}