package id.ac.umn.finalproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tblPemasukan")
public class Pemasukan implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "namaPemasukan")
    private String namaPemasukan;
    @ColumnInfo(name = "jumlahPemasukan")
    private String jumlahPemasukan;

    public Pemasukan(String namaPemasukan, String jumlahPemasukan){
        this.namaPemasukan = namaPemasukan;
        this.jumlahPemasukan = jumlahPemasukan;
    }

    public void setNamaPemasukan(String namaPemasukan){this.namaPemasukan = namaPemasukan;}
    public void setJumlahPemasukan(String jumlahPemasukan){this.jumlahPemasukan = jumlahPemasukan;}

    public String getNamaPemasukan(){return this.namaPemasukan;}
    public String getJumlahPemasukan(){return this.jumlahPemasukan;}
}
