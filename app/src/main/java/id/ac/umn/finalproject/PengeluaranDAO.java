package id.ac.umn.finalproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PengeluaranDAO {
    @Query("SELECT * FROM tblPengeluaran")
    LiveData<List<Pengeluaran>> getAllPengeluaran();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPengeluaran(Pengeluaran pglr);

    @Delete
    void deletePengeluaran(Pengeluaran pglr);

    @Query("DELETE FROM tblPengeluaran")
    void deleteAllPengeluaran();
}
