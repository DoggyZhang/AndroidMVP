package com.example.androidmvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidmvp.base.presenter.impl.PresenterImpl;
import com.example.androidmvp.base.view.BaseView;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends PresenterImpl> extends Fragment implements BaseView {

    protected P mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    protected boolean isInited = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
        this.mContext = context;
        this.mPresenter = setPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = LayoutInflater.from(mContext).inflate(setLayoutId(), container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter.attachView(this);
        handleArgument(getArguments());
        initEventAndData(view, savedInstanceState);
        isInited = true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInited && !hidden) {
            initEventAndData(mView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mView = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mContext = null;
        this.mActivity = null;
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public void refresh() {

    }

    protected abstract P setPresenter();

    protected P getPresenter() {
        return mPresenter;
    }

    protected abstract void getData();

    protected abstract int setLayoutId();

    protected abstract void handleArgument(Bundle argument);

    protected abstract void initEventAndData(View view, @Nullable Bundle savedInstanceState);

    protected void initEventAndData(View view) {
        initEventAndData(view, null);
    }
}
