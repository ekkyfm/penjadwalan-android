package com.denirohi.appexample.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by derohimat on 05/03/16.
 */
public class Cuaca implements Parcelable {
    public Cuaca() {

    }

    private String lokasi;
    private String suhuMax;
    private String suhuMin;
    private String suhu;
    private String tanggal;
    private String cuaca;
    private String kegiatan;

    protected Cuaca(Parcel in) {
        lokasi = in.readString();
        suhuMax = in.readString();
        suhuMin = in.readString();
        suhu = in.readString();
        tanggal = in.readString();
        cuaca = in.readString();
        kegiatan = in.readString();
    }

    public static final Creator<Cuaca> CREATOR = new Creator<Cuaca>() {
        @Override
        public Cuaca createFromParcel(Parcel in) {
            return new Cuaca(in);
        }

        @Override
        public Cuaca[] newArray(int size) {
            return new Cuaca[size];
        }
    };

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getSuhuMax() {
        return suhuMax;
    }

    public void setSuhuMax(String suhuMax) {
        this.suhuMax = suhuMax;
    }

    public String getSuhuMin() {
        return suhuMin;
    }

    public void setSuhuMin(String suhuMin) {
        this.suhuMin = suhuMin;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getCuaca() {
        return cuaca;
    }

    public void setCuaca(String cuaca) {
        this.cuaca = cuaca;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lokasi);
        dest.writeString(suhuMax);
        dest.writeString(suhuMin);
        dest.writeString(suhu);
        dest.writeString(tanggal);
        dest.writeString(cuaca);
        dest.writeString(kegiatan);
    }
}
