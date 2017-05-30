package t3.henu.neteasymusic.appMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import t3.henu.left_library.MainActivity;
import t3.henu.neteasymusic.BlankFragment;
import t3.henu.neteasymusic.R;

/**
 * Created by 高逸博 on 2017/4/1.///
 */

public class TabLayout_Mid extends Fragment {

    private View rootView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments=new ArrayList<Fragment>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.activity_main_appmain_tablayout_mid,container,false);
        initTabLayout();
        initViewPager();
        return rootView;
    }

    private void initTabLayout() {
        tabLayout= (TabLayout) rootView.findViewById(R.id.id_appmain_toolbar_tabLayout_mid_tablayout);
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
        viewPager= (ViewPager) rootView.findViewById(R.id.id_appmain_toolbar_tabLayout_mid_viewPager);
        if(fragments.size()<=0){
            fragments.add(new BlankFragment("第一个"));
            fragments.add(new BlankFragment("第二个"));
            fragments.add(new BlankFragment("第三个"));
            fragments.add(new t3.henu.left_library.YHQ_solve.BillboardFragment());////替换掉第四个fragment。。。。ok？ 写到left？还是app？left
            //new t3.henu.left_library.你的fragment路径   你加的都在那个包里把 ok

        }
        MyViewPagerAdapter myAdapter=new MyViewPagerAdapter(getActivity().getSupportFragmentManager(),fragments);
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
}

