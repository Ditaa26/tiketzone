package com.example.myapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tbl_travel")
public class ModelDatabase implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "nama_penumpang")
    private String namaPenumpang;

    @ColumnInfo(name = "keberangkatan")
    private String keberangkatan;

    @ColumnInfo(name = "tujuan")
    private String tujuan;

    @ColumnInfo(name = "tanggal")
    private String tanggal;

    @ColumnInfo(name = "harga_tiket")
    private int hargaTiket;

    @ColumnInfo(name = "anak_anak")
    private int anakAnak;

    @ColumnInfo(name = "dewasa")
    private int dewasa;

    @ColumnInfo(name = "nomor_telepon")
    private String nomorTelepon;

    @ColumnInfo(name = "kelas")
    private String kelas;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "nama_pengguna")  // Ganti dari "nama_penumpang"
    private String namaPengguna;  // Ubah variabel

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    // Getters and Setters for all fields
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNamaPenumpang() {
        return namaPenumpang;
    }

    public void setNamaPenumpang(String namaPenumpang) {
        this.namaPenumpang = namaPenumpang;
    }
    public String getKeberangkatan() {
        return keberangkatan;
    }

    public void setKeberangkatan(String keberangkatan) {
        this.keberangkatan = keberangkatan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getHargaTiket() {
        return hargaTiket;
    }

    public void setHargaTiket(int hargaTiket) {
        this.hargaTiket = hargaTiket;
    }

    public int getAnakAnak() {
        return anakAnak;
    }

    public void setAnakAnak(int anakAnak) {
        this.anakAnak = anakAnak;
    }

    public int getDewasa() {
        return dewasa;
    }

    public void setDewasa(int dewasa) {
        this.dewasa = dewasa;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

}
