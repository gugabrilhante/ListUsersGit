
package gustavo.brilhante.listusers.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class Id extends RealmObject implements Parcelable {

    @SerializedName("name")
    @Expose
    public String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public Id() {
    }

    protected Id(android.os.Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Id> CREATOR = new Parcelable.Creator<Id>() {
        @Override
        public Id createFromParcel(android.os.Parcel source) {
            return new Id(source);
        }

        @Override
        public Id[] newArray(int size) {
            return new Id[size];
        }
    };
}
