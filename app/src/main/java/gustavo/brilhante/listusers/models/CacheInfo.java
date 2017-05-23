package gustavo.brilhante.listusers.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Gustavo on 19/05/17.
 */

public class CacheInfo extends RealmObject{

    @PrimaryKey
    int primaryKey = 1;

    String downloadedTime;


    public String getDownloadedTime() {
        return downloadedTime;
    }

    public void setDownloadedTime(String downloadedTime) {
        this.downloadedTime = downloadedTime;
    }

}
