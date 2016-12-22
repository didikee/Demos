package com.didikee.demos.dao.download.architecture;

/**
 * Created by Aspsine on 2015/10/29.
 */
public interface Downloader {

    interface OnDownloaderDestroyedListener {
        void onDestroyed(String key, Downloader downloader);
    }

    boolean isRunning();

    void start();

    void pause();

    void cancel();

    void onDestroy();

}
