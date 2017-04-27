package t3.henu.left_library.Brodecast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static t3.henu.left_library.MainActivity.play_status;

public class PlayMusic_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        play_status status= (play_status) intent.getExtras().get("play_status");
        switch (status){
            case IS_PLAY:
                
                break;
        }
    }
}
