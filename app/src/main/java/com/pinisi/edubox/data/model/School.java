package com.pinisi.edubox.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ekky on 11/05/16.
 */
public class School implements Parcelable{
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("url_live")
    @Expose
    private String urlLive;
    @SerializedName("ip_address_live")
    @Expose
    private Object ipAddressLive;
    @SerializedName("url_local")
    @Expose
    private String urlLocal;
    @SerializedName("ip_address_local")
    @Expose
    private String ipAddressLocal;
    @SerializedName("ip_address")
    @Expose
    private Object ipAddress;

    protected School(Parcel in) {
        schoolName = in.readString();
        urlLive = in.readString();
        urlLocal = in.readString();
        ipAddressLocal = in.readString();
    }

    public static final Creator<School> CREATOR = new Creator<School>() {
        @Override
        public School createFromParcel(Parcel in) {
            return new School(in);
        }

        @Override
        public School[] newArray(int size) {
            return new School[size];
        }
    };

    /**
     *
     * @return
     * The schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     *
     * @param schoolName
     * The school_name
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     *
     * @return
     * The urlLive
     */
    public String getUrlLive() {
        return urlLive;
    }

    /**
     *
     * @param urlLive
     * The url_live
     */
    public void setUrlLive(String urlLive) {
        this.urlLive = urlLive;
    }

    /**
     *
     * @return
     * The ipAddressLive
     */
    public Object getIpAddressLive() {
        return ipAddressLive;
    }

    /**
     *
     * @param ipAddressLive
     * The ip_address_live
     */
    public void setIpAddressLive(Object ipAddressLive) {
        this.ipAddressLive = ipAddressLive;
    }

    /**
     *
     * @return
     * The urlLocal
     */
    public String getUrlLocal() {
        return urlLocal;
    }

    /**
     *
     * @param urlLocal
     * The url_local
     */
    public void setUrlLocal(String urlLocal) {
        this.urlLocal = urlLocal;
    }

    /**
     *
     * @return
     * The ipAddressLocal
     */
    public String getIpAddressLocal() {
        return ipAddressLocal;
    }

    /**
     *
     * @param ipAddressLocal
     * The ip_address_local
     */
    public void setIpAddressLocal(String ipAddressLocal) {
        this.ipAddressLocal = ipAddressLocal;
    }

    /**
     *
     * @return
     * The ipAddress
     */
    public Object getIpAddress() {
        return ipAddress;
    }

    /**
     *
     * @param ipAddress
     * The ip_address
     */
    public void setIpAddress(Object ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(schoolName);
        dest.writeString(urlLive);
        dest.writeString(urlLocal);
        dest.writeString(ipAddressLocal);
    }
}
