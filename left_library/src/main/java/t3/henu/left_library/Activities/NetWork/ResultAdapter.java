package t3.henu.left_library.Activities.NetWork;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import t3.henu.left_library.Activities.SongInfo;
import t3.henu.left_library.MainActivity;
import t3.henu.left_library.R;
import t3.henu.left_library.YHQ_solve.OnlineMusic;
import t3.henu.left_library.YHQ_solve.clickBillboard.DownloadInfo;
import t3.henu.left_library.YHQ_solve.clickBillboard.OnlineMusicAdapter;
import t3.henu.left_library.YHQ_solve.http.HttpCallback;
import t3.henu.left_library.YHQ_solve.http.HttpClient;
import t3.henu.left_library.YHQ_solve.utils.FileUtils;
import t3.henu.left_library.YHQ_solve.utils.ImageUtils;

/**
 * Created by 高逸博 on 2017/5/31.
 */

class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private List<SongInfo>mSongList=new ArrayList<>();//就他
    boolean isplaying=false;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView online_cover,online_more;
        View online_divider;
        TextView online_title,online_artist;
        View musicView;
        public ViewHolder(View view) {
            super(view);
            online_cover= (ImageView) view.findViewById(R.id.yhq_online_cover);
            online_more= (ImageView) view.findViewById(R.id.yhq_online_more);
            online_divider=view.findViewById(R.id.yhq_online_divider);
            online_title= (TextView) view.findViewById(R.id.yhq_online_title);
            online_artist= (TextView) view.findViewById(R.id.yhq_online_artist);
            musicView=view;
        }
    }
    public ResultAdapter(List<SongInfo> mMusicList)
    {
        this.mSongList=mMusicList;
    }
    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.yhq_view_holder_music,parent,false);
        final ResultAdapter.ViewHolder holder=new ResultAdapter.ViewHolder(view);
        holder.musicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
               // SongInfo songInfo=mSongList.get(position);//点击事件好
                //Toast.makeText(v.getContext(),songInfo.getPucUrl(),Toast.LENGTH_LONG).show();
                //Toast.makeText(parent.getContext(),position+songInfo.getSong()+songInfo.getSinger(),Toast.LENGTH_SHORT).show();
                if(mSongList!=null&&mSongList.size()>0){
                    MainActivity.playBinder.setPlayList(mSongList);
                    MainActivity.playBinder.setCurrent(position);
                }else{
                    Toast.makeText(v.getContext(),"播放列表为空！！",Toast.LENGTH_LONG).show();
                }


            }
        });
        holder.online_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"点击了更多",Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ResultAdapter.ViewHolder holder, int position) {
        SongInfo onlineMusic=mSongList.get(position);
        //将从网络上获取的Sting 转化为图片
        ImageLoader.getInstance().displayImage(onlineMusic.getPucUrl(), holder.online_cover, ImageUtils.getCoverDisplayOptions());
        holder.online_title.setText(onlineMusic.getSong());
        String artist = FileUtils.getArtistAndAlbum(onlineMusic.getSinger(),onlineMusic.getAlbum());
        holder.online_artist.setText(artist);
        holder.online_divider.setVisibility(isShowDivider(position) ? View.VISIBLE : View.GONE);

    }
    private boolean isShowDivider(int position) {
        return position != mSongList.size() - 1;
    }

    @Override
    public int getItemCount() {
        return mSongList.size();
    }


}
