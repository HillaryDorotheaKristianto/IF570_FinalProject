package id.ac.umn.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PemasukanListAdapter extends RecyclerView.Adapter<PemasukanListAdapter.PemasukanViewHolder> {

    private final LayoutInflater mInflater;
    private List<Pemasukan> daftarPemasukan;

    PemasukanListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PemasukanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_list_pemasukan, parent, false);
        return new PemasukanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PemasukanViewHolder holder, int position) {
        if(daftarPemasukan != null){
            Pemasukan pmsk = daftarPemasukan.get(position);
            holder.NamaPemasukan.setText(pmsk.getNamaPemasukan());
            holder.JumlahPemasukan.setText("+ Rp. " + pmsk.getJumlahPemasukan());
        } else{
            holder.JumlahPemasukan.setText("Tidak ada pemasukan");
        }
    }

    @Override
    public int getItemCount() {
        if(daftarPemasukan != null){
            return daftarPemasukan.size();
        } else{
            return 0;
        }
    }

    public Pemasukan getPemasukanAtPosition(int posisi){
        return daftarPemasukan.get(posisi);
    }

    void setDaftarPemasukan(List<Pemasukan> pmsks){
        daftarPemasukan = pmsks;
        notifyDataSetChanged();
    }

    public class PemasukanViewHolder extends RecyclerView.ViewHolder {
        private TextView NamaPemasukan, JumlahPemasukan;

        public PemasukanViewHolder(@NonNull View itemView) {
            super(itemView);
            NamaPemasukan = itemView.findViewById(R.id.nama_pemasukan);
            JumlahPemasukan = itemView.findViewById(R.id.jlh_pemasukan);
        }
    }
}
