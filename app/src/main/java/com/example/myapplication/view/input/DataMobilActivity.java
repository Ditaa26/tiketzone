package com.example.myapplication.view.input;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.R;
import com.example.myapplication.viewmodel.InputDataViewModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
public class DataMobilActivity extends AppCompatActivity {
    private String[] strAsal = {"Jakarta", "Semarang", "Surabaya", "Bali"};
    private String[] strTujuan = {"Jakarta", "Semarang", "Surabaya", "Bali"};
    private String[] strKelas = {"Eksekutif", "Bisnis", "Ekonomi"};

    private InputDataViewModel inputDataViewModel;
    private String sAsal, sTujuan, sTanggal, sNama, sTelp, sKelas;
    private int hargaDewasa = 0, hargaAnak = 0, hargaKelas = 0;
    private int hargaTotalDewasa = 0, hargaTotalAnak = 0, hargaTotal = 0;
    private int jmlDewasa = 0, jmlAnak = 0;
    private int countAnak = 0, countDewasa = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        setInitView();
        setViewModel();
        setSpinnerAdapter();
        setJmlPenumpang();
        setInputData();
    }

    private void setViewModel() {
        inputDataViewModel = new ViewModelProvider(this).get(InputDataViewModel.class);
    }

    private void setInitView() {
        findViewById(R.id.inputTanggal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar tanggalJemput = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        tanggalJemput.set(Calendar.YEAR, year);
                        tanggalJemput.set(Calendar.MONTH, monthOfYear);
                        tanggalJemput.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String strFormatDefault = "d MMMM yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormatDefault, Locale.getDefault());
                        ((TextView) findViewById(R.id.inputTanggal)).setText(simpleDateFormat.format(tanggalJemput.getTime()));
                    }
                };
                new DatePickerDialog(DataMobilActivity.this, date,
                        tanggalJemput.get(Calendar.YEAR),
                        tanggalJemput.get(Calendar.MONTH),
                        tanggalJemput.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void setSpinnerAdapter() {
        ArrayAdapter<CharSequence> adapterAsal = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strAsal);
        adapterAsal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.spBerangkat)).setAdapter(adapterAsal);

        ArrayAdapter<CharSequence> adapterTujuan = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strTujuan);
        adapterTujuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.spTujuan)).setAdapter(adapterTujuan);

        ArrayAdapter<CharSequence> adapterKelas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strKelas);
        adapterKelas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) findViewById(R.id.spKelas)).setAdapter(adapterKelas);

        ((Spinner) findViewById(R.id.spBerangkat)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sAsal = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ((Spinner) findViewById(R.id.spTujuan)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sTujuan = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ((Spinner) findViewById(R.id.spKelas)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sKelas = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setJmlPenumpang() {
        ImageView imageAdd1 = findViewById(R.id.imageAdd1);
        ImageView imageMinus1 = findViewById(R.id.imageMinus1);
        ImageView imageAdd2 = findViewById(R.id.imageAdd2);
        ImageView imageMinus2 = findViewById(R.id.imageMinus2);
        TextView tvJmlAnak = findViewById(R.id.tvJmlAnak);
        TextView tvJmlDewasa = findViewById(R.id.tvJmlDewasa);

        // Add anak
        imageAdd1.setOnClickListener(v -> {
            countAnak++;
            tvJmlAnak.setText(String.valueOf(countAnak));
        });

        // Min anak
        imageMinus1.setOnClickListener(v -> {
            if (countAnak > 0) {
                countAnak--;
                tvJmlAnak.setText(String.valueOf(countAnak));
            }
        });

        // Add dewasa
        imageAdd2.setOnClickListener(v -> {
            countDewasa++;
            tvJmlDewasa.setText(String.valueOf(countDewasa));
        });

        // Min dewasa
        imageMinus2.setOnClickListener(v -> {
            if (countDewasa > 0) {
                countDewasa--;
                tvJmlDewasa.setText(String.valueOf(countDewasa));
            }
        });
    }

    private void setInputData() {
        findViewById(R.id.btnCheckout).setOnClickListener(v -> {
            setPerhitunganHargaTiket();
            sNama = ((TextView) findViewById(R.id.inputNama)).getText().toString();
            sTanggal = ((TextView) findViewById(R.id.inputTanggal)).getText().toString();
            sTelp = ((TextView) findViewById(R.id.inputTelepon)).getText().toString();

            if (sNama.isEmpty() || sTanggal.isEmpty() || sTelp.isEmpty() || sAsal.isEmpty() ||
                    sTujuan.isEmpty() || countDewasa == 0 || hargaTotal == 0 || sKelas.isEmpty()) {
                Toast.makeText(DataMobilActivity.this, "Mohon lengkapi data pemesanan!", Toast.LENGTH_SHORT).show();
            } else {
                if (sAsal.equals(sTujuan)) {
                    Toast.makeText(DataMobilActivity.this, "Asal dan Tujuan tidak boleh sama!", Toast.LENGTH_LONG).show();
                } else {
                    inputDataViewModel.addDataPemesan(sNama, sAsal, sTujuan, sTanggal, sTelp,
                            countAnak, countDewasa, hargaTotal, sKelas, "1");
                    Toast.makeText(DataMobilActivity.this, "Booking Tiket berhasil, cek di menu riwayat", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void setPerhitunganHargaTiket() {
        // Set Asal & Tujuan
        if (sAsal.equals("Jakarta") && sTujuan.equals("Semarang")) {
            hargaDewasa = 350000;
            hargaAnak = 250000;
        } else if (sAsal.equals("Jakarta") && sTujuan.equals("Surabaya")) {
            hargaDewasa = 600000;
            hargaAnak = 500000;
        } else if (sAsal.equals("Jakarta") && sTujuan.equals("Bali")) {
            hargaDewasa = 900000;
            hargaAnak = 750000;
        } else if (sAsal.equals("Semarang") && sTujuan.equals("Jakarta")) {
            hargaDewasa = 350000;
            hargaAnak = 250000;
        } else if (sAsal.equals("Semarang") && sTujuan.equals("Surabaya")) {
            hargaDewasa = 400000;
            hargaAnak = 300000;
        } else if (sAsal.equals("Semarang") && sTujuan.equals("Bali")) {
            hargaDewasa = 800000;
            hargaAnak = 650000;
        } else if (sAsal.equals("Surabaya") && sTujuan.equals("Jakarta")) {
            hargaDewasa = 600000;
            hargaAnak = 500000;
        } else if (sAsal.equals("Surabaya") && sTujuan.equals("Semarang")) {
            hargaDewasa = 400000;
            hargaAnak = 300000;
        } else if (sAsal.equals("Surabaya") && sTujuan.equals("Bali")) {
            hargaDewasa = 500000;
            hargaAnak = 400000;
        } else if (sAsal.equals("Bali") && sTujuan.equals("Jakarta")) {
            hargaDewasa = 900000;
            hargaAnak = 750000;
        } else if (sAsal.equals("Bali") && sTujuan.equals("Semarang")) {
            hargaDewasa = 800000;
            hargaAnak = 650000;
        } else if (sAsal.equals("Bali") && sTujuan.equals("Surabaya")) {
            hargaDewasa = 500000;
            hargaAnak = 400000;
        }

        // Set Kelas Penumpang
        if (sKelas.equalsIgnoreCase("Eksekutif")) {
            hargaKelas = 300000;
        } else if (sKelas.equalsIgnoreCase("Bisnis")) {
            hargaKelas = 200000;
        } else if (sKelas.equalsIgnoreCase("Ekonomi")) {
            hargaKelas = 100000;
        }


        jmlDewasa = countDewasa;
        jmlAnak = countAnak;
        hargaTotalDewasa = jmlDewasa * hargaDewasa;
        hargaTotalAnak = jmlAnak * hargaAnak;
        hargaTotal = hargaTotalDewasa + hargaTotalAnak + hargaKelas;
    }

}