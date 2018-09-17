package io.github.genelist.util;

import android.content.res.Resources;

import java.util.logging.Logger;

public abstract class Util {

    public static void warn(Logger log, int str) {
        String msg = Resources.getSystem().getString(str);
        log.warning(msg);
    }

}
