package com.aganticsoft.phyxhidephotosandvideos.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.aganticsoft.phyxhidephotosandvideos.R;
import com.aganticsoft.phyxhidephotosandvideos.adapter.HomePagerAdapter;
import com.aganticsoft.phyxhidephotosandvideos.view.fragment.AlbumFragment;
import com.aganticsoft.phyxhidephotosandvideos.view.fragment.InfoFragment;
import com.aganticsoft.phyxhidephotosandvideos.view.fragment.SettingFragment;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector, BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;


    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        HomePagerAdapter pagerAdapter = new HomePagerAdapter(getSupportFragmentManager(),
                Arrays.asList(
                        AlbumFragment.newInstance(),
                        InfoFragment.newInstance(),
                        SettingFragment.newInstance()
                ));
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_album:
                viewPager.setCurrentItem(0);
                break;
            case R.id.action_info:
                viewPager.setCurrentItem(1);
                break;
            case R.id.action_setting:
                viewPager.setCurrentItem(2);
                break;
        }

        return true;
    }

    // <editor-fold desc="[====================== View Pager Listener ===================]">

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                bottomNavigationView.setSelectedItemId(R.id.action_album);
                break;
            case 1:
                bottomNavigationView.setSelectedItemId(R.id.action_info);
                break;
            case 2:
                bottomNavigationView.setSelectedItemId(R.id.action_setting);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    // </editor-fold>


}
