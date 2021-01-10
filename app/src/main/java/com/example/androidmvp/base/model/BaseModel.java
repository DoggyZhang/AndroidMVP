package com.example.androidmvp.base.model;

public interface BaseModel {
    void getData(OnLoadCompleteListener onLoadCompleteListener, String requestUrl);

    interface OnLoadCompleteListener {
        void onLoadComplete(byte[] bytes, String requestUrl);

        void onLoadError(Exception e);
    }
}
