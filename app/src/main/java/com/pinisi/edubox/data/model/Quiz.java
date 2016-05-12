package com.pinisi.edubox.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ekky on 06/05/16.
 */
public class Quiz implements Parcelable {

    @SerializedName("isStudent")
    @Expose
    private Boolean isStudent;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("time_in_minute")
    @Expose
    private String timeInMinute;
    @SerializedName("total_question")
    @Expose
    private Object totalQuestion;
    @SerializedName("lesson")
    @Expose
    private String lesson;

    protected Quiz(Parcel in) {
        status = in.readString();
        id = in.readString();
        title = in.readString();
        timeInMinute = in.readString();
        lesson = in.readString();
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    /**
     *
     * @return
     * The isStudent
     */
    public Boolean getIsStudent() {
        return isStudent;
    }

    /**
     *
     * @param isStudent
     * The isStudent
     */
    public void setIsStudent(Boolean isStudent) {
        this.isStudent = isStudent;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The timeInMinute
     */
    public String getTimeInMinute() {
        return timeInMinute;
    }

    /**
     *
     * @param timeInMinute
     * The time_in_minute
     */
    public void setTimeInMinute(String timeInMinute) {
        this.timeInMinute = timeInMinute;
    }

    /**
     *
     * @return
     * The totalQuestion
     */
    public Object getTotalQuestion() {
        return totalQuestion;
    }

    /**
     *
     * @param totalQuestion
     * The total_question
     */
    public void setTotalQuestion(Object totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    /**
     *
     * @return
     * The lesson
     */
    public String getLesson() {
        return lesson;
    }

    /**
     *
     * @param lesson
     * The lesson
     */
    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(timeInMinute);
        dest.writeString(lesson);
    }
}
