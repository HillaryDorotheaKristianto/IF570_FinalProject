package id.ac.umn.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import id.ac.umn.finalproject.databinding.ActivityPengeluaranBinding;

public class PengeluaranActivity extends AppCompatActivity {

    private Button btnPemasukan;
    private static final int REQUEST_TAMBAH = 1;
    private RecyclerView rvPengeluaran;
    private PengeluaranViewModel pglrVM;
    CoordinatorLayout coordinatorLayout;

    private AppBarConfiguration appBarConfiguration;
    private ActivityPengeluaranBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPengeluaranBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        coordinatorLayout = findViewById(R.id.pengeluaranActivity);
        coordinatorLayout.setBackgroundColor(Color.rgb(249, 242, 231));

        FloatingActionButton inputPengeluaran = findViewById(R.id.inputPengeluaran);
        inputPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inputPengeluaran = new Intent(PengeluaranActivity.this, InputPengeluaranActivity.class);
                startActivityForResult(inputPengeluaran, REQUEST_TAMBAH);
            }
        });

        btnPemasukan = findViewById(R.id.pagePemasukan);
        btnPemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pagePemasukan = new Intent(PengeluaranActivity.this, PemasukanActivity.class);
                startActivity(pagePemasukan);
            }
        });

        rvPengeluaran = (RecyclerView) findViewById(R.id.rvPengeluaran);
        final PengeluaranListAdapter adapterPengeluaran = new PengeluaranListAdapter(this);
        rvPengeluaran.setAdapter(adapterPengeluaran);

        rvPengeluaran.setLayoutManager(new LinearLayoutManager(this));
        pglrVM = ViewModelProviders.of(this).get(PengeluaranViewModel.class);
        pglrVM.getDaftarPengeluaran().observe(this, new Observer<List<Pengeluaran>>() {
            @Override
            public void onChanged(List<Pengeluaran> pglrs) {
                adapterPengeluaran.setDaftarPengeluaran(pglrs);
            }
        });

        ItemTouchHelper helperPengeluaran = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posisiPengeluaran = viewHolder.getAdapterPosition();
                Pengeluaran pglr = adapterPengeluaran.getPengeluaranAtPosition(posisiPengeluaran);
                if(direction == ItemTouchHelper.LEFT){
                    Toast.makeText(PengeluaranActivity.this, "Pengeluaran " + pglr.getNamaPengeluaran() + " telah dihapus", Toast.LENGTH_LONG).show();
                    pglrVM.deletePengeluaran(pglr);
                }
            }
        });
        helperPengeluaran.attachToRecyclerView(rvPengeluaran);
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data){
        super.onActivityResult(reqCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Pengeluaran pglr = (Pengeluaran) data.getSerializableExtra("PENGELUARAN");
            if(reqCode == REQUEST_TAMBAH){
                pglrVM.insertPengeluaran(pglr);
            }
        }
        rvPengeluaran.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pengeluaran, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.pengeluaran) {
            Intent inputPengeluaran = new Intent(PengeluaranActivity.this, InputPengeluaranActivity.class);
            startActivity(inputPengeluaran);
            return true;
        }*/
        if (id == R.id.delAllPengeluaran) {
            pglrVM.deleteAllPengeluaran();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_pengeluaran);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
}