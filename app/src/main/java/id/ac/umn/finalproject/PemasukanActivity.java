package id.ac.umn.finalproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.umn.finalproject.databinding.ActivityPemasukanBinding;

public class PemasukanActivity extends AppCompatActivity {

    private Button btnPengeluaran;
    private static final int REQUEST_TAMBAH = 1;
    private RecyclerView rvPemasukan;
    private PemasukanViewModel pmskVM;
    private CoordinatorLayout coordinatorLayout;

    private AppBarConfiguration appBarConfiguration;
    private ActivityPemasukanBinding binding;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPemasukanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        coordinatorLayout = findViewById(R.id.pemasukanActivity);
        coordinatorLayout.setBackgroundColor(Color.rgb(249, 242, 231));

        FloatingActionButton inputPemasukan = findViewById(R.id.inputPemasukan);
        inputPemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inputPemasukan = new Intent(PemasukanActivity.this, InputPemasukanActivity.class);
                startActivityForResult(inputPemasukan, REQUEST_TAMBAH);
            }
        });

        btnPengeluaran = findViewById(R.id.pagePengeluaran);
        btnPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pagePengeluaran = new Intent(PemasukanActivity.this, PengeluaranActivity.class);
                startActivity(pagePengeluaran);
            }
        });

        rvPemasukan = (RecyclerView) findViewById(R.id.rvPemasukan);
        final PemasukanListAdapter adapter = new PemasukanListAdapter(this);
        rvPemasukan.setAdapter(adapter);

        rvPemasukan.setLayoutManager(new LinearLayoutManager(this));
        pmskVM = ViewModelProviders.of(this).get(PemasukanViewModel.class);
        pmskVM.getDaftarPemasukan().observe(this, new Observer<List<Pemasukan>>() {
            @Override
            public void onChanged(List<Pemasukan> pmsks) {
                adapter.setDaftarPemasukan(pmsks);
            }
        });

        ItemTouchHelper helperPemasukan = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posisi = viewHolder.getAdapterPosition();
                Pemasukan pmsk = adapter.getPemasukanAtPosition(posisi);
                if(direction == ItemTouchHelper.LEFT){
                    Toast.makeText(PemasukanActivity.this, "Pemasukan " + pmsk.getNamaPemasukan() + " telah dihapus", Toast.LENGTH_LONG).show();
                    pmskVM.deletePemasukan(pmsk);
                }
            }
        });
        helperPemasukan.attachToRecyclerView(rvPemasukan);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Pemasukan pmsk = (Pemasukan) data.getSerializableExtra("PEMASUKAN");
            if(reqCode == REQUEST_TAMBAH){
                pmskVM.insertPemasukan(pmsk);
            }
        }
        rvPemasukan.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delAllPemasukan) {
            pmskVM.deleteAllPemasukan();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}