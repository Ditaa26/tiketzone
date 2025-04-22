package com.example.myapplication.view.history;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.ModelDatabase;
import com.example.myapplication.viewmodel.HistoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private List<ModelDatabase> modelDatabaseList = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private HistoryViewModel historyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setInitLayout();
        setViewModel();
        setUpItemTouchHelper();
    }

    private void setInitLayout() {
        historyAdapter = new HistoryAdapter(modelDatabaseList);
        RecyclerView rvHistory = findViewById(R.id.rvHistory);
        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(historyAdapter);
    }

    private void setViewModel() {
        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        historyViewModel.getDataList().observe(this, modelDatabases -> {
            RecyclerView rvHistory = findViewById(R.id.rvHistory);
            View tvNotFound = findViewById(R.id.tvNotFound);

            if (modelDatabases.isEmpty()) {
                tvNotFound.setVisibility(View.VISIBLE);
                rvHistory.setVisibility(View.GONE);
            } else {
                tvNotFound.setVisibility(View.GONE);
                rvHistory.setVisibility(View.VISIBLE);
            }
            historyAdapter.setDataAdapter(modelDatabases);
        });
    }

    private void setUpItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPosition = viewHolder.getAdapterPosition();
                ModelDatabase modelDatabase = historyAdapter.setSwipeRemove(swipedPosition);
                int uid = modelDatabase.getUid();
                historyViewModel.deleteDataById(uid);
                Toast.makeText(HistoryActivity.this, "Data yang dipilih sudah dihapus", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(findViewById(R.id.rvHistory));
    }
}
