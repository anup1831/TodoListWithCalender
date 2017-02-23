package com.pathfinder.anup.util;

import android.os.Build;

/**
 * Created by Anup on 2/23/2017.
 */

public class Tools {


    public static boolean hasJellyBean(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

}
