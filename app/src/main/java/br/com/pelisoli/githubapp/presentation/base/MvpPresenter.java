package br.com.pelisoli.githubapp.presentation.base;

/**
 * Created by pelisoli on 8/11/16.
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}