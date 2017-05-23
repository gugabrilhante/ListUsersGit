
package gustavo.brilhante.listusers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Login extends RealmObject implements Parcelable {

    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("salt")
    @Expose
    public String salt;
    @SerializedName("md5")
    @Expose
    public String md5;
    @SerializedName("sha1")
    @Expose
    public String sha1;
    @SerializedName("sha256")
    @Expose
    public String sha256;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.salt);
        dest.writeString(this.md5);
        dest.writeString(this.sha1);
        dest.writeString(this.sha256);
    }

    public Login() {
    }

    protected Login(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.salt = in.readString();
        this.md5 = in.readString();
        this.sha1 = in.readString();
        this.sha256 = in.readString();
    }

    public static final Parcelable.Creator<Login> CREATOR = new Parcelable.Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel source) {
            return new Login(source);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };
}
