package dts.pnj.pendataanalumni.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import dts.pnj.pendataanalumni.R;
import dts.pnj.pendataanalumni.data.Alumni;
import dts.pnj.pendataanalumni.data.DatabaseHelper;

public class DetailDataAlumniActivity extends ToolBarActivity {

    private static final String EXTRA_ALUMNI = "extra_alumni";
    private Alumni alumni;
    private DatabaseHelper databaseHelper;

    public static void start(AppCompatActivity activity, Alumni alumni) {
        Intent intent = new Intent(activity, DetailDataAlumniActivity.class);
        intent.putExtra(EXTRA_ALUMNI, alumni);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_alumni);

        databaseHelper = new DatabaseHelper(this);

        if (getIntent() != null && getIntent().hasExtra(EXTRA_ALUMNI)) {
            alumni = getIntent().getParcelableExtra(EXTRA_ALUMNI);
        }

        setupToolbar(R.id.toolbarDetailAlumni, "Detail Data Alumni", true);
        toolbar.setNavigationOnClickListener(v -> super.onBackPressed());

        EditText edtNim = findViewById(R.id.edt_nim_data_detail);
        EditText edtNama = findViewById(R.id.edt_name_data_detail);
        EditText edtTempatLahir = findViewById(R.id.edt_tempat_lahir_detail);
        EditText edtTglLahir = findViewById(R.id.edt_tanggal_lahir_detail);
        EditText edtAlamat = findViewById(R.id.edt_alamat_detail);
        EditText edtAgama = findViewById(R.id.edt_agama_detail);
        EditText edtTlpHp = findViewById(R.id.edt_telp_hp_detail);
        EditText edtThnMasuk = findViewById(R.id.edt_tahun_masuk_detail);
        EditText edtThnLulus = findViewById(R.id.edt_tahun_lulus_detail);
        EditText edtPekerjaan = findViewById(R.id.edt_pekerjaan_detail);
        EditText edtJabatan = findViewById(R.id.edt_jabatan_detail);

        if (alumni != null) {
            edtNim.setText(alumni.getNim());
            edtNama.setText(alumni.getNama());
            edtTempatLahir.setText(alumni.getTempatLahir());
            edtTglLahir.setText(alumni.getTanggalLahir());
            edtAlamat.setText(alumni.getAlamat());
            edtAgama.setText(alumni.getAgama());
            edtTlpHp.setText(alumni.getTlpHp());
            edtThnMasuk.setText(alumni.getTahunMasuk());
            edtThnLulus.setText(alumni.getTahunLulus());
            edtPekerjaan.setText(alumni.getPekerjaan());
            edtJabatan.setText(alumni.getJabatan());
        }

        Button btnUpdate = findViewById(R.id.btn_save_detail_data);
        Button btnDelete = findViewById(R.id.btn_delete_data_detail);

        btnUpdate.setOnClickListener(v -> {
            String newNim = edtNim.getText().toString();
            String name = edtNama.getText().toString();
            String tempatLahir = edtTempatLahir.getText().toString();
            String tanggalLahir = edtTglLahir.getText().toString();
            String alamat = edtAlamat.getText().toString();
            String agama = edtAgama.getText().toString();
            String telpHp = edtTlpHp.getText().toString();
            String tahunMasuk = edtThnMasuk.getText().toString();
            String tahunLulus = edtThnLulus.getText().toString();
            String pekerjaan = edtPekerjaan.getText().toString();
            String jabatan = edtJabatan.getText().toString();

            if (alumni != null) {
                long result = databaseHelper.updateAlumni(alumni.getNim(), newNim, name, tempatLahir, tanggalLahir, alamat, agama, telpHp, tahunMasuk, tahunLulus, pekerjaan, jabatan);
                if (result > 0) {
                    Toast.makeText(this, "Alumni updated successfully", Toast.LENGTH_SHORT).show();
                    alumni.setNim(newNim);  // Update the alumni object with the new NIM
                } else {
                    Toast.makeText(this, "Failed to update alumni", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(v -> {
            if (alumni != null) {
                new AlertDialog.Builder(this)
                        .setTitle("Delete Confirmation")
                        .setMessage("Are you sure you want to delete this alumni?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            int result = databaseHelper.deleteAlumni(alumni.getNim());
                            if (result > 0) {
                                Toast.makeText(this, "Alumni deleted successfully", Toast.LENGTH_SHORT).show();
                                finish();  // Close the activity after deletion
                            } else {
                                Toast.makeText(this, "Failed to delete alumni", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back button is disabled in this screen", Toast.LENGTH_SHORT).show();
    }
}
