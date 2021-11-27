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

    }

    public void batalSimpanPengeluaran(View view){
        Intent batalSimpanPengeluaran = new Intent();
        setResult(RESULT_CANCELED, batalSimpanPengeluaran);
        finish();
    }
}