package com.denirohi.appexample.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by derohimat on 05/03/16.
 */
public class Berita implements Parcelable {
    private String judul;
    private String alamat;
    private String gambar;
    private String tanggal;
    private String deskripsi;
    private String isi;

    public Berita() {

    }

    protected Berita(Parcel in) {
        judul = in.readString();
        alamat = in.readString();
        gambar = in.readString();
        tanggal = in.readString();
        deskripsi = in.readString();
        isi = in.readString();
    }

    public static final Creator<Berita> CREATOR = new Creator<Berita>() {
        @Override
        public Berita createFromParcel(Parcel in) {
            return new Berita(in);
        }

        @Override
        public Berita[] newArray(int size) {
            return new Berita[size];
        }
    };

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
        dest.writeString(alamat);
        dest.writeString(gambar);
        dest.writeString(tanggal);
        dest.writeString(deskripsi);
        dest.writeString(isi);
    }
}
