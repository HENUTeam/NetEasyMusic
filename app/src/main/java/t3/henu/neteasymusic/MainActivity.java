package t3.henu.neteasymusic;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import t3.henu.left_library.Fragments.LeftRecyclerView;
import t3.henu.neteasymusic.appMain.MyViewPagerAdapter;
import t3.henu.neteasymusic.appMain.TabLayout_Mid;
import t3.henu.neteasymusic.appMain_drawerlayout_start.LogInactivity;
import t3.henu.neteasymusic.appMain_drawerlayout_start.RecyclerData;
import t3.henu.neteasymusic.appMain_drawerlayout_start.RecyclerViewAdapter;

public class MainActivity extends t3.henu.left_library.MainActivity  {
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    final private int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 122;
    final private int READ_SMS_REQUES_CODE = 122;
    final private int READ_EXTERNAL_STORAGE_REQUEST_CODE = 123;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private ConstraintLayout constraintLayout_left;
    private DrawerLayout drawerLayout;
    private RecyclerView appmain_drawerlayout_left_recyclerView;
    private String[] menuNames;
    private int[] menuicons = new int[]{R.drawable.icon_appmain_left_menu_01,
            R.drawable.icon_appmain_left_menu_01, R.drawable.icon_appmain_left_menu_02,
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
        solvePermisson();
        drawerLayout = (DrawerLayout) findViewById(R.id.id_appmain_drawelayout);
    }

    private void solvePermisson() {
        List<String> permissionsNeeded = new ArrayList<String>();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_REQUES_CODE);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE_REQUEST_CODE);
        }

        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
            permissionsNeeded.add("GPS");
        if (!addPermission(permissionsList, Manifest.permission.READ_CONTACTS))
            permissionsNeeded.add("Read Contacts");
        if (!addPermission(permissionsList, Manifest.permission.WRITE_CONTACTS))
            permissionsNeeded.add("Write Contacts");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);

                return;
            }
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return;
        }
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_CONTACTS, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_CONTACTS, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for ACCESS_FINE_LOCATION
                if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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
        final RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.id_base_play);

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

        appmain_drawerlayout_left_recyclerView = (RecyclerView)
                findViewById(R.id.id_appmain_drawelayout_left_recyclerview1);
        appmain_drawerlayout_left_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adaoter = new RecyclerViewAdapter(RecView_Datas);

        adaoter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (view.getId() == R.id.id_appmain_drawelayout_left_btn_signin) {
                    startActivity(new Intent(MainActivity.this, LogInactivity.class));
                }
            }
        });
        adaoter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                startActivity(new Intent(MainActivity.this, LogInactivity.class));
            }
        });
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
            fragments.add(new LeftRecyclerView());
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
