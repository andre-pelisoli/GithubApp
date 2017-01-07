package br.com.pelisoli.githubapp.domain.api;

import br.com.pelisoli.githubapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pelisoli on 12/08/16.
 */
public class GithubApi {

    private static GithubService mIGithubService;


    private static Retrofit getApi(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        Retrofit.Builder retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return retrofit.build();
    }

    public static GithubService getRetrofit(){
        if(mIGithubService == null){
            mIGithubService = getApi().create(GithubService.class);
        }

        return mIGithubService;
    }
}
