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

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Set up the toolbar with title "Data Alumni" and no back button (since it's a main activity)
        setupToolbar(R.id.toolbarDataAlumni, "Data Alumni", true);

        // Configure the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv_data_alumni);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up the adapter with a click listener to open DetailDataAlumniActivity
        alumniAdapter = new DataAdapter(alumniList, alumni -> DetailDataAlumniActivity.start(this, alumni));

        recyclerView.setAdapter(alumniAdapter);

        // Load the data from the database
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
        // Do nothing to disable the back button
        Toast.makeText(this, "Back button is disabled in this screen", Toast.LENGTH_SHORT).show();
    }
}