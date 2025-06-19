package com.example.myapplication.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseClient;
import com.example.myapplication.database.dao.DatabaseDao;
import com.example.myapplication.model.ModelDatabase;
import com.google.android.material.textfield.TextInputEditText;

import android.content.SharedPreferences; // ← Tambahkan ini
import android.util.Log; // ← Tambahkan untuk Log

public class loginn extends AppCompatActivity {

    private TextInputEditText emailEditText, passwordEditText;
    private Button btnMasuk;
    private TextView txtDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.inputEmail);
        passwordEditText = findViewById(R.id.inputPassword);
        btnMasuk = findViewById(R.id.btnMasuk);
        txtDaftar = findViewById(R.id.txtDaftar);

        btnMasuk.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(loginn.this, "Email dan password harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                try {
                    DatabaseDao dao = DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase()
                            .databaseDao();

                    ModelDatabase user = dao.getUserByEmail(email);

                    runOnUiThread(() -> {
                        if (user != null && user.getPassword().equals(password)) {
                            // ✅ Simpan email ke SharedPreferences
                            SharedPreferences prefs = getSharedPreferences("MY_APP_PREFS", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("USER_EMAIL", email);
                            editor.putString("USERNAME", user.getNamaPengguna()); // ← Tambahan penting
                            editor.apply();

                            Toast.makeText(loginn.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(loginn.this, SplashScreen.class);
                            intent.putExtra("EMAIL", email);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(loginn.this, "Email atau password salah", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    runOnUiThread(() -> {
                        Toast.makeText(loginn.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("LOGIN_ERROR", "Error: ", e);
                    });
                }
            }).start();
        });

        txtDaftar.setOnClickListener(v -> {
            Intent intent = new Intent(loginn.this, daftarr.class);
            startActivity(intent);
        });
    }
}
