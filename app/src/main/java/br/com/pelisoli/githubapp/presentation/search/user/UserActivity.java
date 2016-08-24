package br.com.pelisoli.githubapp.presentation.search.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import br.com.pelisoli.githubapp.R;
import br.com.pelisoli.githubapp.domain.api.GithubApi;
import br.com.pelisoli.githubapp.domain.model.User;
import br.com.pelisoli.githubapp.presentation.search.repository.RepositoriesActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends AppCompatActivity implements UserContract.View {
    @BindView(R.id.searchBtn)
    Button btnSearch;

    @BindView(R.id.searchEdt)
    EditText searchEdt;

    @BindView(R.id.progress_view)
    CircularProgressView progress;

    UserContract.Presenter mPresenter;

    @BindView(R.id.card_view)
    CardView mCardView;

    @BindView(R.id.user_image)
    AppCompatImageView mUserImage;

    @BindView(R.id.user_name)
    TextView mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        ButterKnife.bind(this);

        mPresenter = new UserPresenter(GithubApi.getRetrofit());
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
    public void showCard(boolean showCard) {
        if (showCard) {
            mCardView.setVisibility(View.VISIBLE);
        }else {
            mCardView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showUser(User user) {
        showProgress(false);
        showCard(true);

        mUserName.setText(user.getName());

        Glide
           .with(this)
           .load(user.getAvatar_url())
           .into(mUserImage);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void launchRepoScreen(String userName) {
        Intent intent = new Intent(getApplicationContext(), RepositoriesActivity.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @OnClick(R.id.searchBtn)
    public void searchClick(){
        mPresenter.searchUser(searchEdt.getText().toString());
    }

    @OnClick(R.id.card_view)
    void cardClick(){
        mPresenter.openRepositories(searchEdt.getText().toString());
    }
}
