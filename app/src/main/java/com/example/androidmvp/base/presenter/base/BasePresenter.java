package com.example.androidmvp.base.presenter.base;


import com.example.androidmvp.base.view.BaseView;

/**
 * Created by 阿飞 on 2017/4/8.
 */

public interface BasePresenter<V extends BaseView> {
    /**
     * 绑定视图
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 解除绑定视图
     */
    void detachView();
}
