package com.zaimutest777.zaim.bottombar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.zaimutest777.zaim.R;
import com.zaimutest777.zaim.adapter.MainPagerAdapter;


public class CardFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    MainPagerAdapter viewPagerAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        viewPager = layout.findViewById(R.id.view_pager);
        tabLayout = layout.findViewById(R.id.tabs);
        viewPagerAdapter = new MainPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(setPageLimit());
        viewPagerAdapter.getPageTitle(getId());
        tabLayout.setSelectedTabIndicatorHeight((int) (3 * getResources() .getDisplayMetrics().density));
        tabLayout.setupWithViewPager(viewPager);
        return layout;
    }
    public int setPageLimit()
    {
        return viewPagerAdapter.getCount();
    }




}