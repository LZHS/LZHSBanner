package com.lzhs.lzhsbannerdemo.banner_view_holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzhs.library.holder.LZHSViewHolder;
import com.lzhs.lzhsbannerdemo.GlideApp;
import com.lzhs.lzhsbannerdemo.R;
import com.lzhs.lzhsbannerdemo.banner_view_holder.beans.ImageBean;

import org.w3c.dom.Text;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/9/25$ 下午4:30$ <br/>
 */
public class NomeBannerViewHolder implements LZHSViewHolder<ImageBean> {
    private ImageView mImageView;
    private TextView mTextShowIndicator;

    @Override
    public View createView(Context context) {
        // 返回页面布局文件
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        mImageView = view.findViewById(R.id.mImageView);
        mTextShowIndicator = view.findViewById(R.id.mTextShowIndicator);
        return view;
    }

    @Override
    public void onBind(Context context, int position, ImageBean data) {
        // 数据绑定
        mTextShowIndicator.setText("当前页面下标指示器  [" + position + "]， 说点啥呗");

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
