package t3.henu.left_library.Activities.MusicInfo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 高逸博 on 2017/5/21.
 */

public class Artist implements Parcelable {
    private String id;
    private String name;
    private String picUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.picUrl);
    }

    public Artist() {
    }

    protected Artist(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.picUrl = in.readString();
    }

    public static final Parcelable.Creator<Artist> CREATOR = new Parcelable.Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel source) {
            return new Artist(source);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
}
