
package gustavo.brilhante.listusers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("results")
    @Expose
    public List<Result> results = null;
    @SerializedName("info")
    @Expose
    public Info info;

}
