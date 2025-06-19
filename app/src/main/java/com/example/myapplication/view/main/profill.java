package com.example.myapplication.view.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseClient;
import com.example.myapplication.database.dao.DatabaseDao;
import com.example.myapplication.model.ModelDatabase;
import android.content.Intent;
import android.content.SharedPreferences;

public class profill extends AppCompatActivity {

    private ImageView backButton;
    private TextView textJudulProfil;
    private ImageView imageProfile;
    private TextView textNama;
    private TextView textEmail;
    private Button btnSimpan;
    private Button btnEditNama;
    private EditText editTextNama;

    private boolean isEditing = false;
    private String currentEmail;

    private DatabaseDao databaseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        // Ambil email dari intent
        Intent intent = getIntent();
        currentEmail = intent.getStringExtra("EMAIL");

        if (currentEmail == null || currentEmail.isEmpty()) {
            // Coba ambil dari SharedPreferences jika tidak ada di intent
            SharedPreferences prefs = getSharedPreferences("MY_APP_PREFS", MODE_PRIVATE);
            currentEmail = prefs.getString("USER_EMAIL", "");

            if (currentEmail.isEmpty()) {
                Toast.makeText(this, "Sesi habis, silakan login kembali", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }
        // ... lanjutkan kode lainnya

        databaseDao = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().databaseDao();

        try {
            backButton = findViewById(R.id.backButton);
            textJudulProfil = findViewById(R.id.textJudulProfil);
            imageProfile = findViewById(R.id.imageProfile);
            textNama = findViewById(R.id.textNama);
            textEmail = findViewById(R.id.textEmail);
            btnSimpan = findViewById(R.id.btnSimpan);
            btnEditNama = findViewById(R.id.btnEditnama);
            editTextNama = findViewById(R.id.editTextNama);

            if (backButton == null || textJudulProfil == null || imageProfile == null ||
                    textNama == null || textEmail == null || btnSimpan == null ||
                    btnEditNama == null || editTextNama == null) {
                throw new RuntimeException("Beberapa view tidak ditemukan di layout");
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error inisialisasi view: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("PROFIL_ACTIVITY", "Error inisialisasi view", e);
            finish();
            return;
        }

        loadProfilData();

        backButton.setOnClickListener(v -> finish());

        btnEditNama.setOnClickListener(v -> {
            if (!isEditing) {
                enterEditMode();
            } else {
                saveChanges();
            }
        });

        btnSimpan.setOnClickListener(v -> saveChanges());
    }

    private void enterEditMode() {
        isEditing = true;
        btnEditNama.setText("Simpan");
        textNama.setVisibility(View.GONE);
        editTextNama.setVisibility(View.VISIBLE);
        editTextNama.setText(textNama.getText().toString());
    }

    private void exitEditMode() {
        isEditing = false;
        btnEditNama.setText("Edit Nama");
        editTextNama.setVisibility(View.GONE);
        textNama.setVisibility(View.VISIBLE);
    }

    private void loadProfilData() {
        new Thread(() -> {
            try {
                ModelDatabase user = databaseDao.getUserByEmail(currentEmail);
                runOnUiThread(() -> {
                    if (user != null) {
                        textNama.setText(user.getNamaPengguna());  // Ganti dari getNamaPenumpang()
                        textEmail.setText(currentEmail);
                    } else {
                        Toast.makeText(profill.this, "Data user tidak ditemukan", Toast.LENGTH_SHORT).show();
                        Log.w("PROFIL_ACTIVITY", "Data user kosong untuk email: " + currentEmail);
                    }
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(profill.this, "Gagal memuat data profil", Toast.LENGTH_SHORT).show();
                    Log.e("PROFIL_ACTIVITY", "Error loadProfilData", e);
                });
            }
        }).start();
    }

    private void saveChanges() {
        String newName = editTextNama.getText().toString().trim();

        if (newName.isEmpty()) {
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            try {
                ModelDatabase user = databaseDao.getUserByEmail(currentEmail);
                if (user != null) {
                    user.setNamaPengguna(newName);
                    databaseDao.updateData(user);

                    runOnUiThread(() -> {
                        textNama.setText(newName);
                        exitEditMode();
                        Toast.makeText(profill.this, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(profill.this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
                    });
                }
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(profill.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("PROFIL_ACTIVITY", "Error saveChanges", e);
                });
            }
        }).start();
    }
}
