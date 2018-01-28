package com.tistory.ykyh.arch_mvvm;


import com.tistory.ykyh.arch_mvvm.model.GitHubService;

import java.util.List;

/**
 * Created by eokhyunlee on 2018. 1. 28..
 */

public interface MainListPresenter {
    void showRepositorys(List<GitHubService.RepositoryItem> items);
    void startDetailActivity(String fullName);
}
