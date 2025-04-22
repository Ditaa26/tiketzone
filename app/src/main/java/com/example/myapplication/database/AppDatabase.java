package com.example.myapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.ModelDatabase;
import com.example.myapplication.database.dao.DatabaseDao;

@Database(entities = {ModelDatabase.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}