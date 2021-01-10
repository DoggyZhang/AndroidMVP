package com.example.androidmvp.ui.main;

import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmvp.R;
import com.example.androidmvp.base.BaseActivity;
import com.example.androidmvp.presenter.MainPresenter;
import com.example.androidmvp.presenter.contract.MainContract;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.main_text)
    private TextView mMainText;

    @Override
    protected MainPresenter setPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void doAfterContentView() {
        super.doAfterContentView();
        getPresenter().getMessage();
    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void showMessage(String message) {
        mMainText.setText(message);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}