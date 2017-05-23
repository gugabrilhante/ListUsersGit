package gustavo.brilhante.listusers.utils;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by Gustavo on 22/05/17.
 */

public class ScreenUtils {
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
