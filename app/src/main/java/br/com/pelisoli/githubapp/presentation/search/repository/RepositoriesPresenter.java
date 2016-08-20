package br.com.pelisoli.githubapp.presentation.search.repository;

import android.util.Log;

import br.com.pelisoli.githubapp.BuildConfig;
import br.com.pelisoli.githubapp.domain.api.IGithubService;
import br.com.pelisoli.githubapp.presentation.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pelisoli on 8/11/16.
 */
public class RepositoriesPresenter extends BasePresenter<RepositoriesContract.View>
        implements RepositoriesContract.Presenter {

    IGithubService mGithubService;

    public RepositoriesPresenter(IGithubService githubService) {
        mGithubService = githubService;
    }

    @Override
    public void searchRepository(String user) {
        getView().showProgress(true);

        if (user != null && !user.isEmpty()) {
            addSubscription(mGithubService
                    .getUserRepos(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            repositories -> {
                                getView().showRepositoryList(repositories);
                            },
                            throwable -> Log.e(BuildConfig.LOG_TAG, "searchRepository: " + throwable.getMessage()),
                            () -> Log.i(BuildConfig.LOG_TAG, "searchRepository: " + "onCompleted")
                    )
            );
        }else{
            getView().showError("User cannot be empty");
        }

        getView().showProgress(false);
    }
}
