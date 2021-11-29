package id.ac.umn.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InputPengeluaranActivity extends AppCompatActivity {

    private EditText etNamaPengeluaran, etJumlahPengeluaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pengeluaran);

        etNamaPengeluaran = findViewById(R.id.etNamaPengeluaran);
        etJumlahPengeluaran = findViewById(R.id.etJumlahPengeluaran);

    }

    public void simpanPengeluaran(View view){
        String mNamaPengeluaran = etNamaPengeluaran.getText().toString();
        String mJumlahPengeluaran = etJumlahPengeluaran.getText().toString();

        if(mNamaPengeluaran.length()<=0 || mJumlahPengeluaran.length()<=0){
            Toast.makeText(this, "Semua harus diisi", Toast.LENGTH_LONG).show();
        } else{
            Intent simpanPengeluaran = new Intent();
            Pengeluaran pglr = new Pengeluaran(mNamaPengeluaran, mJumlahPengeluaran);
            simpanPengeluaran.putExtra("PENGELUARAN", pglr);
            setResult(RESULT_OK, simpanPengeluaran);
            finish();
        }
    }

    public void batalSimpanPengeluaran(View view){
        Intent batalSimpanPengeluaran = new Intent();
        setResult(RESULT_CANCELED, batalSimpanPengeluaran);
        finish();
    }
}