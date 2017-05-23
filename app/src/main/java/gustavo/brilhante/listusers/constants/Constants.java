package gustavo.brilhante.listusers.constants;

/**
 * Created by Gustavo on 22/05/17.
 */

public class Constants {

    public static String USER_URL = "https://randomuser.me/api/?results=RESULT_NUMBER";

    public static String getUserUrl(int number){
        return USER_URL.replace("RESULT_NUMBER", ""+number);
    }

}
