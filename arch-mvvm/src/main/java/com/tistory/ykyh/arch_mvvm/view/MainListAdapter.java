package com.tistory.ykyh.arch_mvvm.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tistory.ykyh.arch_mvvm.MainListPresenter;
import com.tistory.ykyh.arch_mvvm.R;
import com.tistory.ykyh.arch_mvvm.databinding.RepositoryItemBinding;
import com.tistory.ykyh.arch_mvvm.model.GitHubService;
import com.tistory.ykyh.arch_mvvm.viewmodel.RepositoryItemViewModel;

import java.util.List;


/**
 * Created by eokhyunlee on 2018. 1. 27..
 */

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainViewHolder>{

    private Context context;
    private List<GitHubService.RepositoryItem> items;
    private MainListPresenter view;

    public MainListAdapter(Context context, MainListPresenter view) {
        this.context = context;
        this.view = view;
    }

    public void setItemsAndRefresh(List<GitHubService.RepositoryItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RepositoryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.repository_item, parent,false);
        binding.setViewModel(new RepositoryItemViewModel(view));

        return new MainViewHolder(binding.getRoot(), binding.getViewModel());
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        GitHubService.RepositoryItem item = items.get(position);

        holder.loadItem(item);
    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }



    public class MainViewHolder extends RecyclerView.ViewHolder {

        private RepositoryItemViewModel viewModel;

        public MainViewHolder(View itemView, RepositoryItemViewModel viewModel) {
            super(itemView);
            this.viewModel = viewModel;
        }

        public void loadItem(GitHubService.RepositoryItem item) {
            viewModel.loadItem(item);
        }
    }

    interface OnRepositoryItemClickListener {
        void onRepositoryItemClick(GitHubService.RepositoryItem item);
    }
}
