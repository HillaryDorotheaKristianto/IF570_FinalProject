package id.ac.umn.finalproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PemasukanViewModel extends AndroidViewModel {

    private PemasukanRepository pmskRepository;
    private LiveData<List<Pemasukan>> daftarPemasukan;

    public PemasukanViewModel(@NonNull Application application) {
        super(application);
        pmskRepository = new PemasukanRepository(application);
        daftarPemasukan = pmskRepository.getDaftarPemasukan();
    }

    LiveData<List<Pemasukan>> getDaftarPemasukan(){ return this.daftarPemasukan; }

    public void insertPemasukan(Pemasukan pmsk){
        pmskRepository.insertPemasukan(pmsk);
    }

    public void deleteAllPemasukan(){
        pmskRepository.deleteAllPemasukan();
    }

    public void deletePemasukan(Pemasukan pmsk){
        pmskRepository.deletePemasukan(pmsk);
    }
}
