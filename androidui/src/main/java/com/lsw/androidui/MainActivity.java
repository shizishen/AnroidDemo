package com.lsw.androidui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lsw.androidui.adapter.MainactivityAdapter;
import com.lsw.androidui.fragment.DiscoveryFragment;
import com.lsw.androidui.fragment.MainFragment;
import com.lsw.androidui.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<Fruit>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //fragment+viewpager实现底部导航栏相关代码
        ViewPager viewPager = findViewById(R.id.main_vp);
        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bnv);
        initData();
        MainactivityAdapter mainactivityAdapter = new MainactivityAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(mainactivityAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.main);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.discovery);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.mine);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //图标选择监听
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.main){
                    viewPager.setCurrentItem(0);

                } else if (menuItem.getItemId() == R.id.discovery) {
                    viewPager.setCurrentItem(1);

                }else if (menuItem.getItemId() == R.id.mine){
                    viewPager.setCurrentItem(2);
                }
                return true;
            }
        });
        
        
        
        

    }

    private void initData() {
        MainFragment mainFragment = MainFragment.newInstance("首页","");
        fragmentList.add(mainFragment);

        DiscoveryFragment discoveryFragment = DiscoveryFragment.newInstance("教程","");
        fragmentList.add(discoveryFragment);

        MineFragment mineFragment = MineFragment.newInstance("个人","");
        fragmentList.add(mineFragment);
    }

}
