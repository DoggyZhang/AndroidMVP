package com.example.androidmvp.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.androidmvp.base.mode.OnNightMode;
import com.example.androidmvp.base.presenter.impl.PresenterImpl;
import com.example.androidmvp.base.view.BaseView;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends PresenterImpl>
        extends Activity implements BaseView, OnNightMode {

    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = setPresenter();
        mPresenter.attachView(this);
        resumeState(savedInstanceState);
        doBeforeContentView();
        setContentView(getContentView());
        doAfterContentView();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mPresenter != null) {
            this.mPresenter.detachView();
            this.mPresenter = null;
        }
    }

    /**
     * 设置当前视图的 Presenter
     *
     * @return
     */
    protected abstract P setPresenter();

    public P getPresenter() {
        return mPresenter;
    }

    /**
     * 获取当前布局资源 ID
     *
     * @return
     */
    protected abstract int getContentView();

    /**
     * 方法在 setContentView（）之前调用
     */
    protected void doBeforeContentView() {

    }

    /**
     * 该方法在 setContentView（）之后调用
     */
    protected void doAfterContentView() {
        ButterKnife.bind(this);
    }

    /**
     * 初始化 控件事件
     */
    protected abstract void initEvent();

    /**
     * 恢复 Activity 状态
     *
     * @param savedInstanceState
     */
    protected void resumeState(Bundle savedInstanceState) {

    }
}