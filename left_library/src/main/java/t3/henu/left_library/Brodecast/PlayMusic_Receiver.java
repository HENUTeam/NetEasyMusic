package t3.henu.left_library.Brodecast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import t3.henu.left_library.Activities.NetWork.MusicNetWork;
import t3.henu.left_library.Activities.SongInfo;
import t3.henu.left_library.All_View;
import t3.henu.left_library.Collect;
import t3.henu.left_library.MainActivity;
import t3.henu.left_library.R;
import t3.henu.left_library.YHQ_solve.utils.ImageUtils;

public class PlayMusic_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        Bundle bun=intent.getBundleExtra("Bunde");
        SongInfo songInfo= (SongInfo) bun.getParcelable("songinfo");
        RequestQueue mQueue = MusicNetWork.getmRequestqueue(context);
        List<All_View> list= Collect.all_view_list;
        for(int i=0;i<list.size();i++){
            final All_View all_view=list.get(i);
            if(all_view.getSinger()!=null){
                all_view.getSinger().setText(songInfo.getSinger());
                all_view.getSong().setText(songInfo.getSong());
                boolean status= (boolean) intent.getExtras().get("play_status");
                //Toast.makeText(context,"收到广播："+(status),Toast.LENGTH_SHORT).show();
                if(status==false){
                    all_view.getImageButton().setImageResource(t3.henu.left_library.R.drawable.icon_play1);
                }else{
                    all_view.getImageButton().setImageResource(t3.henu.left_library.R.drawable.icon_pause);
                }

                //Toast.makeText(context,(t3.henu.left_library.MainActivity.imageView==null)+"::"+songInfo.getPucUrl(),Toast.LENGTH_SHORT).show();
                ImageRequest imageRequest=new ImageRequest(songInfo.getPucUrl(), new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        if(  all_view.getImageView()!=null){
                            all_view.getImageView().setImageBitmap(bitmap);
                        }
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        all_view.getImageView().setImageResource(R.drawable.huge);

                    }
                });mQueue.add(imageRequest);
            }
        }



    }
}
