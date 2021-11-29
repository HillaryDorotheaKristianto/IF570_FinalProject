package id.ac.umn.finalproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tblPengeluaran")
public class Pengeluaran implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "namaPengeluaran")
    private String namaPengeluaran;
    @ColumnInfo(name = "jumlahPengeluaran")
    private String jumlahPengeluaran;

    public Pengeluaran(String namaPengeluaran, String jumlahPengeluaran){
        this.namaPengeluaran = namaPengeluaran;
        this.jumlahPengeluaran = jumlahPengeluaran;
    }

    public void setNamaPengeluaran(String namaPengeluaran){this.namaPengeluaran = namaPengeluaran;}
    public void setJumlahPengeluaran(String jumlahPengeluaran){this.jumlahPengeluaran = jumlahPengeluaran;}

    public String getNamaPengeluaran(){return this.namaPengeluaran;}
    public String getJumlahPengeluaran(){return this.jumlahPengeluaran;}
}
