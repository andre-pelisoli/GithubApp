package br.com.pelisoli.githubapp.presentation.search.repository;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import br.com.pelisoli.githubapp.R;
import br.com.pelisoli.githubapp.domain.api.GithubApi;
import br.com.pelisoli.githubapp.domain.model.Repository;
import br.com.pelisoli.githubapp.presentation.search.dialog.InfoDialog;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pelisoli on 8/21/16.
 */
public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View{
    @BindView(R.id.recycler_repo)
    RecyclerView mRecyclerView;

    @BindView(R.id.rep_progress_view)
    CircularProgressView mProgress;

    RepositoriesAdapter mRepositoriesAdapter;

    List<Repository> mRepositories = null;

    String userName = "";

    RepositoriesContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repositories_activity);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            userName = bundle.getString("userName");
        }

        mRepositoriesAdapter = new RepositoriesAdapter(mRepositories);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRepositoriesAdapter);

        mPresenter = new RepositoriesPresenter(GithubApi.getRetrofit());
        mPresenter.attachView(this);
        mPresenter.searchRepository(userName);

    }

    @Override
    public void showProgress() {
            mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showRepositoryList(List<Repository> repositoriesList) {
        mRepositoriesAdapter.addNewList(repositoriesList);
    }

    @Override
    public void showError() {
        DialogFragment dialog = InfoDialog.newInstance(getString(R.string.error));
        dialog.show(this.getSupportFragmentManager(), "InfoDialog");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
