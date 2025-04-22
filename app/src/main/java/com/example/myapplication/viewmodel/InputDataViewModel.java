package com.example.myapplication.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.example.myapplication.database.DatabaseClient;
import com.example.myapplication.database.dao.DatabaseDao;
import com.example.myapplication.model.ModelDatabase;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InputDataViewModel extends AndroidViewModel {
    private DatabaseDao databaseDao;

    public InputDataViewModel(Application application) {
        super(application);
        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
    }

    public void addDataPemesan(
            String nama_penumpang, String keberangkatan,
            String tujuan, String tanggal, String nomor_telepon,
            int anak_anak, int dewasa, int harga_tiket, String kelas, String status) {

        Completable.fromAction(() -> {
                    ModelDatabase modelDatabase = new ModelDatabase();
                    modelDatabase.setNamaPenumpang(nama_penumpang);
                    modelDatabase.setKeberangkatan(keberangkatan);
                    modelDatabase.setTujuan(tujuan);
                    modelDatabase.setTanggal(tanggal);
                    modelDatabase.setNomorTelepon(nomor_telepon);
                    modelDatabase.setAnakAnak(anak_anak);
                    modelDatabase.setDewasa(dewasa);
                    modelDatabase.setHargaTiket(harga_tiket);
                    modelDatabase.setKelas(kelas);
                    modelDatabase.setStatus(status);
                    databaseDao.insertData(modelDatabase);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}