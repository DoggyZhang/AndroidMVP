package com.example.androidmvp.base.presenter.impl;


import com.example.androidmvp.base.presenter.base.BasePresenter;
import com.example.androidmvp.base.view.BaseView;

import java.lang.ref.WeakReference;

public class PresenterImpl<V extends BaseView> implements BasePresenter<V> {

    protected WeakReference<V> mView;

    public PresenterImpl(V view) {
        attachView(view);
    }

    @Override
    public void attachView(V view) {
        if (mView != null) {
            detachView();
        }
        this.mView = new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    public V getView() {
        return mView == null ? null : mView.get();
    }
}