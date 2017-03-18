package br.com.pelisoli.githubapp.presentation.user;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.pelisoli.githubapp.presentation.util.RxSchedulersOverrideRule;
import br.com.pelisoli.githubapp.domain.api.GithubService;
import br.com.pelisoli.githubapp.domain.log.Log;
import br.com.pelisoli.githubapp.domain.model.User;
import br.com.pelisoli.githubapp.presentation.search.user.UserContract;
import br.com.pelisoli.githubapp.presentation.search.user.UserPresenter;
import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by pelisoli on 18/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserPresenterTest {

    @Mock
    private GithubService api;

    @Mock
    private UserContract.View view;

    @Mock
    private User user;

    @Mock
    private Log log;

    private String userName = "user";

    private UserPresenter presenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp(){
        presenter = new UserPresenter(api, log);
        presenter.attachView(view);
    }

    @After
    public void tearDown(){
        if(presenter != null){
            presenter.detachView();
        }
    }

    @Test
    public void searchUserSuccess() {
        when(api.getUser(userName)).thenReturn(Observable.just(user));

        presenter.searchUser(userName);

        verify(view).showProgress();
        verify(view).hideProgress();
        verify(view).showUser(user);
    }

    @Test
    public void searchUserError() {
        when(api.getUser(userName)).thenReturn(Observable.error(new Exception()));

        presenter.searchUser(userName);

        verify(view).showProgress();
        verify(view).hideProgress();
        verify(view).showError();
    }

    @Test
    public void searchUserNullUser(){
        userName = null;
        presenter.searchUser(null);

        verify(view).showEmptyFieldDialog();
    }

    @Test
    public void nullApiInstance(){
        presenter = new UserPresenter(null, log);
        presenter.attachView(view);

        presenter.searchUser(userName);

        verify(view).showError();
    }

    @Test
    public void openRepositories() {
        presenter.openRepositories(userName);
        verify(view).launchRepoScreen(userName);
    }

}