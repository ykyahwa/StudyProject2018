package com.tistory.ykyh.arch_mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.tistory.ykyh.arch_mvp.R;
import com.tistory.ykyh.arch_mvp.model.GitHubService;
import com.tistory.ykyh.arch_mvp.presenter.MainListPresenter;
import com.tistory.ykyh.arch_mvp.presenter.MainListPresenterImpl;

import java.util.List;

public class MainListActivity extends AppCompatActivity implements MainListPresenter.View, MainListAdapter.OnRepositoryItemClickListener {

    private EditText etKeyord;
    private Button btSearch;
    private RecyclerView rvList;

    private MainListAdapter adapter;
    private MainListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainListPresenterImpl();
        presenter.attachView(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
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
        presenter.requestSearch(keyword);
    }


    @Override
    public void showRepositorys(List<GitHubService.RepositoryItem> items) {
        adapter.setItemsAndRefresh(items);
    }

    @Override
    public void startDetailActivity(String fullName) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_FULL_REPOSITORY_NAME, fullName);
        startActivity(intent);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onRepositoryItemClick(GitHubService.RepositoryItem item) {
        presenter.selectItem(item);
    }
}
