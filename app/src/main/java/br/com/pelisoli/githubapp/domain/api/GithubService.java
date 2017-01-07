package br.com.pelisoli.githubapp.domain.api;

import java.util.List;

import br.com.pelisoli.githubapp.domain.model.User;
import br.com.pelisoli.githubapp.domain.model.Repository;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by pelisoli on 12/08/16.
 */
public interface GithubService {

    @GET("/users/{username}/repos")
    Observable<List<Repository>> getUserRepos(@Path("username") String userName);

    @GET("/users/{username}")
    Observable<User> getUser(@Path("username") String userName);

}
