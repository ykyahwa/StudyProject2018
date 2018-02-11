package com.tistory.ykyh.materialdesign;

import android.support.v4.app.Fragment;

/**
 * Created by eokhyunlee on 2018. 2. 11..
 */

class MyFragmentFactory {

    private static MyFragmentFactory instance = new MyFragmentFactory();

    private MyFragmentFactory() {}



    public static synchronized  MyFragmentFactory getInstance() {
        if (instance == null) {
            instance = new MyFragmentFactory();
        }

        return instance;
    }

    public Fragment create(int position) {
        return MyFragment.createInstance(position);

    }
}
