package dts.pnj.pendataanalumni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TambahDataActivity extends ToolBarActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        setupToolbar(R.id.toolbarTambah, "Tambah Data", true);
        toolbar.setNavigationOnClickListener(v -> {
            super.onBackPressed();
        });

        EditText edtNim = findViewById(R.id.edt_nim_data);
        EditText edtNama = findViewById(R.id.edt_name_data);
        EditText edtTempatLahir = findViewById(R.id.edt_tempat_lahir);
        EditText edtTglLahir = findViewById(R.id.edt_tanggal_lahir);
        EditText edtAlamat = findViewById(R.id.edt_alamat);
        EditText edtAgama = findViewById(R.id.edt_agama);
        EditText edtTlpHp = findViewById(R.id.edt_telp_hp);
        EditText edtThnMasuk = findViewById(R.id.edt_tahun_masuk);
        EditText edtThnLulus = findViewById(R.id.edt_tahun_lulus);
        EditText edtPekerjaan = findViewById(R.id.edt_pekerjaan);
        EditText edtJabatan = findViewById(R.id.edt_jabatan);

        MaterialButton btnCancelData = findViewById(R.id.btn_cancel_data);
        MaterialButton btnSaveData = findViewById(R.id.btn_save_data);

        edtTglLahir.setOnClickListener(v -> showDatePickerDialog(edtTglLahir));
        edtThnMasuk.setOnClickListener(v -> showYearPickerDialog(edtThnMasuk));
        edtThnLulus.setOnClickListener(v -> showYearPickerDialog(edtThnLulus));

        btnSaveData.setOnClickListener(v -> saveData(edtNim, edtNama, edtAgama, edtTempatLahir, edtTglLahir, edtAlamat, edtTlpHp, edtPekerjaan, edtJabatan, edtThnMasuk, edtThnLulus));
        btnCancelData.setOnClickListener(v -> cancelData());
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
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        long result = dbHelper.addAlumni(nim, nama, tempatLahir, tanggalLahir, alamat, agama, tlpHp, tahunMasuk, tahunLulus, pekerjaan, jabatan);

        if (result == -1) {
            Toast.makeText(this, "Gagal menyimpan data!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
            navigateToHomeActivity();
        }

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
        navigateToHomeActivity();
    }

    private void navigateToHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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

        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
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

        datePicker.show(getSupportFragmentManager(), "YEAR_PICKER");
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back button is disabled in this screen", Toast.LENGTH_SHORT).show();
    }
}
