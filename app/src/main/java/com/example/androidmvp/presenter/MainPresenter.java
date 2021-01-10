package com.example.androidmvp.presenter;

import com.example.androidmvp.base.presenter.impl.PresenterImpl;
import com.example.androidmvp.presenter.contract.MainContract;

public class MainPresenter extends PresenterImpl<MainContract.View> implements MainContract.Presenter {
    public MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public void getMessage() {
        getView().showMessage("Android MVP Test");
    }
}
