package com.tistory.ykyh.studyproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by eokhyunlee on 2018. 1. 27..
 */

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainViewHolder>{

    private Context context;
    private OnRepositoryItemClickListener listener;
    private List<GitHubService.RepositoryItem> items;

    public MainListAdapter(Context context,OnRepositoryItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setItemsAndRefresh(List<GitHubService.RepositoryItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.repository_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        GitHubService.RepositoryItem item = items.get(position);

        holder.itemView.setOnClickListener(view -> {
            if (listener != null) listener.onRepositoryItemClick(items.get(position));
        });

        holder.repoName.setText(item.name);
        holder.repoDetail.setText(item.description);
        holder.starCount.setText("star:"+item.stargazers_count);
        // 이미지는 Glide라는 라이브러리로 데이터를 설정한다
        Glide.with(context)
                .load(item.owner.avatar_url)
                .asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.repoImage) {
            @Override
            protected void setResource(Bitmap resource) {
                // 이미지를 동그랗게 만든다
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.repoImage.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }



    public class MainViewHolder extends RecyclerView.ViewHolder {

        private final TextView repoName;
        private final TextView repoDetail;
        private final ImageView repoImage;
        private final TextView starCount;

        public MainViewHolder(View itemView) {
            super(itemView);
            repoName = (TextView) itemView.findViewById(R.id.repo_name);
            repoDetail = (TextView) itemView.findViewById(R.id.repo_detail);
            repoImage = (ImageView) itemView.findViewById(R.id.repo_image);
            starCount = (TextView) itemView.findViewById(R.id.repo_star);
        }
    }

    interface OnRepositoryItemClickListener {
        void onRepositoryItemClick(GitHubService.RepositoryItem item);
    }
}
