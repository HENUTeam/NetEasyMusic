package t3.henu.left_library.Activities.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;


import t3.henu.left_library.*;
import t3.henu.left_library.Activities.MusicUtils;

/**
 * Created by 高逸博 on 2017/4/14.
 */

public class SongRecyclerview extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_music_info,container,false);
        recyclerView= (RecyclerView) rootView.findViewById(R.id.id_recyclerview_songs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SongRecyclerviewAdapter adapter=new SongRecyclerviewAdapter(MusicUtils.getMusicData(getContext()));
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
