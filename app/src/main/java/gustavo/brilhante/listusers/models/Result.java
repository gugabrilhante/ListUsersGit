
package gustavo.brilhante.listusers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

public class Result extends RealmObject implements Parcelable {

    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("name")
    @Expose
    public Name name;
    @SerializedName("location")
    @Expose
    public Location location;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("login")
    @Expose
    public Login login;
    @SerializedName("dob")
    @Expose
    public String dob;
    @SerializedName("registered")
    @Expose
    public String registered;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("cell")
    @Expose
    public String cell;
    @SerializedName("id")
    @Expose
    public Id id;
    @SerializedName("picture")
    @Expose
    public Picture picture;
    @SerializedName("nat")
    @Expose
    public String nat;

    @Ignore
    @Expose(serialize = false, deserialize = false)
    public boolean isSelected = false;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gender);
        dest.writeParcelable(this.name, flags);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.email);
        dest.writeParcelable(this.login, flags);
        dest.writeString(this.dob);
        dest.writeString(this.registered);
        dest.writeString(this.phone);
        dest.writeString(this.cell);
        dest.writeParcelable(this.id, flags);
        dest.writeParcelable(this.picture, flags);
        dest.writeString(this.nat);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    public Result() {
    }

    protected Result(Parcel in) {
        this.gender = in.readString();
        this.name = in.readParcelable(Name.class.getClassLoader());
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.email = in.readString();
        this.login = in.readParcelable(Login.class.getClassLoader());
        this.dob = in.readString();
        this.registered = in.readString();
        this.phone = in.readString();
        this.cell = in.readString();
        this.id = in.readParcelable(Id.class.getClassLoader());
        this.picture = in.readParcelable(Picture.class.getClassLoader());
        this.nat = in.readString();
        this.isSelected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
