
package gustavo.brilhante.listusers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class Picture extends RealmObject implements Parcelable {

    @SerializedName("large")
    @Expose
    public String large;
    @SerializedName("medium")
    @Expose
    public String medium;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.large);
        dest.writeString(this.medium);
        dest.writeString(this.thumbnail);
    }

    public Picture() {
    }

    protected Picture(Parcel in) {
        this.large = in.readString();
        this.medium = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Parcelable.Creator<Picture> CREATOR = new Parcelable.Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel source) {
            return new Picture(source);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
}
