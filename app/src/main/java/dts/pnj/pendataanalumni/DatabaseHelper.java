package dts.pnj.pendataanalumni;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AlumniData.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "alumni";
    public static final String COLUMN_NIM = "nim";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TEMPAT_LAHIR = "tempat_lahir";
    public static final String COLUMN_TGL_LAHIR = "tanggal_lahir";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String COLUMN_AGAMA = "agama";
    public static final String COLUMN_TELP_HP = "telp_hp";
    public static final String COLUMN_TAHUN_MASUK = "tahun_masuk";
    public static final String COLUMN_TAHUN_LULUS = "tahun_lulus";
    public static final String COLUMN_PEKERJAAN = "pekerjaan";
    public static final String COLUMN_JABATAN = "jabatan";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALUMNI_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NIM + " TEXT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_TEMPAT_LAHIR + " TEXT, " +
                COLUMN_TGL_LAHIR + " TEXT, " +
                COLUMN_ALAMAT + " TEXT, " +
                COLUMN_AGAMA + " TEXT, " +
                COLUMN_TELP_HP + " TEXT, " +
                COLUMN_TAHUN_MASUK + " TEXT, " +
                COLUMN_TAHUN_LULUS + " TEXT, " +
                COLUMN_PEKERJAAN + " TEXT, " +
                COLUMN_JABATAN + " TEXT)";
        db.execSQL(CREATE_ALUMNI_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addAlumni(String nim, String name, String tempatLahir, String tanggalLahir,
                          String alamat, String agama, String telpHp, String tahunMasuk,
                          String tahunLulus, String pekerjaan, String jabatan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NIM, nim);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TEMPAT_LAHIR, tempatLahir);
        values.put(COLUMN_TGL_LAHIR, tanggalLahir);
        values.put(COLUMN_ALAMAT, alamat);
        values.put(COLUMN_AGAMA, agama);
        values.put(COLUMN_TELP_HP, telpHp);
        values.put(COLUMN_TAHUN_MASUK, tahunMasuk);
        values.put(COLUMN_TAHUN_LULUS, tahunLulus);
        values.put(COLUMN_PEKERJAAN, pekerjaan);
        values.put(COLUMN_JABATAN, jabatan);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public List<Alumni> getAllAlumni() {
        List<Alumni> alumniList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        String[] columnNames = cursor.getColumnNames();
        Log.d("DatabaseHelper", "Columns: " + Arrays.toString(columnNames));

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_NIM);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int tempatLahirIndex = cursor.getColumnIndex(COLUMN_TEMPAT_LAHIR);
            int tanggalLahirIndex = cursor.getColumnIndex(COLUMN_TGL_LAHIR);
            int alamatIndex = cursor.getColumnIndex(COLUMN_ALAMAT);
            int agamaIndex = cursor.getColumnIndex(COLUMN_AGAMA);
            int telpHpIndex = cursor.getColumnIndex(COLUMN_TELP_HP);
            int tahunMasukIndex = cursor.getColumnIndex(COLUMN_TAHUN_MASUK);
            int tahunLulusIndex = cursor.getColumnIndex(COLUMN_TAHUN_LULUS);
            int pekerjaanIndex = cursor.getColumnIndex(COLUMN_PEKERJAAN);
            int jabatanIndex = cursor.getColumnIndex(COLUMN_JABATAN);

            if (idIndex == -1 || nameIndex == -1 || tempatLahirIndex == -1 ||
                    tanggalLahirIndex == -1 || alamatIndex == -1 || agamaIndex == -1 ||
                    telpHpIndex == -1 || tahunMasukIndex == -1 || tahunLulusIndex == -1 ||
                    pekerjaanIndex == -1 || jabatanIndex == -1) {
                Log.e("DatabaseHelper", "Column not found. Check your column names.");
                return alumniList;
            }

            do {
                String nim = cursor.getString(idIndex);
                String name = cursor.getString(nameIndex);
                String tempatLahir = cursor.getString(tempatLahirIndex);
                String tanggalLahir = cursor.getString(tanggalLahirIndex);
                String alamat = cursor.getString(alamatIndex);
                String agama = cursor.getString(agamaIndex);
                String telpHp = cursor.getString(telpHpIndex);
                String tahunMasuk = cursor.getString(tahunMasukIndex);
                String tahunLulus = cursor.getString(tahunLulusIndex);
                String pekerjaan = cursor.getString(pekerjaanIndex);
                String jabatan = cursor.getString(jabatanIndex);

                Alumni alumni = new Alumni(nim, name, tempatLahir, tanggalLahir, alamat, agama, telpHp, tahunMasuk, tahunLulus, pekerjaan, jabatan);
                alumniList.add(alumni);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return alumniList;
    }
}