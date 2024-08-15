package dts.pnj.pendataanalumni;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TambahDataFragment extends Fragment {

    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah_data, container, false);

        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setToolbarTitle("Tambah Data");
            mainActivity.setBottomNavigationViewVisibility(false);
        }

        EditText edtNim = view.findViewById(R.id.edt_nim_data);
        EditText edtNama = view.findViewById(R.id.edt_name_data);
        EditText edtTempatLahir = view.findViewById(R.id.edt_tempat_lahir);
        EditText edtTglLahir = view.findViewById(R.id.edt_tanggal_lahir);
        EditText edtAlamat = view.findViewById(R.id.edt_alamat);
        EditText edtAgama = view.findViewById(R.id.edt_agama);
        EditText edtTlpHp = view.findViewById(R.id.edt_telp_hp);
        EditText edtThnMasuk = view.findViewById(R.id.edt_tahun_masuk);
        EditText edtThnLulus = view.findViewById(R.id.edt_tahun_lulus);
        EditText edtPekerjaan = view.findViewById(R.id.edt_pekerjaan);
        EditText edtJabatan = view.findViewById(R.id.edt_jabatan);

        MaterialButton btnCancelData = view.findViewById(R.id.btn_cancel_data);
        MaterialButton btnSaveData = view.findViewById(R.id.btn_save_data);

        edtTglLahir.setOnClickListener(v -> showDatePickerDialog(edtTglLahir));
        edtThnMasuk.setOnClickListener(v -> showYearPickerDialog(edtThnMasuk));
        edtThnLulus.setOnClickListener(v -> showYearPickerDialog(edtThnLulus));

        btnSaveData.setOnClickListener(v -> saveData(edtNim, edtNama, edtAgama, edtTempatLahir, edtTglLahir, edtAlamat, edtTlpHp, edtPekerjaan, edtJabatan, edtThnMasuk, edtThnLulus));
        btnCancelData.setOnClickListener(v -> cancelData());

        return view;
    }

    private void saveData(EditText edtNim, EditText edtNama, EditText edtAgama, EditText edtTempatLahir,
                          EditText edtTglLahir, EditText edtAlamat, EditText edtTlpHp, EditText edtPekerjaan,
                          EditText edtJabatan, EditText edtThnMasuk, EditText edtThnLulus) {

        String nama = edtNama.getText().toString().trim();
        String nim = edtNim.getText().toString().trim();
        String agama = edtAgama.getText().toString().trim();
        String tempatLahir = edtTempatLahir.getText().toString().trim();
        String alamat = edtAlamat.getText().toString().trim();
        String tlpHp = edtTlpHp.getText().toString().trim();
        String pekerjaan = edtPekerjaan.getText().toString().trim();
        String jabatan = edtJabatan.getText().toString().trim();
        String tanggalLahir = edtTglLahir.getText().toString().trim();
        String tahunMasuk = edtThnMasuk.getText().toString().trim();
        String tahunLulus = edtThnLulus.getText().toString().trim();

        if (nama.isEmpty() || nim.isEmpty() || tanggalLahir.isEmpty() ||
                tahunMasuk.isEmpty() || tahunLulus.isEmpty()) {
            Toast.makeText(requireContext(), "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper = new DatabaseHelper(requireContext());
        long result = dbHelper.addAlumni(nim, nama, tempatLahir, tanggalLahir, alamat, agama, tlpHp, tahunMasuk, tahunLulus, pekerjaan, jabatan);

        if (result == -1) {
            Toast.makeText(requireContext(), "Gagal menyimpan data!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
            navigateToHomeFragment();
        }

        // Reset semua input setelah menyimpan data
        edtNama.setText("");
        edtNim.setText("");
        edtTglLahir.setText("");
        edtThnMasuk.setText("");
        edtThnLulus.setText("");
        edtJabatan.setText("");
        edtPekerjaan.setText("");
        edtTlpHp.setText("");
        edtAlamat.setText("");
        edtTempatLahir.setText("");
        edtAgama.setText("");

    }

    private void cancelData() {
        navigateToHomeFragment();
    }

    private void navigateToHomeFragment() {
        if (getActivity() instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setToolbarTitle("Home");
            mainActivity.setBottomNavigationViewVisibility(true);

            Fragment homeFragment = new HomeFragment();
            requireFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        }
    }

    private void showDatePickerDialog(final EditText editText) {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Pilih Tanggal")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(selection);
            String selectedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime());
            editText.setText(selectedDate);
        });

        datePicker.show(getParentFragmentManager(), "DATE_PICKER");
    }

    private void showYearPickerDialog(final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        long currentYearInMillis = calendar.getTimeInMillis();

        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Pilih Tahun")
                .setSelection(currentYearInMillis)
                .build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            Calendar selectedCalendar = Calendar.getInstance();
            selectedCalendar.setTimeInMillis(selection);
            int selectedYear = selectedCalendar.get(Calendar.YEAR);

            editText.setText(String.valueOf(selectedYear));
        });

        datePicker.show(getParentFragmentManager(), "YEAR_PICKER");
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