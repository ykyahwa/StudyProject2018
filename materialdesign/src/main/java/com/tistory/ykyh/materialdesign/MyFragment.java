package com.tistory.ykyh.materialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by eokhyunlee on 2018. 2. 11..
 */

public class MyFragment extends Fragment {

    private static final String INDEX = "index";

    public MyFragment(){}

    public static MyFragment createInstance(int index) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(INDEX);
        View view = inflater.inflate(R.layout.fragment_my, null);
        TextView tv = view.findViewById(R.id.text);

        tv.setText("INDEX = " + index);

        return view;
    }
}
