package com.example.myapplication.view.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

import com.example.myapplication.view.main.SplashScreen; // Ganti dengan path yang sesuai


public class daftarr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        Button btnDaftar = findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(daftarr.this, SplashScreen.class); // Ganti dengan target yang sesuai
                startActivity(intent);
                finish(); // optional: tutup halaman login biar nggak bisa balik
            }
        });

    }
}
