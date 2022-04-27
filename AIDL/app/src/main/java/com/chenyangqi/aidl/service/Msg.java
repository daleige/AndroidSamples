package com.chenyangqi.aidl.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author : ChenYangQi
 * date   : 2022/4/27 23:53
 * desc   :
 */
public class Msg implements Parcelable {

    private String msg;
    private long time;

    public Msg(String msg, long time) {
        this.msg = msg;
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.msg);
        dest.writeLong(this.time);
    }

    public void readFromParcel(Parcel source) {
        this.msg = source.readString();
        this.time = source.readLong();
    }

    protected Msg(Parcel in) {
        this.msg = in.readString();
        this.time = in.readLong();
    }

    public static final Parcelable.Creator<Msg> CREATOR = new Parcelable.Creator<Msg>() {
        @Override
        public Msg createFromParcel(Parcel source) {
            return new Msg(source);
        }

        @Override
        public Msg[] newArray(int size) {
            return new Msg[size];
        }
    };

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                ", time=" + time +
                '}';
    }
}
