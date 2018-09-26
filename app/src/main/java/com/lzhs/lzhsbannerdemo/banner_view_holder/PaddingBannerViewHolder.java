package com.lzhs.lzhsbannerdemo.banner_view_holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.lzhs.library.holder.LZHSViewHolder;
import com.lzhs.lzhsbannerdemo.GlideApp;
import com.lzhs.lzhsbannerdemo.R;
import com.lzhs.lzhsbannerdemo.banner_view_holder.beans.ImageBean;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/9/25$ 下午4:30$ <br/>
 */
public class PaddingBannerViewHolder implements LZHSViewHolder<ImageBean> {
    ImageView mImageView;

    @Override
    public View createView(Context context) {
        // 返回页面布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item_padding, null);
        mImageView = view.findViewById(R.id.banner_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, ImageBean data) {
        // 数据绑定
        if (data.getPathType() == 0)
            mImageView.setImageResource(data.getImageRes());
        else loadImage(context, data.getImagePath());
    }

    private void loadImage(Context context, String path) {
        GlideApp.with(context)
                .load(path)
                .centerCrop()
                .placeholder(R.drawable.loading_spinner)
                .into(mImageView);

    }
}
