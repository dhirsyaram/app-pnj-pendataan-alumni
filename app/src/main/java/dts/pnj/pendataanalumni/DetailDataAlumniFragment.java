package dts.pnj.pendataanalumni;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailDataAlumniFragment extends Fragment {

    private static final String ARG_ALUMNI = "arg_alumni";
    private Alumni alumni;

    public static DetailDataAlumniFragment newInstance(Alumni alumni) {
        DetailDataAlumniFragment fragment = new DetailDataAlumniFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ALUMNI, alumni);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            alumni = getArguments().getParcelable(ARG_ALUMNI);
        }

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_data_alumni, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        mainActivity.setToolbarVisibility(true);
        mainActivity.setToolbarBackButton(true);
        mainActivity.setBottomNavigationViewVisibility(false);
        mainActivity.setToolbarTitle("Detail Data Alumni");

        EditText edtNim = view.findViewById(R.id.edt_nim_data_detail);
        EditText edtNama = view.findViewById(R.id.edt_name_data_detail);
        EditText edtTempatLahir = view.findViewById(R.id.edt_tempat_lahir_detail);
        EditText edtTglLahir = view.findViewById(R.id.edt_tanggal_lahir_detail);
        EditText edtAlamat = view.findViewById(R.id.edt_alamat_detail);
        EditText edtAgama = view.findViewById(R.id.edt_agama_detail);
        EditText edtTlpHp = view.findViewById(R.id.edt_telp_hp_detail);
        EditText edtThnMasuk = view.findViewById(R.id.edt_tahun_masuk_detail);
        EditText edtThnLulus = view.findViewById(R.id.edt_tahun_lulus_detail);
        EditText edtPekerjaan = view.findViewById(R.id.edt_pekerjaan_detail);
        EditText edtJabatan = view.findViewById(R.id.edt_jabatan_detail);

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

        return view;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setToolbarVisibility(true);
        mainActivity.setToolbarBackButton(true);
        mainActivity.setBottomNavigationViewVisibility(false);
        mainActivity.setToolbarTitle("Detail Data Alumni");
        Log.d("DetailDataAlumniFragment", "onViewCreated called");
    }
}