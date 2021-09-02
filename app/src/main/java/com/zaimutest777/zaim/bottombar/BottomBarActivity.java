package com.zaimutest777.zaim.bottombar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zaimutest777.zaim.R;
import com.zaimutest777.zaim.bottombar.fragments.CardFragment;
import com.zaimutest777.zaim.bottombar.fragments.KredityFragment;
import com.zaimutest777.zaim.bottombar.fragments.ZaimyFragment;

public class BottomBarActivity extends AppCompatActivity {
    Toolbar toolbar;
    private static final String SELECTION = "SELECTION";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        setupBottomMenu(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTION, bottomNavigationView.getSelectedItemId());
    }

    private void setupToolbar() {


    }

    private void setupBottomMenu(Bundle savedInstanceState) {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.page_home:
                    showFragment(new ZaimyFragment());
                    toolbar.setTitle(R.string.bottom_nav_home);
                    break;
                case R.id.page_list:
                    showFragment(new CardFragment());
                    toolbar.setTitle(R.string.bottom_nav_list);
                    break;
                case R.id.page_fav:
                    showFragment(new KredityFragment());
                    toolbar.setTitle(R.string.bottom_nav_fav);
                    break;

                default:
                    throw new IllegalArgumentException("item not implemented : " + item.getItemId());
            }
            return true;
        });


        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.page_home);
        } else {
            bottomNavigationView.setSelectedItemId(savedInstanceState.getInt(SELECTION));
        }

    }

    private void showFragment(Fragment frg) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, frg)
                .commitAllowingStateLoss();
    }


}