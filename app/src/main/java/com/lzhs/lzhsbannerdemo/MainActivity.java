package com.lzhs.lzhsbannerdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lzhs.lzhsbannerdemo.fragments.DynamicFragment;
import com.lzhs.lzhsbannerdemo.fragments.NetFragment;
import com.lzhs.lzhsbannerdemo.fragments.StaticFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    ViewPager mViewPager;
    BottomNavigationView mBottomNavigationView;

    List<Fragment> mFragmnets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mToolbar = findViewById(R.id.mToolbar);
        mViewPager = findViewById(R.id.mViewPager);
        mBottomNavigationView = findViewById(R.id.mBottomNavigationView);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("Banner");
        setSupportActionBar(mToolbar);
        mFragmnets.add(StaticFragment.newInstance());
        mFragmnets.add(DynamicFragment.newInstance());
        mFragmnets.add(NetFragment.newInstance());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragmnets.get(i);
            }

            @Override
            public int getCount() {
                return mFragmnets.size();
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mBottomNavigationView.getMenu().getItem(i).setChecked(true);
                switch (mBottomNavigationView.getMenu().getItem(i).getItemId()) {
                    case R.id.mStaticNavigation:
                        mToolbar.setTitle("静态Banner");
                        break;
                    case R.id.mDynamicNavigation:
                        mToolbar.setTitle("动态Banner");
                        break;
                    case R.id.NETNavigation:
                        mToolbar.setTitle("网络图片Banner");
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mStaticNavigation:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.mDynamicNavigation:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.NETNavigation:
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }


}
