package br.com.pelisoli.githubapp.presentation.search.repository;

import br.com.pelisoli.githubapp.domain.api.GithubService;
import br.com.pelisoli.githubapp.presentation.base.BasePresenter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pelisoli on 8/11/16.
 */
public class RepositoriesPresenter extends BasePresenter<RepositoriesContract.View>
        implements RepositoriesContract.Presenter {

    GithubService mGithubService;

    public RepositoriesPresenter(GithubService githubService) {
        mGithubService = githubService;
    }

    @Override
    public void searchRepository(String user) {
        if(mGithubService != null) {
            if (user != null && !user.isEmpty()) {
                getView().showProgress();

                addSubscription(mGithubService
                        .getUserRepos(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                repositories -> {
                                    getView().hideProgress();
                                    getView().showRepositoryList(repositories);
                                },

                                throwable -> {
//                                Log.e(BuildConfig.LOG_TAG, "searchUser error: " + throwable.getMessage());
                                    getView().hideProgress();
                                    getView().showError();
                                }));
            } else {
                getView().showError();
            }
        }else{
            getView().showError();
        }


    }
}
