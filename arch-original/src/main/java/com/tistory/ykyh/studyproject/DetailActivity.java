package com.tistory.ykyh.studyproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_FULL_REPOSITORY_NAME = "EXTRA_FULL_REPOSITORY_NAME";
    private ImageView ivImage;
    private TextView tvName;
    private TextView tvDetail;
    private TextView tvStar;
    private TextView tvFork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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

        loadRepositories(fullRepoName);
    }

    private void loadRepositories(String fullRepoName) {

        String[] repoData = fullRepoName.split("/");
        String owner = repoData[0];
        String repoName = repoData[1];

        RetrofitCreator.getGitHubService().detailRepo(owner, repoName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GitHubService.RepositoryItem>() {
                    @Override
                    public void onCompleted() {
                        
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GitHubService.RepositoryItem repositoryItem) {
                        setupRepositoryInfo(repositoryItem);
                    }
                });


    }

    private void setupRepositoryInfo(GitHubService.RepositoryItem repositoryItem) {
//        https://api.github.com/search/repositories?sort=stars&order=desc&q=java
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
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(repositoryItem.html_url)));
            } catch (Exception e) {
                Snackbar.make(findViewById(android.R.id.content), "링크를 열 수 없습니다", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        };
        tvName.setOnClickListener(listener);
        ivImage.setOnClickListener(listener);
    }
}
