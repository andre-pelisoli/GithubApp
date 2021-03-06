package br.com.pelisoli.githubapp.presentation.search.user;

import br.com.pelisoli.githubapp.domain.model.User;
import br.com.pelisoli.githubapp.presentation.base.MvpPresenter;
import br.com.pelisoli.githubapp.presentation.base.MvpView;

/**
 * Created by pelisoli on 8/21/16.
 */
public interface UserContract {

    interface View extends MvpView {
        void showProgress();

        void hideProgress();

        void showCard(boolean showCard);

        void showUser(User user);

        void showEmptyFieldDialog();

        void showError();

        void launchRepoScreen(String userName);
    }

    interface Presenter extends MvpPresenter<View> {
        void searchUser(String user);

        void openRepositories(String userName);
    }

}
