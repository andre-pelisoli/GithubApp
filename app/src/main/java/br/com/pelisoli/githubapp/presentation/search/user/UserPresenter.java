package br.com.pelisoli.githubapp.presentation.search.user;

import android.util.Log;

import br.com.pelisoli.githubapp.BuildConfig;
import br.com.pelisoli.githubapp.domain.api.IGithubService;
import br.com.pelisoli.githubapp.presentation.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pelisoli on 8/21/16.
 */
public class UserPresenter extends BasePresenter<UserContract.View>
        implements UserContract.Presenter  {

    IGithubService mGithubService;

    public UserPresenter(IGithubService githubService) {
        mGithubService = githubService;
    }

    @Override
    public void searchUser(String userName) {
        getView().showProgress(true);

        if (userName != null && !userName.isEmpty()) {
            addSubscription(mGithubService
                    .getUser(userName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            user -> getView().showUser(user),

                            throwable -> {
                                Log.e(BuildConfig.LOG_TAG, "searchUser: " + throwable.getMessage());
                                getView().showError();
                            },

                            () -> Log.i(BuildConfig.LOG_TAG, "searchUser: " + "Completed" )
                    ));
        }else{
            getView().showEmptyFieldDialog();
        }

        getView().showProgress(false);
    }

    @Override
    public void openRepositories(String userName) {
        getView().launchRepoScreen(userName);
    }
}
