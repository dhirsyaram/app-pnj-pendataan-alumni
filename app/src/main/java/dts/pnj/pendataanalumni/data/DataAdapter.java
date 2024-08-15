package dts.pnj.pendataanalumni.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dts.pnj.pendataanalumni.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.AlumniViewHolder> {

    private final List<Alumni> alumniList;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Alumni alumni);
    }

    public DataAdapter(List<Alumni> alumniList, OnItemClickListener onItemClickListener) {
        this.alumniList = alumniList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AlumniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_alumni, parent, false);
        return new AlumniViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumniViewHolder holder, int position) {
        Alumni alumni = alumniList.get(position);
        holder.bind(alumni);
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(alumni));
    }

    @Override
    public int getItemCount() {
        return alumniList.size();
    }

    public static class AlumniViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNama, tvNim;

        public AlumniViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_name_data_alumni);
            tvNim = itemView.findViewById(R.id.tv_nim_data_alumni);
        }

        public void bind(Alumni alumni){
            tvNama.setText(alumni.getNama());
            tvNim.setText(alumni.getNim());
        }
    }
}
