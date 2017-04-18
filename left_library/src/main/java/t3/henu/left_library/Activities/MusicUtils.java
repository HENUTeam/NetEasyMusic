package t3.henu.left_library.Activities;

import android.content.*;
import android.database.*;
import android.provider.*;

import java.util.*;

/**
 * Created by 高逸博 on 2017/4/14.
 */

public class MusicUtils {

    public static List<SongInfo> getMusicData(Context context){
        List <SongInfo>songs=new ArrayList<SongInfo>();

        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        if(cursor!=null){
            while (cursor.moveToNext()){
                SongInfo song=new SongInfo();
                song.setSongName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))) ;
                song.setSinger(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                songs.add(song);
            }
            cursor.close();
        }
        return songs;
    }

}
