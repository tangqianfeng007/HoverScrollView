package com.tqf.demo.hoverscrollview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author tangqianfeng
 */
public class MainActivity extends AppCompatActivity {

    public static final int PAGE_COUNT = 4;

    HoverScrollView hoverScrollView;
    LinearLayout linContain;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linContain = findViewById(R.id.linear);
        viewPager = findViewById(R.id.vp);
        tabLayout = findViewById(R.id.tablayout);
        hoverScrollView = findViewById(R.id.scrollview);
        initView();
    }

    private void initView() {
        for (int i = 0; i < 20; i++) {
            TextView item = new TextView(this);
            item.setText("item");
            item.setTextSize(18);
            item.setGravity(Gravity.CENTER);
            item.setPadding(12,12,12,12);
            linContain.addView(item, 0);
        }
        final HoverpageAdpter hoverpageAdpter = new HoverpageAdpter(getSupportFragmentManager());
        viewPager.setAdapter(hoverpageAdpter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                hoverScrollView.setController(hoverpageAdpter.getOverScrollController(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        hoverScrollView.setController(hoverpageAdpter.getOverScrollController(0));
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                int tabh = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP , 48 , getResources().getDisplayMetrics());
                int vh = hoverScrollView.getHeight() - tabh;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , vh);
                viewPager.setLayoutParams(params);
            }
        });

    }

    public static class HoverpageAdpter extends FragmentPagerAdapter {

        private SparseArray<ItemFragment> datas = new SparseArray<>();

        public HoverpageAdpter(FragmentManager fm) {
            super(fm);
            for (int i = 0; i < PAGE_COUNT; i++) {
                datas.append(i , ItemFragment.newInstance(80));
            }
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "TAB" + position;
        }

        public HoverScrollView.OverScrollController getOverScrollController (int pos){
            return datas.get(pos);
        }
    }

}
