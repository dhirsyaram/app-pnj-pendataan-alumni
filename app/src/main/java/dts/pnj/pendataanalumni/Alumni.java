package dts.pnj.pendataanalumni;

import android.os.Parcel;
import android.os.Parcelable;

public class Alumni implements Parcelable {
    private String nim;
    private String nama;
    private String tempatLahir;
    private String tanggalLahir;
    private String alamat;
    private String agama;
    private String tlpHp;
    private String tahunMasuk;
    private String tahunLulus;
    private String pekerjaan;
    private String jabatan;

    public Alumni(String nim, String nama, String tempatLahir, String tanggalLahir, String alamat, String agama, String tlpHp, String tahunMasuk, String tahunLulus, String pekerjaan, String jabatan) {
        this.nim = nim;
        this.nama = nama;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.agama = agama;
        this.tlpHp = tlpHp;
        this.tahunMasuk = tahunMasuk;
        this.tahunLulus = tahunLulus;
        this.pekerjaan = pekerjaan;
        this.jabatan = jabatan;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getTlpHp() {
        return tlpHp;
    }

    public void setTlpHp(String tlpHp) {
        this.tlpHp = tlpHp;
    }

    public String getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(String tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public String getTahunLulus() {
        return tahunLulus;
    }

    public void setTahunLulus(String tahunLulus) {
        this.tahunLulus = tahunLulus;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    protected Alumni(Parcel in) {
        nim = in.readString();
        nama = in.readString();
        tempatLahir = in.readString();
        tanggalLahir = in.readString();
        alamat = in.readString();
        agama = in.readString();
        tlpHp = in.readString();
        tahunMasuk = in.readString();
        tahunLulus = in.readString();
        pekerjaan = in.readString();
        jabatan = in.readString();
    }

    public static final Creator<Alumni> CREATOR = new Creator<Alumni>() {
        @Override
        public Alumni createFromParcel(Parcel in) {
            return new Alumni(in);
        }

        @Override
        public Alumni[] newArray(int size) {
            return new Alumni[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nim);
        dest.writeString(nama);
        dest.writeString(tempatLahir);
        dest.writeString(tanggalLahir);
        dest.writeString(alamat);
        dest.writeString(agama);
        dest.writeString(tlpHp);
        dest.writeString(tahunMasuk);
        dest.writeString(tahunLulus);
        dest.writeString(pekerjaan);
        dest.writeString(jabatan);
    }
}
