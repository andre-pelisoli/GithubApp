package br.com.pelisoli.githubapp.presentation.search.repository;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.pelisoli.githubapp.R;
import br.com.pelisoli.githubapp.domain.model.Repository;

/**
 * Created by pelisoli on 8/20/16.
 */
public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder> {
    List<Repository> mRepositories;

    public RepositoriesAdapter(List<Repository> repositories) {
        mRepositories = repositories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repository repository = mRepositories.get(position);

        holder.mRepoName.setText(repository.getName());
    }

    @Override
    public int getItemCount() {
        return mRepositories != null ? mRepositories.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mRepoName;

        public ViewHolder(View v) {
            super(v);

            mRepoName = (TextView) v.findViewById(R.id.repo_name);
        }
    }

    public void addNewList(List<Repository> repositoryList){
        mRepositories = repositoryList;
        notifyDataSetChanged();
    }

}
