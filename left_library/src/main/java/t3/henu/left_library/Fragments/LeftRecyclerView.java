package t3.henu.left_library.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import t3.henu.left_library.Activities.*;
import t3.henu.left_library.R;

/**
 * Created by 高逸博 on 2017/4/13.
 */

public class LeftRecyclerView extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private List<RecyclerData> mDatas=new ArrayList<RecyclerData>();
    private String []texts;
    private String []numbers;
    private int images[]={R.drawable.icon_music_first,R.drawable.icon_bofang,R.drawable.icon_mydown,
                        R.drawable.icon_diantai,R.drawable.icon_shoucang};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_recyclerview,container,false);
        recyclerView= (RecyclerView) rootView.findViewById(R.id.id_recycler_Allmusic);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if(mDatas.size()<=0){
            texts=getResources().getStringArray(R.array.strs_music_liebiao);
            numbers=getResources().getStringArray(R.array.strs_music_number);
            for(int i=0;i<texts.length*2-1;i++){
                RecyclerData recyclerData;
                if(i%2==1){
                    recyclerData=new RecyclerData(images[0]);
                }
                else{
                    recyclerData=new RecyclerData(images[i/2],texts[i/2],numbers[i/2]);
                }
                mDatas.add(recyclerData);
            }
            int image=R.drawable.icon_next_right;
            String text[]={"创建的歌单","收藏的歌单"};
            String nu[]={"6","25"};
            for(int i=0;i<nu.length;i++){
                RecyclerData recyclerData=new RecyclerData(image,text[i],nu[i]);
                
                mDatas.add(recyclerData);
            }
        }
       RecyclerAdapter adapter=new RecyclerAdapter(mDatas);
        recyclerView.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                if(i==0){
                    startActivity(new Intent(getContext(), MyMusic.class));
                }

                toast(mDatas.get(i).getText());
            }
        });
        return rootView;
    }

    private void toast(String text) {
        Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
    }
}
