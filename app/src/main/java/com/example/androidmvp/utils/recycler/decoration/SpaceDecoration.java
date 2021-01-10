package com.example.androidmvp.utils.recycler.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by 阿飞 on 2017/4/15.
 */

public class SpaceDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
        }
    }
}
