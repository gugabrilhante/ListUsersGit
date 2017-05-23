package gustavo.brilhante.listusers.utils;

import android.view.View;

import gustavo.brilhante.listusers.constants.Config;


/**
 * Created by Gustavo on 19/05/17.
 */

public class AnimUtils {

    public static void fadeInView(View view){
        view.setAlpha(0f);
        view.animate().alpha(1f).setDuration(Config.VIEW_FADE_IN_TIME).start();
    }

    public static void fadeInViewWithTime(View view, int time){
        view.setAlpha(0f);
        view.animate().alpha(1f).setDuration(time).start();
    }


}
