package com.wug.framew.net.log;

import okhttp3.internal.platform.Platform;

/**
 * @author ihsan on 11/07/2017.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface Logger {
    void log(int level, String tag, String msg);

    Logger DEFAULT = new Logger() {
        @Override
        public void log(int level, String tag, String msg) {
            Platform.get().log(msg,level, null);
        }
    };
}
