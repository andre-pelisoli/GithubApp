package br.com.pelisoli.githubapp.presentation.search.user;

import br.com.pelisoli.githubapp.domain.api.GithubService;
import br.com.pelisoli.githubapp.domain.log.Log;
import br.com.pelisoli.githubapp.presentation.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pelisoli on 8/21/16.
 */
public class UserPresenter extends BasePresenter<UserContract.View>
        implements UserContract.Presenter  {

    private GithubService githubService;

    private Log log;

    public UserPresenter(GithubService githubService, Log log) {
        this.githubService = githubService;
        this.log = log;
    }

    @Override
    public void searchUser(String userName) {
        getView().showProgress();
        if(githubService != null) {
            if (userName != null && !userName.isEmpty()) {
                addSubscription(githubService
                        .getUser(userName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                user -> {
                                    getView().hideProgress();
                                    getView().showUser(user);
                                },

                                throwable -> {
                                    log.logError("searchUser: " + throwable.getMessage());
                                    getView().hideProgress();
                                    getView().showError();
                                }));
            } else {
                log.logError("searchUser: " + "User is empty or null");
                getView().showEmptyFieldDialog();
            }
        }else{
            log.logError("searchUser: " + "API object is null");
            getView().showError();
        }
    }

    @Override
    public void openRepositories(String userName) {
        getView().launchRepoScreen(userName);
    }
}
