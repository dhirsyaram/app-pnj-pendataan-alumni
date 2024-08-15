package dts.pnj.pendataanalumni;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataAlumniActivity extends ToolBarActivity {

    private DatabaseHelper dbHelper;
    private DataAdapter alumniAdapter;
    private final List<Alumni> alumniList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_alumni);

        dbHelper = new DatabaseHelper(this);

        setupToolbar(R.id.toolbarDataAlumni, "Data Alumni", true);
        toolbar.setNavigationOnClickListener(v -> {
            super.onBackPressed();
        });

        RecyclerView recyclerView = findViewById(R.id.rv_data_alumni);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        alumniAdapter = new DataAdapter(alumniList, alumni -> DetailDataAlumniActivity.start(this, alumni));

        recyclerView.setAdapter(alumniAdapter);

        loadData();
    }

    private void loadData() {
        List<Alumni> alumniListFromDb = dbHelper.getAllAlumni();

        Log.d("DataAlumniActivity", "Number of alumni: " + alumniListFromDb.size());
        alumniList.clear();
        alumniList.addAll(alumniListFromDb);

        alumniAdapter.notifyDataSetChanged();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back button is disabled in this screen", Toast.LENGTH_SHORT).show();
    }
}