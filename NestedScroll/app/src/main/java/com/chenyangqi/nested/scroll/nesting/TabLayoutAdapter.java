package com.chenyangqi.nested.scroll.nesting;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author chenyangqi
 * @describe
 * @time 2021/7/21 11:18
 */
public class TabLayoutAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    TabLayoutAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}

