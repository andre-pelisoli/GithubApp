package br.com.pelisoli.githubapp.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import br.com.pelisoli.githubapp.RxSchedulersOverrideRule;
import br.com.pelisoli.githubapp.domain.api.GithubService;
import br.com.pelisoli.githubapp.domain.model.Repository;
import br.com.pelisoli.githubapp.presentation.search.repository.RepositoriesContract;
import br.com.pelisoli.githubapp.presentation.search.repository.RepositoriesPresenter;
import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by pelisoli on 07/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RepositoryPresenterTest {

    @Mock
    GithubService api;

    @Mock
    RepositoriesContract.View view;

    @Mock
    List<Repository> repositories;

    String userName = "User";

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    RepositoriesPresenter presenter;

    @Before
    public void setUp(){
        presenter = new RepositoriesPresenter(api);
        presenter.attachView(view);
    }

    @Test
    public void searchRepository(){
        when(api.getUserRepos(userName)).thenReturn(Observable.just(repositories));

        presenter.searchRepository(userName);

        verify(presenter.getView()).showRepositoryList(repositories);
    }

    @Test
    public void searchRepositoryError(){
        when(api.getUserRepos(userName)).thenReturn(Observable.error(new Exception()));

        presenter.searchRepository(userName);

        verify(presenter.getView()).showProgress();
        verify(presenter.getView()).hideProgress();
        verify(presenter.getView()).showError();
    }

    @Test
    public void searchRepositoryEmptyUser(){
        userName = "";
        presenter.searchRepository(userName);

        verify(presenter.getView()).showError();
    }

    @Test
    public void searchRepositoryNullUser(){
        userName = null;
        presenter.searchRepository(userName);

        verify(presenter.getView()).showError();
    }

    @Test
    public void nullApiInstance(){
        presenter = new RepositoriesPresenter(null);
        presenter.attachView(view);

        presenter.searchRepository(userName);

        verify(presenter.getView()).showError();
    }

    @After
    public void tearDown(){
        if(presenter != null){
            presenter.detachView();
        }
    }

}
