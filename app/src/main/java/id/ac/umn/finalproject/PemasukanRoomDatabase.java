package id.ac.umn.finalproject;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Pemasukan.class}, version = 1, exportSchema = false)
public abstract class PemasukanRoomDatabase extends RoomDatabase {

    public abstract PemasukanDAO daoPemasukan();
    private static PemasukanRoomDatabase INSTANCE;

    static PemasukanRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (PemasukanRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PemasukanRoomDatabase.class, "dbPemasukan").addCallback(sRoomPemasukanDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomPemasukanDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db1){
            super.onOpen(db1);
        }
    };
}
