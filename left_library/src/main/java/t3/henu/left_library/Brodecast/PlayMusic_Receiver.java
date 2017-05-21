package t3.henu.left_library.Brodecast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import t3.henu.left_library.Activities.SongInfo;
import t3.henu.left_library.MainActivity;
import t3.henu.left_library.R;

public class PlayMusic_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bun=intent.getBundleExtra("Bunde");
        SongInfo songInfo= (SongInfo) bun.getSerializable("songinfo");
        if(MainActivity.t_singer!=null){
            MainActivity.t_singer.setText(songInfo.getSinger());
            MainActivity.t_songname.setText(songInfo.getSong());
            //MainActivity.imageView.setImageBitmap(songInfo.getAlbumUrl());
        }
        boolean status= (boolean) intent.getExtras().get("play_status");
        if( MainActivity.btn_play!=null){
            if(status==false){
                MainActivity.btn_play.setImageResource(R.drawable.icon_play1);
            }else{
                MainActivity.btn_play.setImageResource(R.drawable.icon_pause);
            }
        }

        //Toast.makeText(context,"收到广播："+(status),Toast.LENGTH_SHORT).show();
    }
}
