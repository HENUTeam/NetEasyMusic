package t3.henu.neteasymusic;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import t3.henu.neteasymusic.appMain.MyViewPagerAdapter;
import t3.henu.neteasymusic.appMain.TabLayout_Mid;
import t3.henu.neteasymusic.appMain_left.LogInactivity;
import t3.henu.neteasymusic.appMain_left.ReViewAdaoter;
import t3.henu.neteasymusic.appMain_left.RecyclerData;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private ConstraintLayout constraintLayout_left;
    private DrawerLayout drawerLayout;
    private RecyclerView appmain_drawerlayout_left_recyclerView;
    private String[] menuNames;
    private int[] menuicons = new int[]{R.drawable.icon_appmain_left_menu_01, R.drawable.icon_appmain_left_menu_02,
            R.drawable.icon_appmain_left_menu_03, R.drawable.icon_appmain_left_menu_02
            , R.drawable.icon_appmain_left_menu_02, R.drawable.icon_appmain_left_menu_02,
            R.drawable.icon_appmain_left_menu_02, R.drawable.icon_appmain_left_menu_02
            , R.drawable.icon_appmain_left_menu_02, R.drawable.icon_appmain_left_menu_02,
            R.drawable.icon_appmain_left_menu_02, R.drawable.icon_appmain_left_menu_02
            , R.drawable.icon_appmain_left_menu_02, R.drawable.icon_appmain_left_menu_02
            , R.drawable.icon_appmain_left_menu_02, R.drawable.icon_appmain_left_menu_02};
    Fragment fragment_appmain_left = null;
    private List<RecyclerData> RecView_Datas = new LinkedList<RecyclerData>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawerlayout);
        initView();
    }

    private void initView() {
        initToolbar();
        initViewPager();
        initDrawerlayout();
        drawerLayout = (DrawerLayout) findViewById(R.id.id_appmain_drawelayout);
    }

    private void initDrawerlayout() {
        constraintLayout_left = (ConstraintLayout) findViewById(R.id.id_appmain_drawelayout_left);
        ImageButton btn = (ImageButton) findViewById(R.id.id_appmain_btn_showleft);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawerLayout.isDrawerOpen(constraintLayout_left)) {
                    drawerLayout.openDrawer(constraintLayout_left);
                }
            }
        });
        Button btn_signin = (Button) findViewById(R.id.id_appmain_drawelayout_left_btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogInactivity.class);
                startActivity(intent);
            }
        });
        initDrawerlayout_left_RecyclerView();
    }

    private void initDrawerlayout_left_RecyclerView() {

        menuNames = getResources().getStringArray(R.array.appmain_drawerlayout_left_menu_itemNames);
        if (RecView_Datas.size() <= 0) {
            for (int i = 0; i < menuNames.length; i++) {
                RecyclerData rd = new RecyclerData(menuicons[i], menuNames[i]);
                RecView_Datas.add(rd);
            }
        }
        ReViewAdaoter adaoter = new ReViewAdaoter(this, RecView_Datas);
        appmain_drawerlayout_left_recyclerView = (RecyclerView) findViewById(R.id.id_appmain_drawelayout_left_recyclerview1)
        appmain_drawerlayout_left_recyclerView.setAdapter(adaoter);
    }

    private void initToolbar() {
        tabLayout = (TabLayout) findViewById(R.id.id_appmain_toolbar_tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.id_appmain_viewpager);
        if (fragments.size() <= 0) {
            if (fragment_appmain_left != null) {
                fragment_appmain_left = new BlankFragment("第三个");
            }
            fragments.add(new BlankFragment("第一个"));
            fragments.add(new TabLayout_Mid());
            fragments.add(new BlankFragment("第三个"));
        }
        MyViewPagerAdapter myAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}
