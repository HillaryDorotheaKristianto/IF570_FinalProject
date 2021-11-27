package id.ac.umn.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class InputPemasukanActivity extends AppCompatActivity {

    private EditText etNamaPemasukan, etJumlahPemasukan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pemasukan);

        etNamaPemasukan = findViewById(R.id.etNamaPemasukan);
        etJumlahPemasukan = findViewById(R.id.etJumlahPemasukan);
    }

    public void simpanPemasukan(View view){

    }

    public void batalSimpanPemasukan(View view){
        Intent batalSimpanPemasukan = new Intent();
        setResult(RESULT_CANCELED, batalSimpanPemasukan);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}