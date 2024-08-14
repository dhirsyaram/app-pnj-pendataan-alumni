package dts.pnj.pendataanalumni;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class TambahDataFragment extends Fragment {

    MaterialButton btnCancelData, btnSaveData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_data, container, false);

        // Update toolbar and bottom navigation visibility
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setToolbarTitle("Tambah Data");
            mainActivity.setBottomNavigationViewVisibility(false);  // Hide BottomNavigationView
        }

        btnCancelData = view.findViewById(R.id.btn_cancel_data);
        btnSaveData = view.findViewById(R.id.btn_save_data);

        btnSaveData.setOnClickListener(v -> saveData());
        btnCancelData.setOnClickListener(v -> cancelData());

        return view;
    }

    private void saveData() {
        // Implement your save data logic here
        // For demonstration, you might want to navigate back to HomeFragment
        navigateToHomeFragment();
    }

    private void cancelData() {
        // Implement your cancel data logic here
        // Navigate back to HomeFragment
        navigateToHomeFragment();
    }

    private void navigateToHomeFragment() {
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setToolbarTitle("Home");
            mainActivity.setBottomNavigationViewVisibility(true);  // Show BottomNavigationView

            // Navigate to HomeFragment
            Fragment homeFragment = new HomeFragment();
            requireFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setBottomNavigationViewVisibility(true);
        }
    }
}