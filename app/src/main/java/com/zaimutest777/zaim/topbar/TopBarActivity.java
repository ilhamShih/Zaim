package com.zaimutest777.zaim.topbar;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.zaimutest777.zaim.R;
import com.zaimutest777.zaim.adapter.MainPagerAdapter;

public class TopBarActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    MainPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);

        viewPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);


        tabLayout.setupWithViewPager(viewPager);
    }
}
