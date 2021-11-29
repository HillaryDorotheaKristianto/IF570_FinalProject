package id.ac.umn.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PengeluaranListAdapter extends RecyclerView.Adapter<PengeluaranListAdapter.PengeluaranViewHolder> {

    private final LayoutInflater mInflater;
    private List<Pengeluaran> daftarPengeluaran;

    PengeluaranListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PengeluaranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_list_pengeluaran, parent, false);
        return new PengeluaranViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PengeluaranViewHolder holder, int position) {
        if(daftarPengeluaran != null){
            Pengeluaran pglr = daftarPengeluaran.get(position);
            holder.namaPengeluaran.setText(pglr.getNamaPengeluaran());
            holder.jumlahPengeluaran.setText("- Rp. " + pglr.getJumlahPengeluaran());
        } else{
            holder.jumlahPengeluaran.setText("Tidak ada pengeluaran");
        }
    }

    @Override
    public int getItemCount() {
        if(daftarPengeluaran != null){
            return daftarPengeluaran.size();
        } else{
            return 0;
        }
    }

    public Pengeluaran getPengeluaranAtPosition(int posisi){
        return daftarPengeluaran.get(posisi);
    }

    void setDaftarPengeluaran(List<Pengeluaran> pglrs){
        daftarPengeluaran = pglrs;
        notifyDataSetChanged();
    }

    public class PengeluaranViewHolder extends RecyclerView.ViewHolder {
        private TextView namaPengeluaran, jumlahPengeluaran;

        public PengeluaranViewHolder(@NonNull View itemView) {
            super(itemView);
            namaPengeluaran = itemView.findViewById(R.id.nama_pengeluaran);
            jumlahPengeluaran = itemView.findViewById(R.id.jlh_pengeluaran);
        }
    }
}
