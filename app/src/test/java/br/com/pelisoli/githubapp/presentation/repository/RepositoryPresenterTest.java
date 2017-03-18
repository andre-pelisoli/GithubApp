package br.com.pelisoli.githubapp.presentation.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import br.com.pelisoli.githubapp.presentation.util.RxSchedulersOverrideRule;
import br.com.pelisoli.githubapp.domain.api.GithubService;
import br.com.pelisoli.githubapp.domain.log.Log;
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
    private GithubService api;

    @Mock
    private RepositoriesContract.View view;

    @Mock
    private List<Repository> repositories;

    @Mock
    private Log log;

    private String userName = "User";

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    RepositoriesPresenter presenter;

    @Before
    public void setUp(){
        presenter = new RepositoriesPresenter(api, log);
        presenter.attachView(view);
    }

    @Test
    public void searchRepository(){
        when(api.getUserRepos(userName)).thenReturn(Observable.just(repositories));

        presenter.searchRepository(userName);

        verify(view).showProgress();
        verify(view).hideProgress();
        verify(view).showRepositoryList(repositories);
    }

    @Test
    public void searchRepositoryError(){
        when(api.getUserRepos(userName)).thenReturn(Observable.error(new Exception()));

        presenter.searchRepository(userName);

        verify(view).showProgress();
        verify(view).hideProgress();
        verify(view).showError();
    }

    @Test
    public void searchRepositoryEmptyUser(){
        userName = "";
        presenter.searchRepository(userName);

        verify(view).showError();
    }

    @Test
    public void searchRepositoryNullUser(){
        userName = null;
        presenter.searchRepository(userName);

        verify(view).showError();
    }

    @Test
    public void nullApiInstance(){
        presenter = new RepositoriesPresenter(null, log);
        presenter.attachView(view);

        presenter.searchRepository(userName);

        verify(view).showError();
    }

    @After
    public void tearDown(){
        if(presenter != null){
            presenter.detachView();
        }
    }

}
