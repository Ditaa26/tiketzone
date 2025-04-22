package com.example.myapplication.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.model.ModelDatabase;

import java.util.List;

@Dao
public interface DatabaseDao {

    @Query("SELECT * FROM tbl_travel")
    LiveData<List<ModelDatabase>> getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertData(ModelDatabase... modelDatabases);

    @Query("DELETE FROM tbl_travel WHERE uid = :uid")
    void deleteDataById(int uid);
}