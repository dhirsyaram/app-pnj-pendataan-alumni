package dts.pnj.pendataanalumni;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataAlumniFragment extends Fragment {

    private DatabaseHelper dbHelper;
    private DataAdapter alumniAdapter;
    private final List<Alumni> alumniList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_alumni, container, false);

        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setToolbarTitle("Data Alumni");
            mainActivity.setToolbarVisibility(true);
            mainActivity.setToolbarBackButton(true);
            mainActivity.setBottomNavigationViewVisibility(false);
        }

        RecyclerView recyclerView = view.findViewById(R.id.rv_data_alumni);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        alumniAdapter = new DataAdapter(alumniList, alumni -> {
            DetailDataAlumniFragment detailFragment = DetailDataAlumniFragment.newInstance(alumni);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        recyclerView.setAdapter(alumniAdapter);

        dbHelper = new DatabaseHelper(getContext());
        loadData();

        return view;
    }

    private void loadData() {
        List<Alumni> alumniListFromDb = dbHelper.getAllAlumni();

        Log.d("DataAlumniFragment", "Number of alumni: " + alumniListFromDb.size());
        alumniList.clear();
        alumniList.addAll(alumniListFromDb);

        alumniAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setToolbarVisibility(true);
            mainActivity.setToolbarBackButton(false);
            mainActivity.setBottomNavigationViewVisibility(true);
        }
    }

}