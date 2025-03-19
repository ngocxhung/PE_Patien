package com.example.pe_patien.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pe_patien.R;
import com.example.pe_patien.entity.Patien;
import com.example.pe_patien.repository.PatienRepository;

import java.util.List;

public class PatienAdapter extends RecyclerView.Adapter<PatienAdapter.PatienViewHolder> {
    private List<Patien> patienList;
    private Context context;
    public PatienAdapter(List<Patien> patienList, Context context) {
        this.patienList = patienList;
        this.context = context;
    }
    @NonNull
    @Override
    public PatienAdapter.PatienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patien_list_item, parent, false);
        return new PatienViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PatienAdapter.PatienViewHolder holder, int position) {

        Patien patien = patienList.get(position);
        holder.tvPatienCode.setText(patien.getCode());
        holder.tvPatienName.setText(patien.getName());
        holder.tvPatienDOB.setText(patien.getDob());
        holder.tvPatienAge.setText(String.valueOf(patien.getAge()));

    }

    @Override
    public int getItemCount() {
        return patienList.size();
    }

    public class PatienViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
        private final TextView tvPatienCode;
        private final TextView tvPatienName;
        private final TextView tvPatienDOB;
        private final TextView tvPatienAge;
        private final TextView tvAction;
        private PatienAdapter patienAdapter;
        private PatienRepository patienRepository;

        public PatienViewHolder(@NonNull View itemView, PatienAdapter patienAdapter) {

            super(itemView);
            tvPatienCode = itemView.findViewById(R.id.tvPatienCode);
            tvPatienName = itemView.findViewById(R.id.tvPatienName);
            tvPatienDOB = itemView.findViewById(R.id.tvPatienDOB);
            tvPatienAge = itemView.findViewById(R.id.tvPatienAge);
            tvAction = itemView.findViewById(R.id.tvAction);
            tvAction.setOnClickListener(this);
            this.patienAdapter = patienAdapter;
        }

        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Delete Product Confirmation");
            builder.setMessage("Do you want to delete this patien?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    patienRepository = new PatienRepository(view.getContext());
                    patienRepository.delete(patienList.get(position));

                    patienAdapter.patienList.remove(position);
                    patienAdapter.notifyItemRemoved(position);
                }
                dialog.dismiss();
            });
            builder.setNegativeButton("No", (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();

        }
    }
}
