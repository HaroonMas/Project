package com.haroonmasjidi.project.Data;

import android.os.Parcel;
import android.os.Parcelable;

import static java.lang.System.out;

public class DatabaseGson implements Parcelable {

    private  int mData;

    protected DatabaseGson(Parcel in) {
        mData=in.readInt();
    }



    public static final Creator<DatabaseGson> CREATOR = new Creator<DatabaseGson>() {
        @Override
        public DatabaseGson createFromParcel(Parcel in) {
            return new DatabaseGson(in);
        }

        @Override
        public DatabaseGson[] newArray(int size) {
            return new DatabaseGson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        out.write(mData);
    }
}
