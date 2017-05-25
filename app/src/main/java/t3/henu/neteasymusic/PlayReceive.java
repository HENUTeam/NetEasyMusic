package t3.henu.neteasymusic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import t3.henu.left_library.Activities.SongInfo;

public class PlayReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Bundle bun=intent.getBundleExtra("Bunde");
        SongInfo songInfo= (SongInfo) bun.getSerializable("songinfo");
       MainActivity.text_singer.setText(songInfo.getSinger());
        MainActivity.text_song.setText(songInfo.getSong());
        boolean status= (boolean) intent.getExtras().get("play_status");
        if(status==false){
           MainActivity.btn_play.setImageResource(t3.henu.left_library.R.drawable.icon_play1);
        }else{
           MainActivity.btn_play.setImageResource(t3.henu.left_library.R.drawable.icon_pause);
        }
    }
}
