
package gustavo.brilhante.listusers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class Name extends RealmObject implements Parcelable {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("first")
    @Expose
    public String first;
    @SerializedName("last")
    @Expose
    public String last;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.first);
        dest.writeString(this.last);
    }

    public Name() {
    }

    protected Name(Parcel in) {
        this.title = in.readString();
        this.first = in.readString();
        this.last = in.readString();
    }

    public static final Parcelable.Creator<Name> CREATOR = new Parcelable.Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel source) {
            return new Name(source);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };
}
