package com.tistory.ykyh.arch_mvvm.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tistory.ykyh.arch_mvvm.DetailPresenter;
import com.tistory.ykyh.arch_mvvm.R;
import com.tistory.ykyh.arch_mvvm.databinding.ActivityDetailBinding;
import com.tistory.ykyh.arch_mvvm.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity implements DetailPresenter{
    public static final String EXTRA_FULL_REPOSITORY_NAME = "EXTRA_FULL_REPOSITORY_NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        DetailViewModel viewModel = new DetailViewModel(this);

        binding.setViewModel(viewModel);

        Intent intent = getIntent();
        String fullRepoName = intent.getStringExtra(EXTRA_FULL_REPOSITORY_NAME);
        viewModel.requestDetail(fullRepoName);


    }

    @Override
    public void startWeb(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
