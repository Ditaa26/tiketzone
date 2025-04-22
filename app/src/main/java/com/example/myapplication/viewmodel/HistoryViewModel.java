package com.example.myapplication.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.myapplication.database.DatabaseClient;
import com.example.myapplication.database.dao.DatabaseDao;
import com.example.myapplication.model.ModelDatabase;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private LiveData<List<ModelDatabase>> dataList;
    private DatabaseDao databaseDao;

    public HistoryViewModel(Application application) {
        super(application);
        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
        dataList = databaseDao.getAllData();
    }

    public LiveData<List<ModelDatabase>> getDataList() {
        return dataList;
    }

    public void deleteDataById(int uid) {
        Completable.fromAction(() -> databaseDao.deleteDataById(uid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}