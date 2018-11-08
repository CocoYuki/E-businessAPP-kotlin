package com.yirong.core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.yirong.core.app.Suger;


/**
 * Created by yiron on 2018/10/26.
 */

public class DimenUtils {

    public static int getScreenWidth(){
        Resources resources = Suger.INSTANCE.getSugerContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;

    }

    public static int getScreenHeight(){
        Resources resources = Suger.INSTANCE.getSugerContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;

    }


}
