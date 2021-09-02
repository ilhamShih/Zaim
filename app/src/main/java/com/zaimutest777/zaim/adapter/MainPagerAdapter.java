package com.zaimutest777.zaim.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zaimutest777.zaim.topbar.fragments.DebitCardsFrag;
import com.zaimutest777.zaim.topbar.fragments.RasrochkaFrag;
import com.zaimutest777.zaim.topbar.fragments.CreditCardsFrag;

public class MainPagerAdapter extends FragmentPagerAdapter {
    int pager_number = 3;
    public MainPagerAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
            fragment = new CreditCardsFrag();
        else if (position == 1)
            fragment = new DebitCardsFrag();
        else if (position == 2)
            fragment = new RasrochkaFrag();

        return fragment;
    }


    @Override
    public int getCount()
    {
        return pager_number;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;

        if (position == 0)
            title = "Кредитные";
        else if (position == 1)
            title = "Дебетовые";
        else if (position == 2)
            title = "Рассрочки";
        return title;
    }

}