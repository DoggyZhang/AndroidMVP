package com.example.androidmvp.presenter.contract;

import com.example.androidmvp.base.presenter.base.BasePresenter;
import com.example.androidmvp.base.view.BaseView;

public interface MainContract {

    interface View extends BaseView {
        void showMessage(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void getMessage();
    }
}
