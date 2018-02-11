package com.tistory.ykyh.materialdesign;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by eokhyunlee on 2018. 2. 11..
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private static final int MAX_COUNT = 3;

    public MyFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return MyFragmentFactory.getInstance().create(position+1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "탭 "+ (position +1);
    }

    @Override
    public int getCount() {
        return MAX_COUNT;
    }
}
