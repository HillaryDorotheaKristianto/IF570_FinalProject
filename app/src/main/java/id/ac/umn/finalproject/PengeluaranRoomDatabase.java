package id.ac.umn.finalproject;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Pengeluaran.class}, version = 1, exportSchema = false)
public abstract class PengeluaranRoomDatabase extends RoomDatabase {
    public abstract PengeluaranDAO daoPengeluaran();
    private static PengeluaranRoomDatabase INSTANCE;

    static PengeluaranRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (PengeluaranRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PengeluaranRoomDatabase.class, "dbPengeluaran").addCallback(sRoomPengeluaranDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomPengeluaranDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db2){
            super.onOpen(db2);
        }
    };
}
