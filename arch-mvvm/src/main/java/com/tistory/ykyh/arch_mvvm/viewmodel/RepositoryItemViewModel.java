package com.tistory.ykyh.arch_mvvm.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.tistory.ykyh.arch_mvvm.MainListPresenter;
import com.tistory.ykyh.arch_mvvm.model.GitHubService;

/**
 * Created by eokhyunlee on 2018. 1. 28..
 */

public class RepositoryItemViewModel {
    public ObservableField<String> repoName = new ObservableField<>();
    public ObservableField<String> repoDetail = new ObservableField<>();
    public ObservableField<String> repoStar = new ObservableField<>();
    public ObservableField<String> repoImageUrl = new ObservableField<>();

    private MainListPresenter view;
    private String fullName;

    public RepositoryItemViewModel(MainListPresenter view) {
        this.view = view;

    }

    public void loadItem(GitHubService.RepositoryItem item) {
        fullName = item.full_name;
        repoDetail.set(item.description);
        repoName.set(item.name);
        repoStar.set("star:" + item.stargazers_count);
        repoImageUrl.set(item.owner.avatar_url);
    }

    public void onItemClick(View itemView) {
        view.startDetailActivity(fullName);
    }
}
