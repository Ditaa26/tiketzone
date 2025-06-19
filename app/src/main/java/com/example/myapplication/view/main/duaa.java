package com.example.myapplication.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.myapplication.R;
import com.example.myapplication.view.history.HistoryActivity;
import com.example.myapplication.view.input.DataKapalActivity;
import com.example.myapplication.view.input.DataKeretaActivity;
import com.example.myapplication.view.input.DataPesawatActivity;
import com.example.myapplication.view.input.DataMobilActivity;

public class duaa extends AppCompatActivity {

    private CardView cvPesawat, cvKapal, cvKereta, cvMobil, cvProfile;
    private ImageView imageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua); // ← Panggil SEKALI SAJA!

        // Inisialisasi komponen
        cvPesawat = findViewById(R.id.cvPesawat);
        cvKapal   = findViewById(R.id.cvKapal);
        cvKereta  = findViewById(R.id.cvKereta);
        cvMobil   = findViewById(R.id.cvHotel); // Asumsinya untuk mobil
        cvProfile = findViewById(R.id.cvprofile);
        imageProfile = findViewById(R.id.imageProfile);

        // Pasang listener
        cvPesawat.setOnClickListener(v ->
                startActivity(new Intent(this, DataPesawatActivity.class)));

        cvKapal.setOnClickListener(v ->
                startActivity(new Intent(this, DataKapalActivity.class)));

        cvKereta.setOnClickListener(v ->
                startActivity(new Intent(this, DataKeretaActivity.class)));

        cvMobil.setOnClickListener(v ->
                startActivity(new Intent(this, DataMobilActivity.class)));

        cvProfile.setOnClickListener(v ->
                startActivity(new Intent(this, profill.class)));

        imageProfile.setOnClickListener(v ->
                startActivity(new Intent(this, HistoryActivity.class)));
    }
}
