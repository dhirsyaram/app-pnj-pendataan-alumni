# Aplikasi Pendataan Alumni

## Gambaran Umum
**Aplikasi Pendataan Alumni** ini adalah sebuah aplikasi <i>mobile</i> yang di design dengan melakukan manajemen
data alumni. Aplikasi ini juga menggunakan dengan bahasa pemrograman Java dan XML, lalu menggunakan API
dari Spaceflight News untuk menampilkan daftar berita tentang Antariksa dan bisa melihat detail beritanya. Data
alumni dalam aplikasi pun ditampilkan secara daftar dalam RecyclerView dan bisa ditampilkan detail dari data tersebut.
Aplikasi ini bisa dijalankan dengan minimal SDK 24 (Android 7) dan maksimal SDK 34 (Android 14) tetapi aplikasi ini 
lebih optimal dengan maksimal SDK 32 (Android 12).

## Ketersediaan Fitur
- **Login**: Input Email dan Password menggunakan "EditText" agar melakukan validasi pengguna 
- **Daftar Berita**: Tampilkan daftar berita mengenai Antariksa yang digunakan RecylerView dalam bentuk Grid dan bisa dilihat detail Berita. API yang digunakan dari Spaceflight News
- **Tambah Data**: Dapat menambahkan sebuah data dan data tersebut disimpan menggunakan SQLite
- **Manajemen Data**: Data akan ditampilkan menggunakan RecyclerView dan bisa dilakukan perubahan data maupun menghapus data tersebut.

## API
Aplikasi ini menggunakan API dari Spaceflight News yang dapay diakses [disini](https://spaceflightnewsapi.net/).
API ini bisa mengambil data seperti blogspot, artikel, dan lain secara up-to-date yang tersedia.

## Infrastruktur Pengembangan
- **Bahasa Pemrograman**: Java
- **UI**: XML
- **Minimal SDK**: 24
- **Maksimal SDK**: 34
- **Optimal Maksimal SDK**: 32

## Kelebihan & Kekurangan
- **Kelebihan**
  1. Dapat menampilkan berita dengan penggunaan API.
  2. Dapat melakukan manajemen data seperti menambah, menghapus, memodifikasi, dan menampilkan.
  3. APlikasi bisa dilakukan sebuah Login dan Logout bahkan data pengguna aplikasi ditampilkan pada halaman Profile.
  4. Penggunaan Database secara Lokal seperti SQLite, SharedPreferences, dan Internal Storage.
  5. Implementasi Tampilkan daftar dengan RecyclerView.
     
- **Kekurangan**
  1. Aplikasi diperlukan koneksi dengan Internet.
  2. Aplikasi belum bisa memuat data secara <i>real-time</i> sehingga perlu kembali ke halaman lainnya.
  3. Bagian Login ini sudah diatur secara statis (hardcoded) dan tidak bisa daftarkan pengguna sehingga perlu diatur dalam pengkodean.
  4. Tampilan masih sederhana.
 
  ## Video Demo
  Silakan dilihat video demo dari aplikasi tersebut: [Video Demo](https://github.com/user-attachments/assets/32e18ed8-305f-43dc-a1fe-adc37dbc6d91)


  ## Cuplikan Layar
