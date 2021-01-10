package com.example.androidmvp.base.view;

public interface BaseView {
    /**
     * 显示加载失败的信息
     *
     * @param e 失败原因
     */
    void showError(Exception e);

}
