package com.example.loginapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginapp.models.Resena;
import java.util.List;

public class ResenaAdapter extends RecyclerView.Adapter<ResenaAdapter.ResenaViewHolder> {

    private List<Resena> resenas;

    public ResenaAdapter(List<Resena> resenas) {
        this.resenas = resenas;
    }

    @NonNull
    @Override
    public ResenaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ResenaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResenaViewHolder holder, int position) {
        Resena resena = resenas.get(position);
        holder.commentTextView.setText(resena.getComentario());
        holder.dateTextView.setText(resena.getFechaCreacion());
    }

    @Override
    public int getItemCount() {
        return resenas.size();
    }

    static class ResenaViewHolder extends RecyclerView.ViewHolder {
        TextView commentTextView;
        TextView dateTextView;

        ResenaViewHolder(@NonNull View itemView) {
            super(itemView);
            commentTextView = itemView.findViewById(android.R.id.text1);
            dateTextView = itemView.findViewById(android.R.id.text2);
        }
    }
}
