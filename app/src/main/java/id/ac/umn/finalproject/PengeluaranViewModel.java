package id.ac.umn.finalproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PengeluaranViewModel extends AndroidViewModel {
    private PengeluaranRepository pglrRepository;
    private LiveData<List<Pengeluaran>> daftarPengeluaran;

    public PengeluaranViewModel(@NonNull Application application) {
        super(application);
        pglrRepository = new PengeluaranRepository(application);
        daftarPengeluaran = pglrRepository.getDaftarPengeluaran();
    }

    LiveData<List<Pengeluaran>> getDaftarPengeluaran(){
        return this.daftarPengeluaran;
    }

    public void insertPengeluaran(Pengeluaran pglr){
        pglrRepository.insertPengeluaran(pglr);
    }

    public void deleteAllPengeluaran(){
        pglrRepository.deleteAllPengeluaran();
    }

    public void deletePengeluaran(Pengeluaran pglr){
        pglrRepository.deletePengeluaran(pglr);
    }
}
