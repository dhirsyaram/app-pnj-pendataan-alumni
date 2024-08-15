package dts.pnj.pendataanalumni.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import dts.pnj.pendataanalumni.activity.MainActivity;
import dts.pnj.pendataanalumni.R;

public class ProfileFragment extends Fragment {

    private static final String FILENAME = "userfile.txt";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        String fileContents = readInternalStorage();
        parseFileContents(fileContents, view);

        MaterialButton btnLogout = view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v -> showLogoutConfirmationDialog());

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setToolbarTitle("Profile");
        }

        return view;
    }

    private String readInternalStorage() {
        File file = new File(requireActivity().getFilesDir(), FILENAME);
        StringBuilder fileContents = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(file)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                fileContents.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return fileContents.toString();
    }

    private void parseFileContents(String fileContents, View view) {
        String[] lines = fileContents.split("\n");
        if (lines.length >= 4) {
            String email = lines[0].replace("Email: ", "");
            String nim = lines[1].replace("NIM: ", "");
            String name = lines[2].replace("Name: ", "");
            String className = lines[3].replace("Kelas: ", "");

            updateUI(email, nim, name, className, view);
        }
    }

    private void updateUI(String email, String nim, String name, String className, View view) {
        if (view != null) {
            EditText edtEmail = view.findViewById(R.id.edt_email_profile);
            EditText edtNim = view.findViewById(R.id.edt_nim);
            EditText edtName = view.findViewById(R.id.edt_name);
            EditText edtClass = view.findViewById(R.id.edt_class);

            if (edtEmail != null) edtEmail.setText(email);
            if (edtNim != null) edtNim.setText(nim);
            if (edtName != null) edtName.setText(name);
            if (edtClass != null) edtClass.setText(className);
        }
    }

    private void showLogoutConfirmationDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> handleLogout())
                .setNegativeButton("No", null)
                .show();
    }

    private void handleLogout() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        hapusFile();

        Intent intent = new Intent(requireActivity(), MainActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    private void hapusFile() {
        File file = new File(requireActivity().getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }
}