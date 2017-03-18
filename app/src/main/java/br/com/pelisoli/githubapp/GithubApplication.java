package br.com.pelisoli.githubapp;

import android.app.Application;

import br.com.pelisoli.githubapp.domain.log.Log;
import br.com.pelisoli.githubapp.domain.log.LogManager;

/**
 * Created by pelisoli on 18/03/17.
 */

public class GithubApplication extends Application{

    private Log log;

    public Log getLog(){
        if(log ==  null){
            log = new LogManager(BuildConfig.LOG_TAG);
        }

        return log;
    }

}
