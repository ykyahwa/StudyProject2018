package com.tistory.ykyh.arch_mvvm.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.tistory.ykyh.arch_mvvm.DetailPresenter;
import com.tistory.ykyh.arch_mvvm.model.GitHubService;
import com.tistory.ykyh.arch_mvvm.model.RetrofitCreator;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eokhyunlee on 2018. 1. 28..
 */

public class DetailViewModel {
    public ObservableField<String> detailName = new ObservableField<>();
    public ObservableField<String> detailDetail = new ObservableField<>();
    public ObservableField<String> detailStar = new ObservableField<>();
    public ObservableField<String> detailFork = new ObservableField<>();
    public ObservableField<String> detailImageUrl = new ObservableField<>();

    private DetailPresenter view;
    private GitHubService.RepositoryItem repositoryItem;
    public DetailViewModel(DetailPresenter view) {
        this.view = view;
    }

    public void loadRepository(GitHubService.RepositoryItem repositoryItem) {
        this.repositoryItem = repositoryItem;

        detailName.set(repositoryItem.full_name);
        detailDetail.set(repositoryItem.description);
        detailStar.set("Star:" + repositoryItem.stargazers_count);
        detailFork.set("Fork:" + repositoryItem.forks_count);
        detailImageUrl.set(repositoryItem.owner.avatar_url);
    }

    public void requestDetail(String fullName) {
        String[] repoData = fullName.split("/");
        String owner = repoData[0];
        String repoName = repoData[1];

        RetrofitCreator.getGitHubService().detailRepo(owner, repoName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GitHubService.RepositoryItem>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GitHubService.RepositoryItem repositoryItem) {
                        loadRepository(repositoryItem);
                    }
                });

    }

    public void showBrower(View v) {
        view.startWeb(repositoryItem.html_url);
    }

}
