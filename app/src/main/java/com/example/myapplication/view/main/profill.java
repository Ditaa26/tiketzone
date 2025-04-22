package com.example.myapplication.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.view.main.SplashScreen; // Pastikan path ini sesuai dengan Splashscreen kamu

public class profill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        // Inisialisasi tombol back
        ImageView backButton = findViewById(R.id.backButton);

        // Aksi saat tombol back diklik
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigasi ke halaman Splashscreen
                Intent intent = new Intent(profill.this, SplashScreen.class);
                startActivity(intent);
                finish(); // supaya halaman profil tertutup dan tidak bisa dikembalikan dengan tombol back
            }
        });
    }
}
