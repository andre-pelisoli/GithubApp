package br.com.pelisoli.githubapp.presentation.search.repository;

import br.com.pelisoli.githubapp.domain.api.GithubService;
import br.com.pelisoli.githubapp.domain.log.Log;
import br.com.pelisoli.githubapp.presentation.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pelisoli on 8/11/16.
 */
public class RepositoriesPresenter extends BasePresenter<RepositoriesContract.View>
        implements RepositoriesContract.Presenter {

    private GithubService githubService;

    private Log log;

    public RepositoriesPresenter(GithubService githubService, Log log) {
        this.githubService = githubService;
        this.log = log;
    }

    @Override
    public void searchRepository(String user) {
        if(githubService != null) {
            if (user != null && !user.isEmpty()) {
                getView().showProgress();

                addSubscription(githubService
                        .getUserRepos(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                repositories -> {
                                    getView().showRepositoryList(repositories);
                                },

                                throwable -> {
                                    log.logError("searchRepository : " + throwable.getMessage());
                                    getView().hideProgress();
                                    getView().showError();
                                }));
            } else {
                log.logError("searchRepository : " + "User is empty or null");
                getView().showError();
            }
        }else{
            log.logError("searchRepository : " + "API object is null");
            getView().showError();
        }
    }
}
