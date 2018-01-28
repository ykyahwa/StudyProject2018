package com.tistory.ykyh.arch_mvvm.viewmodel;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.tistory.ykyh.arch_mvvm.MainListPresenter;
import com.tistory.ykyh.arch_mvvm.model.GitHubService;
import com.tistory.ykyh.arch_mvvm.model.RetrofitCreator;

import java.util.Objects;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by eokhyunlee on 2018. 1. 28..
 */

public class MainListViewModel {

    ObservableField<String> keyword = new ObservableField<>();

    private MainListPresenter view;

    public MainListViewModel(MainListPresenter view) {
        this.view = view;

    }

    public TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!Objects.equals(keyword.get(), s.toString())) {
                keyword.set(s.toString());
            }
        }
    };

    public void onClickSearch(View view) {
        requestSearch(keyword.get());
//        requestSearch((String) parent.getItemAtPosition(position));
    }

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
