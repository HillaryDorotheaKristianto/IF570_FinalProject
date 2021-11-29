package id.ac.umn.finalproject;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PengeluaranRepository {
    private PengeluaranDAO daoPengeluaran;
    private LiveData<List<Pengeluaran>> daftarPengeluaran;

    PengeluaranRepository(Application app){
        PengeluaranRoomDatabase db2 = PengeluaranRoomDatabase.getDatabase(app);
        daoPengeluaran = db2.daoPengeluaran();
        daftarPengeluaran = daoPengeluaran.getAllPengeluaran();
    }

    LiveData<List<Pengeluaran>> getDaftarPengeluaran(){
        return this.daftarPengeluaran;
    }

    public void insertPengeluaran(Pengeluaran pglr){
        new insertPengeluaranAsyncTask(daoPengeluaran).execute(pglr);
    }

    public void deleteAllPengeluaran(){
        new deleteAllPengeluaranAsyncTask(daoPengeluaran).execute();
    }

    public void deletePengeluaran(Pengeluaran pglr){
        new deletePengeluaranAsyncTask(daoPengeluaran).execute(pglr);
    }

    private static class insertPengeluaranAsyncTask extends AsyncTask<Pengeluaran, Void, Void>{
        private PengeluaranDAO asyncDaoPengeluaran;

        insertPengeluaranAsyncTask(PengeluaranDAO dao){
            this.asyncDaoPengeluaran = dao;
        }

        @Override
        protected Void doInBackground(final Pengeluaran... pengeluaran) {
            asyncDaoPengeluaran.insertPengeluaran(pengeluaran[0]);
            return null;
        }
    }

    private static class deleteAllPengeluaranAsyncTask extends AsyncTask<Void, Void, Void>{
        private PengeluaranDAO asyncDaoPengeluaran;

        deleteAllPengeluaranAsyncTask(PengeluaranDAO dao){
            this.asyncDaoPengeluaran = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncDaoPengeluaran.deleteAllPengeluaran();
            return null;
        }
    }

    private static class deletePengeluaranAsyncTask extends AsyncTask<Pengeluaran, Void, Void>{
        private PengeluaranDAO asyncDaoPengeluaran;

        deletePengeluaranAsyncTask(PengeluaranDAO dao){
            this.asyncDaoPengeluaran = dao;
        }

        @Override
        protected Void doInBackground(Pengeluaran... pengeluarans) {
            asyncDaoPengeluaran.deletePengeluaran(pengeluarans[0]);
            return null;
        }
    }
}
