package com.tistory.ykyh.arch_mvp.presenter;

import com.tistory.ykyh.arch_mvp.MvpView;
import com.tistory.ykyh.arch_mvp.Presenter;
import com.tistory.ykyh.arch_mvp.model.GitHubService;

/**
 * Created by eokhyunlee on 2018. 1. 28..
 */

public interface DetailPresenter extends Presenter<DetailPresenter.View> {

    void requestDetail(String fullName);
    void showBrower(String url);


    interface View extends MvpView {
        void setRepositoryInfo(GitHubService.RepositoryItem repositoryItem);



    }
}
