package id.ac.umn.finalproject;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PemasukanRepository {
    private PemasukanDAO daoPemasukan;
    private LiveData<List<Pemasukan>> daftarPemasukan;

    PemasukanRepository(Application app){
        PemasukanRoomDatabase db1 = PemasukanRoomDatabase.getDatabase(app);
        daoPemasukan = db1.daoPemasukan();
        daftarPemasukan = daoPemasukan.getAllPemasukan();
    }

    LiveData<List<Pemasukan>> getDaftarPemasukan(){
        return this.daftarPemasukan;
    }

    public void insertPemasukan(Pemasukan pmsk){
        new insertPemasukanAsyncTask(daoPemasukan).execute(pmsk);
    }

    public void deleteAllPemasukan(){
        new deleteAllPemasukanAsyncTask(daoPemasukan).execute();
    }

    public void deletePemasukan(Pemasukan pmsk){
        new deletePemasukanAsyncTask(daoPemasukan).execute(pmsk);
    }

    private static class insertPemasukanAsyncTask extends AsyncTask<Pemasukan, Void, Void>{

        private PemasukanDAO asyncDaoPemasukan;

        insertPemasukanAsyncTask(PemasukanDAO dao){
            this.asyncDaoPemasukan = dao;
        }

        @Override
        protected Void doInBackground(final Pemasukan... pemasukan) {
            asyncDaoPemasukan.insertPemasukan(pemasukan[0]);
            return null;
        }
    }

    private static class deleteAllPemasukanAsyncTask extends AsyncTask<Void, Void, Void>{

        private PemasukanDAO asyncDaoPemasukan;

        deleteAllPemasukanAsyncTask(PemasukanDAO dao){
            this.asyncDaoPemasukan = dao;
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            asyncDaoPemasukan.deleteAllPemasukan();
            return null;
        }
    }

    private static class deletePemasukanAsyncTask extends AsyncTask<Pemasukan, Void, Void>{

        private PemasukanDAO asyncDaoPemasukan;

        deletePemasukanAsyncTask(PemasukanDAO dao){
            this.asyncDaoPemasukan = dao;
        }

        @Override
        protected Void doInBackground(Pemasukan... pemasukans) {
            asyncDaoPemasukan.deletePemasukan(pemasukans[0]);
            return null;
        }
    }
}
