# 可折叠Banner

[![](https://img.shields.io/badge/LZHSBanner-v1.1-brightgreen.svg)](https://jitpack.io/#LZHS/LZHSBanner)



### gradle
Step 1.Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
 		 ...
 		 maven { url 'https://jitpack.io' }
 	}
}
```

Step 2. Add the dependency

```	
dependencies {
	implementation 'com.github.LZHS:LZHSBanner:v1.1'
}
```  
### 效果图  

  <div align=center>
 <img src="https://github.com/LZHS/LZHSBanner/blob/master/images/img01.jpg" width = 30% height = 40% /> 
 <img src="https://github.com/LZHS/LZHSBanner/blob/master/images/img02.jpg" width = 30% height = 40% />
 <img src="https://github.com/LZHS/LZHSBanner/blob/master/images/img03.jpg" width = 30% height = 40% />
 </div>  
 
### 调用方法

* 布局调用


```  

 <com.lzhs.library.LZHSBannerView
            android:id="@+id/mBannerPadding"
            android:layout_width="match_parent"
            android:layout_height="140dp"
            app:canLoop="true"
            app:indicatorAlign="center"
            app:indicatorPaddingBottom="10dp"
            app:isFoldEffect="true"
            app:middle_page_cover="true" />
```


* 代码设置



```

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
        for (String images : imagePath)
            bannerNomeList.add(new ImageBean(1, 0, images));
        mBannerNormal.setPages(bannerNomeList, new LZHSHolderCreator<NomeBannerViewHolder>() {
            @Override
            public NomeBannerViewHolder createViewHolder() {
                return new NomeBannerViewHolder();
            }
        });
        mBannerNormal.setIndicatorVisible(false);
        mBannerNormal.setPageTransformer(new DepthPageTransformer());

```


* LZHSViewHolder



```

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


```


### 提供属性以及方法 


| 属性名 | 属性意义 | 属性值 |
| :------:| :------: | :------: |
| isFoldEffect | 是否开启banner 折叠效果 | true 开启， false 关闭 |
| canLoop | 是否开启轮播图片 | true 开启， false 关闭 |  
| indicatorPaddingLeft | ndicator 距离左边的距离 | |  
| indicatorPaddingRight | indicator 距离右边的距离 |  |  
| indicatorPaddingTop | indicator 距离上边的距离 |  |  
| indicatorPaddingBottom | indicator 距离下边的距离 |   |
| indicatorPaddingBottom | indicator 距离下边的距离 |   |
| indicatorAlign | indicator 对齐方式 | left 居左， center 居中 ， right  居右|
| middle_page_cover | i中间Page是否覆盖两边，默认覆盖 | true 开启， false 关闭  |
 


---------------------------

* 方法

> start()  : 开始轮播 应该确保在调用用了 **setPages(List, LZHSHolderCreator)**  之后调用这个方法开始轮播   
> 
> pause() : 停止轮播
> 
> setDelayedTime(int delayedTime) : 设置BannerView 的切换时间间隔
> 
> addPageChangeLisnter(ViewPager.OnPageChangeListener onPageChangeListener) : 设置Banner 轮播页面，改变监听
> 
> setBannerPageClickListener(BannerPageClickListener bannerPageClickListener) : 添加Page点击事件
> 
> setIndicatorVisible(boolean visible) : 是否显示Indicator
> 
> setIndicatorPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) : 设置indicator padding
> 
> ViewPager getViewPager() : 返回ViewPager
> 
> setIndicatorRes(@DrawableRes int unSelectRes, @DrawableRes int selectRes) : 设置indicator 图片资源
> 
> setPages(List<T> datas, LZHSHolderCreator mzHolderCreator) : 设置数据，这是最重要的一个方法。 **其他的配置应该在这个方法之前调用。** datas           Banner 展示的数据集合 mHolderCreator ViewHolder生成器
> 
> setPageTransformer(ViewPager.PageTransformer mPageTransformer) : 设置Banner 切换效果  注意该方法只在 普通Banner 切换下，才会生效
> 
>  setIndicatorAlign(IndicatorAlign indicatorAlign) ： 设置Indicator 的对齐方式
> 
> LinearLayout getIndicatorContainer() : 获取 下标指示器控件
> 
> setDuration(int duration) : 设置Banner切换的速度
> 
> int getDuration() : 获取Banner页面切换动画时间
> 
> setUseDefaultDuration(boolean useDefaultDuration) : 设置是否使用ViewPager默认是的切换速度


***注意***
> 调用 **setIndicatorPadding（）**方法之前必须设置 **setIndicatorVisible()为true**
> 
> ViewPager getViewPager()
> 
> 数据集合的长度至少为3,否则，自动为普通Banner模式 不管配置的:open_mz_mode 属性的值是否为true
> 
> **setPageTransformer**注意该方法只在 普通Banner 切换下，才会生效
> 
> 

### DemoAPK
[DemoAPK](https://github.com/LZHS/LZHSBanner/raw/master/apk/LZHSBanner.apk)


<img src="https://github.com/LZHS/LZHSBanner/blob/master/images/qrcode.png" width = 40% height = 40% />


### 参考
[https://www.jianshu.com/p/653680cfe877](https://www.jianshu.com/p/653680cfe877)
