package com.tistory.ykyh.studyproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainListActivity extends AppCompatActivity implements MainListAdapter.OnRepositoryItemClickListener {

    private EditText etKeyord;
    private Button btSearch;
    private RecyclerView rvList;

    private MainListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        etKeyord = findViewById(R.id.et_keyword);
        etKeyord.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                String keyword = etKeyord.getText().toString();
                loadRepositories(keyword);
            }
            return true;
        });
        btSearch = findViewById(R.id.bt_search);
        btSearch.setOnClickListener(view -> {
            String keyword = etKeyord.getText().toString();
            loadRepositories(keyword);

        });
        rvList = findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainListAdapter(this, this);
        rvList.setAdapter(adapter);
    }

    private void loadRepositories(String keyword) {
        Observable<GitHubService.Repositories> observable = RetrofitCreator.getGitHubService().listRepos(keyword);


        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GitHubService.Repositories>() {
            @Override
            public void onNext(GitHubService.Repositories repositories) {
                // 로딩이 끝났으므로 진행바를 표시하지 않는다
                // 가져온 아이템을 표시하고자 RecyclerView에 아이템을 설정하고 갱신한다
                Log.d("TEST" , "onNext");
                adapter.setItemsAndRefresh(repositories.items);
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

    @Override
    public void onRepositoryItemClick(GitHubService.RepositoryItem item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_FULL_REPOSITORY_NAME, item.full_name);
        startActivity(intent);
    }


}
