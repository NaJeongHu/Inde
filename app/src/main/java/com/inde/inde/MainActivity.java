package com.inde.inde;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import com.google.android.material.tabs.TabLayout;
import com.inde.inde.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //TabLayout 설정
        setTabLayout();
        //ViewPager 연결
        setViewPager();

    }

    @TargetApi(Build.VERSION_CODES.N)
    private void setTabLayout(){

        //어댑터 초기화
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), PAGE_POSITION);
        viewPager = binding.viewPager;//== (viewPager = findViewById(R.id.viewPager);
        //어댑터 연결
        viewPager.setAdapter(adapter);

        tabLayout = binding.tab;//== (tabLayout = findViewById(R.id.tab);
        //ArrayList에 저장된 순서대로 Tab 이름을 지정해줌
        //tabNames.stream().forEach(name ->tabLayout.addTab(tabLayout.newTab().setText(name)));
        tabLayout.setupWithViewPager(viewPager);


        //Tab 아이콘 설정
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_checkbox2);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_search);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_stack);
        tabLayout.getTabAt(3).setIcon(R.drawable.icon_user2);

        //Tab 아이콘 색상 초기화 (맨 처음 화면일때의 Tab 아이콘 색상 설정)
        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#808080"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(Color.parseColor("#808080"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(Color.parseColor("#808080"), PorterDuff.Mode.SRC_IN);
    }

    private void setViewPager() {

        //페이지 리스너 (viewPager와 TabLayout의 페이지를 맞춰줌)
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //탭 선택 리스너 (탭 행동 설정)
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //선택된 탭일 때
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //선택된 탭과 연결된 fragment를 가져옴
                viewPager.setCurrentItem(tab.getPosition(),false);
                //아이콘 색상을 흰색으로 설정
                tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                setStatusBarColor(tab.getPosition());
            }
            //선택되지 않은 탭일 때
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //아이콘 색상을 #0070C0 으로 설정
                tab.getIcon().setColorFilter(Color.parseColor("#808080"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setStatusBarColor(int position) {

        Window window = getWindow();

        switch (position) {
            case 0:
                window.setStatusBarColor(getResources().getColor(R.color.main_navy));
                break;
            case 1:
                window.setStatusBarColor(getResources().getColor(R.color.main_mauve));
                break;
            case 2:
                window.setStatusBarColor(getResources().getColor(R.color.main_mauve));
                break;
            case 3:
                window.setStatusBarColor(getResources().getColor(R.color.main_navy));
                break;
        }
    }
}