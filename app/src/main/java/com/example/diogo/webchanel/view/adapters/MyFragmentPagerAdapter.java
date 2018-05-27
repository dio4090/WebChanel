package com.example.diogo.webchanel.view.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.diogo.webchanel.view.fragments.FragmentDetails;
import com.example.diogo.webchanel.view.fragments.FragmentServices;
import com.example.diogo.webchanel.view.fragments.FragmentSchedules;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTabTitles;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] mTabTitles) {
        super(fm);
        this.mTabTitles = mTabTitles;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FragmentDetails();
            case 1:
                return new FragmentServices();
            case 2:
                return new FragmentSchedules();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return this.mTabTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.mTabTitles[position];
    }
}
