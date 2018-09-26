package com.lzhs.lzhsbannerdemo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lzhs.library.LZHSBannerView;
import com.lzhs.library.holder.LZHSHolderCreator;
import com.lzhs.library.holder.LZHSViewHolder;
import com.lzhs.library.transformers.DepthPageTransformer;
import com.lzhs.lzhsbannerdemo.R;
import com.lzhs.lzhsbannerdemo.banner_view_holder.FlodBannerViewHolder;
import com.lzhs.lzhsbannerdemo.banner_view_holder.NomeBannerViewHolder;
import com.lzhs.lzhsbannerdemo.banner_view_holder.PaddingBannerViewHolder;
import com.lzhs.lzhsbannerdemo.banner_view_holder.beans.ImageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 无限轮播Banner  <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/9/25$ 上午11:22$ <br/>
 */
public class DynamicFragment extends Fragment {
    private static final String TAG = DynamicFragment.class.getSimpleName();
    View mRootView;
    public static final int[] bannerFlod = new int[]{R.mipmap.banner01, R.mipmap.banner02, R.mipmap.banner03, R.mipmap.banner04, R.mipmap.banner05};
    private LZHSBannerView mBannerNormal, mBannerFlod, mBannerPadding;

    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_dynamic, container, false);
        initViews();
        return mRootView;
    }


    private void initViews() {
        mBannerNormal = mRootView.findViewById(R.id.mBannerNormal);
        mBannerNormal.setBannerPageClickListener(new LZHSBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(getContext(), "你点击了 普通Banner 下标 ：" + position, Toast.LENGTH_LONG).show();
            }
        });
        mBannerNormal.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "----->addPageChangeLisnter:" + position + "positionOffset:" + positionOffset + "positionOffsetPixels:" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "addPageChangeLisnter:" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        List<ImageBean> bannerNomeList = new ArrayList<>();
        for (int imageRes : bannerFlod)
            bannerNomeList.add(new ImageBean(0, imageRes, ""));
        mBannerNormal.setPages(bannerNomeList, new LZHSHolderCreator<NomeBannerViewHolder>() {
            @Override
            public NomeBannerViewHolder createViewHolder() {
                return new NomeBannerViewHolder();
            }
        });
        mBannerNormal.setIndicatorVisible(false);
        mBannerNormal.setPageTransformer(new DepthPageTransformer());

        mBannerFlod = mRootView.findViewById(R.id.mBannerFlod);
        mBannerFlod.setPages(bannerNomeList, new LZHSHolderCreator<FlodBannerViewHolder>() {
            @Override
            public FlodBannerViewHolder createViewHolder() {
                return new FlodBannerViewHolder();
            }
        });
        mBannerPadding = mRootView.findViewById(R.id.mBannerPadding);
        mBannerPadding.setPages(bannerNomeList, new LZHSHolderCreator() {
            @Override
            public LZHSViewHolder createViewHolder() {
                return new PaddingBannerViewHolder();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        mBannerNormal.pause();
        mBannerFlod.pause();
        mBannerPadding.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBannerNormal.start();
        mBannerFlod.start();
        mBannerPadding.start();
    }
}
