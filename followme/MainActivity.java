package com.xiaoshan.polly.followme;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xiaoshan.polly.followme.fragment.FragmentAnother;
import com.xiaoshan.polly.followme.fragment.FragmentTest;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager vpTest;
    private TabLayout tabLayout;
    private String[] titles = {"山哥", "自然", "最帅","无语"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vpTest = ((ViewPager) findViewById(R.id.vp_test));
        toolbar.setNavigationIcon(R.drawable.ic_android);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeButtonEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "会怎样", Toast.LENGTH_SHORT).show();
                            }
                        }).setActionTextColor(Color.YELLOW).show();


            }
        });

        tabLayout = ((TabLayout) findViewById(R.id.tab_test));
        tabLayout.setBackgroundColor(Color.GREEN);
        tabLayout.setTabTextColors(Color.BLACK, Color.WHITE);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("MainActivity", "我很菜啊");
                Toast.makeText(MainActivity.this, "我很菜啊", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        TabLayout.Tab tab1 = tabLayout.newTab();
//        tab1.setText("山哥");
//        TabLayout.Tab tab2 = tabLayout.newTab();
//        tab2.setText("自然");
//        TabLayout.Tab tab3 = tabLayout.newTab();
//        tab3.setText("最帅");
//        tabLayout.addTab(tab1);
//        tabLayout.addTab(tab2);
//        tabLayout.addTab(tab3);

        vpTest.setAdapter(new MyVpAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(vpTest);

    }

    private class MyVpAdapter extends FragmentPagerAdapter {

        public MyVpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentTest();
                case 1:
                    return FragmentTest.newInstance("image", null);
                case 2:
                    return new FragmentAnother();
            }
            return new FragmentTest();
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_search) {
            Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
