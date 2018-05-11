package com.christinehakimideapark.chip.laporan;

public class konfigurasi {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_GET_ALL = "http://36.67.233.109:5152/Android/Laporan/tampilSemua.php";
    public static final String URL_GET_DIV = "http://36.67.233.109:5152/Android/Laporan/tampilDivisi.php";
    public static final String URL_GET_HAR = "http://36.67.233.109:5152/Android/Laporan/tampilHarian.php";
    //public static final String URL_GET_EMP = "http://192.168.3.109:8080/Android/pegawai/tampilPgw.php?id=";



    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "name";
    public static final String KEY_EMP_POSISI = "desg"; //desg itu variabel untuk posisi
    public static final String KEY_EMP_GAJIH = "salary"; //salary itu variabel untuk gajih
    public static final String KEY_PASS = "pass";

    public static final String KEY_DIV = "divisi";
    public static final String KEY_TANGGAL = "tanggal";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_POSISI = "desg";
    public static final String TAG_GAJIH = "salary";
    public static final String TAG_PASS = "pass";

    public static final String TAG_SOURCE ="Source";
    public static final String TAG_TANGGAL = "Tanggal";
    public static final String TAG_TOTAL = "Total";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";
    public static final String INSOURCE = "inSource";
    public static final String TGL1 = "tanggal1";
    public static final String TGL2 = "tanggal2";

}

