package com.lsw.androidui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentStatePagerAdapter并不适合用于需要保持 Fragment 所有状态的情况，例如表单输入、视频播放等。
 * 对于这些情况，可以考虑使用 FragmentPagerAdapter 或自定义适配器来保存和恢复 Fragment 的状态。
 */
public class MainactivityAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList = new ArrayList<>();

    public MainactivityAdapter(@NonNull FragmentManager fm,  List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
