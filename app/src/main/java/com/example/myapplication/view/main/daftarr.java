package com.example.myapplication.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseClient;
import com.example.myapplication.database.dao.DatabaseDao;
import com.example.myapplication.model.ModelDatabase;

public class daftarr extends AppCompatActivity {

    private EditText namaEditText, emailEditText, passwordEditText;
    private Button btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        namaEditText = findViewById(R.id.inputNama2);
        emailEditText = findViewById(R.id.inputEmail2);
        passwordEditText = findViewById(R.id.inputPassword2);
        btnDaftar = findViewById(R.id.btnDaftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = namaEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (nama.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(daftarr.this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                } else {
                    // Simpan ke database menggunakan thread background
                    new Thread(() -> {
                        // Buat data user
                        ModelDatabase user = new ModelDatabase();
                        user.setNamaPengguna(nama);  // Ganti dari setNamaPenumpang()
                        user.setEmail(email);
                        user.setPassword(password);

                        // Simpan ke DB
                        DatabaseDao dao = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().databaseDao();
                        dao.insertData(user);

                        runOnUiThread(() -> {
                            Toast.makeText(daftarr.this, "Pendaftaran berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(daftarr.this, loginn.class);
                            startActivity(intent);
                            finish();
                        });
                    }).start();
                }
            }
        });
    }
}
