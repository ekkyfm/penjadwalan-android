package com.pinisi.edubox.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekky on 07/05/16.
 */
public class    Data<T> implements Parcelable {

    @SerializedName("totalPage")
    @Expose
    private Integer totalPage;
    @SerializedName("perPage")
    @Expose
    private Integer perPage;
    @SerializedName("result")
    @Expose
    private T result;


    public Data() {
    }

    public Data(Integer totalPage, Integer perPage, T result) {
        this.totalPage = totalPage;
        this.perPage = perPage;
        this.result = result;
    }

    protected Data(Parcel in) {
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}

