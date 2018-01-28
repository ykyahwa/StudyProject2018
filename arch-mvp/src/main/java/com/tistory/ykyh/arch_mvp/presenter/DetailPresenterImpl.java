package com.tistory.ykyh.arch_mvp.presenter;

import android.content.Intent;
import android.net.Uri;

import com.tistory.ykyh.arch_mvp.model.GitHubService;
import com.tistory.ykyh.arch_mvp.model.RetrofitCreator;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eokhyunlee on 2018. 1. 28..
 */

public class DetailPresenterImpl implements DetailPresenter {
    private DetailPresenter.View view;

    @Override
    public void attachView(View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

    @Override
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
                        view.setRepositoryInfo(repositoryItem);
                    }
                });

    }

    @Override
    public void showBrower(String url) {
        view.getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
