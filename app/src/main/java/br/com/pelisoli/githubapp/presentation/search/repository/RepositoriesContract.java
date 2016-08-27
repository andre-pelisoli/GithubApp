package br.com.pelisoli.githubapp.presentation.search.repository;

import java.util.List;

import br.com.pelisoli.githubapp.domain.model.Repository;
import br.com.pelisoli.githubapp.presentation.base.MvpPresenter;
import br.com.pelisoli.githubapp.presentation.base.MvpView;

/**
 * Created by pelisoli on 8/11/16.
 */
public interface RepositoriesContract {

    interface View extends MvpView {
        void showProgress(boolean showProgress);

        void showRepositoryList(List<Repository> repositoriesList);

        void showError();
    }

    interface Presenter extends MvpPresenter<View> {
        void searchRepository(String user);
    }

}

