package br.com.pelisoli.githubapp.domain.log;

/**
 * Created by pelisoli on 18/03/17.
 */

public interface Log {

    void logError(String message);

    void logVerbose(String message);

    void logDebug(String message);

    void logInfo(String message);
}
