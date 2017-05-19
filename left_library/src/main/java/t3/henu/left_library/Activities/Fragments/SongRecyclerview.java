package t3.henu.left_library.Activities.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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

import t3.henu.left_library.Activities.MusicUtils;
import t3.henu.left_library.Activities.SongInfo;
import t3.henu.left_library.MainActivity;
import t3.henu.left_library.R;

/**
 * Created by 高逸博 on 2017/4/14.
 */

public class SongRecyclerview extends Fragment {
    private static final int READ_SMS_REQUEST_CODE = 125;
    final private int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 122;
    final private int READ_EXTERNAL_STORAGE_REQUEST_CODE = 123;
    private View rootView;
    private RecyclerView recyclerView;
    private List<SongInfo> listsong = new ArrayList<SongInfo>();
    MediaPlayer player = null;
    boolean isPlay = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_music_info, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.id_recyclerview_songs);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_REQUEST_CODE);
        }
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_SMS,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE_REQUEST_CODE);
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                initView();
            }
        });

        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initView() {
        if (listsong.size() <= 0) {
            listsong = MusicUtils.getMp3Infos(getContext());
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SongRecyclerviewAdapter adapter = new SongRecyclerviewAdapter(listsong, getContext());
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                //toast(listsong.get(i).getSong() + ",路径：" + listsong.get(i).path);
                MainActivity.playBinder.setPlayList(listsong);
                MainActivity.playBinder.setCurrent(i);
            }
        });
        adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                int i1 = view.getId();
                if (i1 == R.id.id_songinfo_mv) {
                    toast("mv:" + i);

                } else if (i1 == R.id.id_songinfo_more) {
                    toast("更多:" + i);
                }

            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void playMusic(SongInfo songInfo, boolean cnt) {

    }

    private void toast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
