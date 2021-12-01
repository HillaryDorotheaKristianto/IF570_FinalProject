package id.ac.umn.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class InputPemasukanActivity extends AppCompatActivity {

    private EditText etNamaPemasukan, etJumlahPemasukan;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pemasukan);

        linearLayout = findViewById(R.id.inputPemasukanActivity);
        linearLayout.setBackgroundColor(Color.rgb(249, 242, 231));

        etNamaPemasukan = findViewById(R.id.etNamaPemasukan);
        etJumlahPemasukan = findViewById(R.id.etJumlahPemasukan);
    }

    public void simpanPemasukan(View view){
        String mNamaPemasukan = etNamaPemasukan.getText().toString();
        String mJumlahPemasukan = etJumlahPemasukan.getText().toString();

        if(mNamaPemasukan.length() <= 0 || mJumlahPemasukan.length() <= 0){
            Toast.makeText(this, "Semua harus diisi", Toast.LENGTH_LONG).show();
        } else{
            Intent simpanPemasukan = new Intent();
            Pemasukan pmsk = new Pemasukan(mNamaPemasukan, mJumlahPemasukan);
            simpanPemasukan.putExtra("PEMASUKAN", pmsk);
            setResult(RESULT_OK, simpanPemasukan);
            finish();
//            Log.d("Data 1: ", mNamaPemasukan);
//            Log.d("Data 2: ", mJumlahPemasukan);
        }
    }

    public void batalSimpanPemasukan(View view){
        Intent batalSimpanPemasukan = new Intent();
        setResult(RESULT_CANCELED, batalSimpanPemasukan);
        finish();
    }
}