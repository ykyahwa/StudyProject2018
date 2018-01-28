package com.tistory.ykyh.arch_mvp;

/**
 * Created by eokhyunlee on 2016-08-27.
 */
public interface Presenter<V> {
    void attachView(V view);
    void detachView();
}
