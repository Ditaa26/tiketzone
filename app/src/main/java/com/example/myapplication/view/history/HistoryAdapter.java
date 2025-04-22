package com.example.myapplication.view.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.ModelDatabase;
import com.example.myapplication.utils.FunctionHelper;

import java.util.List;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<ModelDatabase> modelDatabase;

    public HistoryAdapter(List<ModelDatabase> modelDatabase) {
        this.modelDatabase = new ArrayList<>(modelDatabase);
    }

    public void setDataAdapter(List<ModelDatabase> items) {
        modelDatabase.clear();
        modelDatabase.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelDatabase data = modelDatabase.get(position);

        // Menentukan kode keberangkatan
        switch (data.getKeberangkatan()) {
            case "Jakarta":
                holder.tvKode1.setText("JKT");
                break;
            case "Semarang":
                holder.tvKode1.setText("SRG");
                break;
            case "Surabaya":
                holder.tvKode1.setText("SUB");
                break;
            case "Bali":
                holder.tvKode1.setText("DPS");
                break;
            default:
                holder.tvKode1.setText(""); // Mengatasi kode tidak valid
                break;
        }

        // Menentukan kode tujuan
        switch (data.getTujuan()) {
            case "Jakarta":
                holder.tvKode2.setText("JKT");
                break;
            case "Semarang":
                holder.tvKode2.setText("SRG");
                break;
            case "Surabaya":
                holder.tvKode2.setText("SUB");
                break;
            case "Bali":
                holder.tvKode2.setText("DPS");
                break;
            default:
                holder.tvKode2.setText(""); // Mengatasi kode tidak valid
                break;
        }

        holder.tvKelas.setText(data.getKelas());
        holder.tvDate.setText(data.getTanggal());
        holder.tvNama.setText(data.getNamaPenumpang());
        holder.tvKeberangkatan.setText(data.getKeberangkatan());
        holder.tvTujuan.setText(data.getTujuan());
        holder.tvHargaTiket.setText(FunctionHelper.rupiahFormat(data.getHargaTiket()));
    }

    @Override
    public int getItemCount() {
        return modelDatabase.size();
    }

    public ModelDatabase setSwipeRemove(int position) {
        return modelDatabase.remove(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvKelas, tvDate, tvNama, tvHargaTiket, tvKode1, tvKode2, tvKeberangkatan, tvTujuan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKelas = itemView.findViewById(R.id.tvKelas);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvHargaTiket = itemView.findViewById(R.id.tvHargaTiket);
            tvKode1 = itemView.findViewById(R.id.tvKode1);
            tvKode2 = itemView.findViewById(R.id.tvKode2);
            tvKeberangkatan = itemView.findViewById(R.id.tvKeberangkatan);
            tvTujuan = itemView.findViewById(R.id.tvTujuan);
        }
    }
}