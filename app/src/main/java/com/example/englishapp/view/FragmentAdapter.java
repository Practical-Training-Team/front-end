package com.example.englishapp.view;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {

    private FragmentManager fragmentManager;
    private List<Fragment> mList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        Fragment fragment = mList.get(position);
        fragmentManager.beginTransaction().hide(fragment).commit();
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
