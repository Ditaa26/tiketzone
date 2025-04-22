package com.example.myapplication.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.myapplication.R;

public class historyy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Inisialisasi Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Menambahkan aksi untuk tombol back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Tampilkan tombol back

        // Menambahkan listener untuk tombol back
        toolbar.setNavigationOnClickListener(v -> {
            // Arahkan ke SplashScreen ketika tombol back diklik
            Intent intent = new Intent(historyy.this, SplashScreen.class);
            startActivity(intent);
            finish(); // tutup HistoryActivity agar tidak bisa kembali ke halaman ini
        });
    }
}
