package com.example.day12_24;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_shop)
    RadioButton rbLove;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.rb_title)
    RadioButton rbTitle;
    @BindView(R.id.rb_widgets)
    RadioButton rbWidgets;
    @BindView(R.id.rb_people)
    RadioButton rbPeople;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initListener() {
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbLove.setSelected(false);
                        rbHome.setSelected(true);
                        rbPeople.setSelected(false);
                        rbTitle.setSelected(false);
                        rbWidgets.setSelected(false);
                        break;
                    case 1:
                        rbLove.setSelected(false);
                        rbHome.setSelected(false);
                        rbPeople.setSelected(false);
                        rbTitle.setSelected(true);
                        rbWidgets.setSelected(false);
                        break;
                    case 2:
                        rbLove.setSelected(false);
                        rbHome.setSelected(false);
                        rbPeople.setSelected(false);
                        rbTitle.setSelected(false);
                        rbWidgets.setSelected(true);
                        break;
                    case 3:
                        rbLove.setSelected(true);
                        rbHome.setSelected(false);
                        rbPeople.setSelected(false);
                        rbTitle.setSelected(false);
                        rbWidgets.setSelected(false);
                        break;
                    case 4:
                        rbLove.setSelected(false);
                        rbHome.setSelected(false);
                        rbPeople.setSelected(true);
                        rbTitle.setSelected(false);
                        rbWidgets.setSelected(false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vpMain.setCurrentItem(0);
                        break;
                    case R.id.rb_title:
                        vpMain.setCurrentItem(1);
                        break;
                    case R.id.rb_widgets:
                        vpMain.setCurrentItem(2);
                        break;
                    case R.id.rb_shop:
                        vpMain.setCurrentItem(3);
                        break;
                    case R.id.rb_people:
                        vpMain.setCurrentItem(4);
                        break;
                }
            }
        });
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TitleFragment());
        fragments.add(new WidgetsFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new LoveFragment());
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager());
        vpMain.setAdapter(adapter);
        rbHome.setSelected(true);
    }

    private class VpAdapter extends FragmentPagerAdapter {
        public VpAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}