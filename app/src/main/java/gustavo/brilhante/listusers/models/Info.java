
package gustavo.brilhante.listusers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Info extends RealmObject{

    @PrimaryKey
    @Expose(serialize = false, deserialize = true)
    public int primaryKey = 2;

    @SerializedName("seed")
    @Expose
    public String seed;
    @SerializedName("results")
    @Expose
    public Integer results;
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("version")
    @Expose
    public String version;

}
