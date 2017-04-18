package t3.henu.left_library.Activities.Fragments;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import t3.henu.left_library.Activities.*;
import t3.henu.left_library.R;

/**
 * Created by 高逸博 on 2017/4/14.
 */

public class SongRecyclerviewAdapter extends BaseQuickAdapter<SongInfo> {

    public SongRecyclerviewAdapter(List<SongInfo> data) {
        super(R.layout.music_song_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SongInfo songInfo) {
        baseViewHolder.setText(R.id.id_songinfo_author,songInfo.getSinger());
        baseViewHolder.setText(R.id.id_songinfo_name,songInfo.getSongName());
    }
}
