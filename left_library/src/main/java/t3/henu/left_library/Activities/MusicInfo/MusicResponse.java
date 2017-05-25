package t3.henu.left_library.Activities.MusicInfo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 高逸博 on 2017/5/21.
 */

public class MusicResponse implements Parcelable {
    private Result result;
    private int code;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.result, flags);
        dest.writeInt(this.code);
    }

    public MusicResponse() {
    }

    protected MusicResponse(Parcel in) {
        this.result = in.readParcelable(Result.class.getClassLoader());
        this.code = in.readInt();
    }

    public static final Creator<MusicResponse> CREATOR = new Creator<MusicResponse>() {
        @Override
        public MusicResponse createFromParcel(Parcel source) {
            return new MusicResponse(source);
        }

        @Override
        public MusicResponse[] newArray(int size) {
            return new MusicResponse[size];
        }
    };
}
