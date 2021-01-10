package com.example.androidmvp.utils.download;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.androidmvp.R;
import com.example.androidmvp.app.App;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.os.Environment.DIRECTORY_PICTURES;

/**
 * Created by 阿飞 on 2017/4/16.
 */

public class ImageDownload {

    private static ImageDownload INSTANCE;

    private OnImageDownListener mOnImageDownListener;

    public static ImageDownload getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageDownload();
        }
        return INSTANCE;
    }

    private Context mContext;

    private ImageDownload() {
        mContext = App.getInstance();
    }

    public void setOnImageDownListener(OnImageDownListener onImageDownListener) {
        this.mOnImageDownListener = onImageDownListener;
    }

    public void saveImage(String url) {
        saveImage(getImageName(url), url);
    }

    //Glide保存图片
    public void saveImage(final String imageName, String url) {
        Glide.with(mContext)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        saveImage(imageName, resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    public void saveImage(String filename, Bitmap bitmap) {
        File saveImageFile = getSaveImageFile(filename);
        if (saveImageFile == null) {
            // 保存失败
            if (this.mOnImageDownListener != null) {
                this.mOnImageDownListener.onFail(new Exception("SD卡不存在或者不可读写"));
            }
            return;
        }
        try {
            OutputStream fOut = new FileOutputStream(saveImageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.close();
            if (this.mOnImageDownListener != null) {
                this.mOnImageDownListener.onSuccess(filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (this.mOnImageDownListener != null) {
                this.mOnImageDownListener.onFail(new Exception("SD卡不存在或者不可读写", e));
            }
        }
    }

    //往SD卡写入文件的方法
    public void saveImage(String filename, byte[] bytes) {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        File saveImageFile = getSaveImageFile(filename);
        if (saveImageFile == null) {
            if (this.mOnImageDownListener != null) {
                this.mOnImageDownListener.onFail(new Exception("SD卡不存在或者不可读写"));
            }
            return;
        }
        try {
            FileOutputStream output = new FileOutputStream(saveImageFile);
            output.write(bytes);
            //将bytes写入到输出流中
            output.close();
            //关闭输出流
            if (this.mOnImageDownListener != null) {
                this.mOnImageDownListener.onSuccess(filename);
            }
        } catch (Exception e) {
            if (this.mOnImageDownListener != null) {
                this.mOnImageDownListener.onFail(new Exception("图片下载失败"));
            }
            e.printStackTrace();
        }
    }

    private File getSaveImageFile(String fileName) {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String filePath = Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES) + "/" + mContext.getResources().getString(R.string.app_name);
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                fileName = filePath + "/" + fileName;
                //这里就不要用openFileOutput了,那个是往手机内存中写数据的
                File file = new File(fileName);
                if (!file.exists()) {
                    file.createNewFile();
                } else {
                    file.delete();
                    file.createNewFile();
                }
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getImageName(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public interface OnImageDownListener {
        void onSuccess(String path);

        void onFail(Exception e);
    }
}
