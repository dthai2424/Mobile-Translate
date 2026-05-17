package com.example.translator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder> {
    private final List<SavedTranslation> list;

    public SavedAdapter(List<SavedTranslation> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavedTranslation item = list.get(position);
        holder.tvSource.setText(String.format("[%s] %s", item.sourceLang, item.sourceText));
        holder.tvTarget.setText(String.format("[%s] %s", item.targetLang, item.targetText));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSource, tvTarget;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSource = itemView.findViewById(R.id.tvSource);
            tvTarget = itemView.findViewById(R.id.tvTarget);
        }
    }
}