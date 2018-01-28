package com.tistory.ykyh.arch_mvp.presenter;

import com.tistory.ykyh.arch_mvp.MvpView;
import com.tistory.ykyh.arch_mvp.Presenter;
import com.tistory.ykyh.arch_mvp.model.GitHubService;

import java.util.List;

/**
 * Created by eokhyunlee on 2018. 1. 28..
 */

public interface MainListPresenter extends Presenter<MainListPresenter.View> {

    void selectItem(GitHubService.RepositoryItem item);
    void requestSearch(String keyword);

    interface View extends MvpView {
        void showRepositorys(List<GitHubService.RepositoryItem> items);
        void startDetailActivity(String fullName);

    }
}
