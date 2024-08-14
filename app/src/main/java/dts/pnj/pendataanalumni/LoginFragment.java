package dts.pnj.pendataanalumni;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoginFragment extends Fragment {

    private EditText edtEmail, edtPass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        edtEmail = view.findViewById(R.id.edt_email);
        edtPass = view.findViewById(R.id.edt_password);
        MaterialButton btnLogin = view.findViewById(R.id.btn_login);


        btnLogin.setOnClickListener(click -> {
            processLogin();
        });

        return view;
    }

    private void processLogin() {
        String email = edtEmail.getText().toString();
        String pass = edtPass.getText().toString();

        String setEmail = "dhirsya@education.com";
        String setPassword = "belajarGiat123";

        if (email.equals(setEmail) && pass.equals(setPassword)) {

            String setNIM = "50421369";
            String setName = "Dhirsya Ramadhan Pattah";
            String setClass = "3IA20";
            saveInternalStorage(setEmail, setNIM, setName, setClass);

            ((MainActivity) requireActivity()).navigateToHomeFragment();

            Toast.makeText(getActivity(), "Berhasil Login", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Gagal Login", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveInternalStorage(String setEmail, String setNIM, String setName, String setClass) {
        String FILENAME = "userfile.txt";
        String fileContents = "Email: "+setEmail+"\nNIM: "+setNIM+"\nName: "+setName+"\nKelas: "+setClass;

        File file = new File(requireActivity().getFilesDir(), FILENAME);
        try (FileOutputStream fos = new FileOutputStream(file)){
            fos.write(fileContents.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}