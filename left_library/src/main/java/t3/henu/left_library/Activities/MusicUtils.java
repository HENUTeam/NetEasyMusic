package t3.henu.left_library.Activities;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 高逸博 on 2017/4/14.
 */

public class MusicUtils {

    public static List<SongInfo> getMp3Infos(Context context) {

        List<SongInfo> list = new LinkedList<SongInfo>();
        // 媒体库查询语句（写一个工具类MusicUtils）

        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        getCursor(cursor,list,context);
        Cursor cursor2=context.getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
                null,null,null,
                MediaStore.Audio.AudioColumns.IS_MUSIC);
        getCursor(cursor2,list,context);
        //Toast.makeText(context,"list共有："+list.size()+"第35"+list.get(38).getSong(),Toast.LENGTH_LONG).show();
        return list;
    }

    private static void getCursor(Cursor cursor, List<SongInfo> list, Context context) {

        if (cursor != null&&cursor.moveToFirst()) {

            do {
                SongInfo mp3 = new SongInfo();
                mp3.song = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                mp3.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                mp3.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                mp3.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                mp3.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                if (mp3.size > 1000 * 800) {
                    // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                    if (mp3.song.contains("-")) {
                        String[] str = mp3.song.split("-");
                        mp3.singer = str[0];
                        mp3.song = str[1];
                    }
                    list.add(mp3);
                }
            }while (cursor.moveToNext());
            // 释放资源
            cursor.close();
        }
    }

    /**
     * 定义一个方法用来格式化获取到的时间
     */
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            return time / 1000 / 60 + ":0" + time / 1000 % 60;

        } else {
            return time / 1000 / 60 + ":" + time / 1000 % 60;
        }

    }

}
