package id.ac.umn.finalproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PemasukanDAO {
    @Query("SELECT * FROM tblPemasukan")
    LiveData<List<Pemasukan>> getAllPemasukan();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPemasukan(Pemasukan pmsk);

    @Delete
    void deletePemasukan(Pemasukan pmsk);

    @Query("DELETE FROM tblPemasukan")
    void deleteAllPemasukan();
}
