package com.tistory.ykyh.arch_mvp.presenter;

import android.util.Log;

import com.tistory.ykyh.arch_mvp.model.GitHubService;
import com.tistory.ykyh.arch_mvp.model.RetrofitCreator;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eokhyunlee on 2018. 1. 28..
 */

public class MainListPresenterImpl implements MainListPresenter {

    private MainListPresenter.View view;


    @Override
    public void attachView(View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void selectItem(GitHubService.RepositoryItem item) {
        view.startDetailActivity(item.full_name);
    }

    @Override
    public void requestSearch(String keyword) {
        Observable<GitHubService.Repositories> observable = RetrofitCreator.getGitHubService().listRepos(keyword);


        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GitHubService.Repositories>() {
            @Override
            public void onNext(GitHubService.Repositories repositories) {
                // 로딩이 끝났으므로 진행바를 표시하지 않는다
                // 가져온 아이템을 표시하고자 RecyclerView에 아이템을 설정하고 갱신한다
                Log.d("TEST" , "onNext");
                view.showRepositorys(repositories.items);
            }

            @Override
            public void onError(Throwable e) {
                // 통신 실패 시에 호출된다
                // 여기서는 스낵바를 표시한다(아래에 표시되는 바)
                Log.d("TEST" , "onError");
                e.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // 아무것도 하지 않는다
                Log.d("TEST" , "onCompleted");
            }
        });
    }
}
