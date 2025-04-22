package com.example.myapplication.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.view.main.SplashScreen; // Ganti dengan path yang sesuai
import com.example.myapplication.view.main.daftarr; // Ganti dengan path class daftarr kamu

public class loginn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Tombol Masuk
        Button btnMasuk = findViewById(R.id.btnMasuk);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginn.this, SplashScreen.class); // Ganti dengan target yang sesuai
                startActivity(intent);
                finish(); // optional: tutup halaman login biar nggak bisa balik
            }
        });

        // Teks "Daftar disini!"
        TextView txtDaftar = findViewById(R.id.txtDaftar);
        txtDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginn.this, daftarr.class); // Ganti dengan class halaman daftar kamu
                startActivity(intent);
            }
        });
    }
}
