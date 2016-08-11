package br.com.pelisoli.githubapp.presentation.search.repository;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import br.com.pelisoli.githubapp.R;
import br.com.pelisoli.githubapp.model.Repository;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View {
    @BindView(R.id.searchBtn)
    Button btnSearch;

    @BindView(R.id.searchEdt)
    EditText searchEdt;

    @BindView(R.id.progress_view)
    CircularProgressView progress;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    public void setProgressVisibility(boolean showProgress) {
        if (showProgress) {
            progress.setVisibility(View.VISIBLE);
        }else {
            progress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showRepositoryList(List<Repository> repositoriesList) {

    }
}
