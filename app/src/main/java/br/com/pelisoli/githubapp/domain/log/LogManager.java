package br.com.pelisoli.githubapp.domain.log;

/**
 * Created by pelisoli on 18/03/17.
 */

public class LogManager implements Log {

    private String tag;

    public LogManager(String tag) {
        this.tag = tag;
    }

    @Override
    public void logError(String message) {
        android.util.Log.e(tag, message);
    }

    @Override
    public void logVerbose(String message) {
        android.util.Log.v(tag, message);
    }

    @Override
    public void logDebug(String message) {
        android.util.Log.d(tag, message);
    }

    @Override
    public void logInfo(String message) {
        android.util.Log.i(tag, message);
    }
}
