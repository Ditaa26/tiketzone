package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private static DatabaseClient mInstance;
    private final AppDatabase appDatabase;

    // Private constructor to prevent instantiation from other classes
    private DatabaseClient(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "travel_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    // Synchronized method to get the singleton instance of DatabaseClient
    public static synchronized DatabaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}