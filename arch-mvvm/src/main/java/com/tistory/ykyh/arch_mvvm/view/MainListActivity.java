package com.tistory.ykyh.arch_mvvm.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.tistory.ykyh.arch_mvvm.MainListPresenter;
import com.tistory.ykyh.arch_mvvm.R;
import com.tistory.ykyh.arch_mvvm.databinding.ActivityMainListBinding;
import com.tistory.ykyh.arch_mvvm.model.GitHubService;
import com.tistory.ykyh.arch_mvvm.viewmodel.MainListViewModel;

import java.util.List;


public class MainListActivity extends AppCompatActivity implements MainListPresenter, MainListAdapter.OnRepositoryItemClickListener{

    private EditText etKeyord;
    private RecyclerView rvList;

    private MainListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main_list);
        binding.setViewModel(new MainListViewModel(this));

        init();

    }

    private void init() {
        etKeyord = findViewById(R.id.et_keyword);
        etKeyord.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                String keyword = etKeyord.getText().toString();
//                loadRepositories(keyword);
            }
            return true;
        });
        rvList = findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainListAdapter(this, this);
        rvList.setAdapter(adapter);
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
    public void onRepositoryItemClick(GitHubService.RepositoryItem item) {

    }
}
