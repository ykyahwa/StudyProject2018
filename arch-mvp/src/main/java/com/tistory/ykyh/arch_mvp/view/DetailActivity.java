package com.tistory.ykyh.arch_mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.tistory.ykyh.arch_mvp.R;
import com.tistory.ykyh.arch_mvp.model.GitHubService;
import com.tistory.ykyh.arch_mvp.presenter.DetailPresenter;
import com.tistory.ykyh.arch_mvp.presenter.DetailPresenterImpl;

public class DetailActivity extends AppCompatActivity implements DetailPresenter.View{

    public static final String EXTRA_FULL_REPOSITORY_NAME = "EXTRA_FULL_REPOSITORY_NAME";
    private ImageView ivImage;
    private TextView tvName;
    private TextView tvDetail;
    private TextView tvStar;
    private TextView tvFork;

    private DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        presenter = new DetailPresenterImpl();
        presenter.attachView(this);

        initView();
    }

    private void initView() {
        ivImage = findViewById(R.id.ivImage);
        tvName = findViewById(R.id.tvName);
        tvDetail = findViewById(R.id.tvDetail);
        tvStar = findViewById(R.id.tvStar);
        tvFork = findViewById(R.id.tvFork);


        Intent intent = getIntent();
        String fullRepoName = intent.getStringExtra(EXTRA_FULL_REPOSITORY_NAME);


        presenter.requestDetail(fullRepoName);

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void setRepositoryInfo(GitHubService.RepositoryItem repositoryItem) {
        tvName.setText(repositoryItem.full_name);
        tvDetail.setText(repositoryItem.description);
        tvStar.setText("Star:"+repositoryItem.stargazers_count);
        tvFork.setText("Fork:"+repositoryItem.forks_count);
        // 서버로부터 이미지를 가져와 imageView에 넣는다
        Glide.with(DetailActivity.this)
                .load(repositoryItem.owner.avatar_url)
                .asBitmap().centerCrop().into(new BitmapImageViewTarget(ivImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                ivImage.setImageDrawable(circularBitmapDrawable);
            }
        });
        // 로고와 리포지토리 이름을 탭하면 작자의 GitHub 페이지를 브라우저로 연다
        View.OnClickListener listener = v -> {
            presenter.showBrower(repositoryItem.html_url);
        };
        tvName.setOnClickListener(listener);
        ivImage.setOnClickListener(listener);
    }
}
