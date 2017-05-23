package gustavo.brilhante.listusers.utils;

import gustavo.brilhante.listusers.models.Location;
import gustavo.brilhante.listusers.models.Name;

/**
 * Created by Gustavo on 22/05/17.
 */

public class StringUtils {
    public static String getCompleteName(Name name){
        String nomeCompleto = "";
        if(name!=null) {
            if(name.title!=null)nomeCompleto += name.title;
            if(name.first!=null)nomeCompleto += " " + name.first;
            if(name.last!=null)nomeCompleto += " " + name.last;
            nomeCompleto = nomeCompleto.trim();
        }
        return nomeCompleto;
    }

    public static String getCompleteAddress(Location location){
        String address = "";
        if(location!=null){
            if(location.street!=null)address += location.street;
            if(location.city!=null)address += ", "+location.city;
            if(location.state!=null)address += " -"+location.state;
            address = address.trim();
        }
        return address;
    }

}
