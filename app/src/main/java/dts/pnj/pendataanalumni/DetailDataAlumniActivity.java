package dts.pnj.pendataanalumni;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailDataAlumniActivity extends ToolBarActivity {

    private static final String EXTRA_ALUMNI = "extra_alumni";
    private Alumni alumni;

    public static void start(AppCompatActivity activity, Alumni alumni) {
        Intent intent = new Intent(activity, DetailDataAlumniActivity.class);
        intent.putExtra(EXTRA_ALUMNI, alumni);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_alumni);

        if (getIntent() != null && getIntent().hasExtra(EXTRA_ALUMNI)) {
            alumni = getIntent().getParcelableExtra(EXTRA_ALUMNI);
        }

        setupToolbar(R.id.toolbarDetailAlumni, "Detail Data Alumni", true);

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
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        // Do nothing to disable the back button
        Toast.makeText(this, "Back button is disabled in this screen", Toast.LENGTH_SHORT).show();
    }
}