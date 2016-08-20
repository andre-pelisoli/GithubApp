package br.com.pelisoli.githubapp.presentation.search.repository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.List;

import br.com.pelisoli.githubapp.R;
import br.com.pelisoli.githubapp.domain.api.GithubApi;
import br.com.pelisoli.githubapp.domain.model.Repository;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View {
    @BindView(R.id.searchBtn)
    Button btnSearch;

    @BindView(R.id.searchEdt)
    EditText searchEdt;

    @BindView(R.id.progress_view)
    CircularProgressView progress;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    RepositoriesContract.Presenter mPresenter;

    RepositoriesAdapter mRepositoriesAdapter;

    List<Repository> mRepositories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRepositoriesAdapter = new RepositoriesAdapter(mRepositories);
        recyclerView.setAdapter(mRepositoriesAdapter);

        mPresenter = new RepositoriesPresenter(GithubApi.getRetrofit());
        mPresenter.attachView(this);

    }

    @Override
    public void showProgress(boolean showProgress) {
        if (showProgress) {
            progress.setVisibility(View.VISIBLE);
        }else {
            progress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showRepositoryList(List<Repository> repositoriesList) {
        mRepositoriesAdapter.addNewList(repositoriesList);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @OnClick(R.id.searchBtn)
    public void searchClick(){
        mPresenter.searchRepository(searchEdt.getText().toString());
    }
}
