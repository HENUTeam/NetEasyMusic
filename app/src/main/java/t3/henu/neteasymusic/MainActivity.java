package t3.henu.neteasymusic;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import t3.henu.neteasymusic.appMain.MyViewPagerAdapter;
import t3.henu.neteasymusic.appMain.TabLayout_Mid;
import t3.henu.neteasymusic.appMain_left.LogInactivity;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments=new ArrayList<Fragment>();
    private ConstraintLayout constraintLayout_left;
    private DrawerLayout drawerLayout;
    private ListView appmain_drawerlayout_left_listview;
    private  String []menuNames;
    private int []menuicons;
    private List<Map<String,Object> > appmain_drawerlayout_left_listview_Data=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawerlayout);
        initView();
    }

    private void initView() {

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                menuicons=new int[]{R.drawable.icon_appmain_left_menu_01,R.drawable.icon_appmain_left_menu_02,
                        R.drawable.icon_appmain_left_menu_03,R.drawable.icon_appmain_left_menu_02
                        ,R.drawable.icon_appmain_left_menu_02,R.drawable.icon_appmain_left_menu_02,
                        R.drawable.icon_appmain_left_menu_02,R.drawable.icon_appmain_left_menu_02
                        ,R.drawable.icon_appmain_left_menu_02,R.drawable.icon_appmain_left_menu_02,
                        R.drawable.icon_appmain_left_menu_02,R.drawable.icon_appmain_left_menu_02
                        ,R.drawable.icon_appmain_left_menu_02,R.drawable.icon_appmain_left_menu_02};
                initToolbar();initViewPager();
                initDrawerlayout();
                drawerLayout= (DrawerLayout) findViewById(R.id.id_appmain_drawelayout);
            }
        }) ;
    }

    private void initDrawerlayout() {
        constraintLayout_left=(ConstraintLayout)findViewById(R.id.id_appmain_drawelayout_left);
        ImageButton btn= (ImageButton) findViewById(R.id.id_appmain_btn_showleft);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!drawerLayout.isDrawerOpen(constraintLayout_left)){
                    drawerLayout.openDrawer(constraintLayout_left);
                }
            }
        });
        Button btn_signin= (Button) findViewById(R.id.id_appmain_drawelayout_left_btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LogInactivity.class);
                startActivity(intent);
            }
        });
        initDrawerlayout_left_RecyclerView();
    }

    private void initDrawerlayout_left_RecyclerView() {
        appmain_drawerlayout_left_listview= (ListView) findViewById(R.id.id_appmain_drawelayout_left_recyclerview1);
        menuNames=getResources().getStringArray(R.array.appmain_drawerlayout_left_menu_itemNames);
       appmain_drawerlayout_left_listview_Data=new ArrayList<Map<String,Object>>();
        if(appmain_drawerlayout_left_listview_Data!=null){
            for(int i=0;i<menuNames.length;i++){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("image", menuicons[i]);               //图片资源
                map.put("text", menuNames[i]); //物品详情
                appmain_drawerlayout_left_listview_Data.add(map);
            }
        }
        SimpleAdapter simpAdapter=new SimpleAdapter(this,appmain_drawerlayout_left_listview_Data,R.layout.appmain_drawerlayout_left_recycleview_itemview,
                new String[]{"image","text"}, new int[]{R.id.id_appmain_drawelayout_left_recyclerview1_item_imageview
                ,R.id.id_appmain_drawelayout_left_recyclerview1_item_textview});
       appmain_drawerlayout_left_listview.setAdapter(simpAdapter);
    }

    private void initToolbar() {
        tabLayout= (TabLayout) findViewById(R.id.id_appmain_toolbar_tabLayout);
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
        viewPager= (ViewPager) findViewById(R.id.id_appmain_viewpager);
        if(fragments.size()<=0){
            fragments.add(new BlankFragment("第一个"));
            fragments.add(new TabLayout_Mid());
            fragments.add(new BlankFragment("第三个"));
        }
        MyViewPagerAdapter myAdapter=new MyViewPagerAdapter(getSupportFragmentManager(),fragments);
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
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }



}
