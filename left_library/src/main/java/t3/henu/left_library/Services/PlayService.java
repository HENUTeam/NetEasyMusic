package t3.henu.left_library.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;

import android.os.IBinder;

import java.util.List;
import java.util.Random;

import t3.henu.left_library.Activities.MusicUtils;
import t3.henu.left_library.Activities.SongInfo;

public class PlayService extends Service {
    public static MediaPlayer mediaPlayer;
    public static List<SongInfo>play_list;//当前播放的列表
    public static int status=1;//1代表顺序循环，2代表随机循环，3代表单曲循环
    public int current=0;//当前播放的歌曲序号
    public boolean isplay=false;//是否播放
    public long hasTime=0;//已经播放的歌曲时间
    public long allTime;//歌曲总时间

    private playBinder binder=new playBinder();
    public class playBinder extends Binder{
        public void setPlayStatus(int s){
            status=s;
        }
        public void setPlayList(List<SongInfo> list){
            play_list=list;
        }
        public void setIsPlay(boolean b){
            isplay=b;

        }
    }

    @Override
    public void onCreate() {
        mediaPlayer=new MediaPlayer();
        play_list=MusicUtils.getMp3Infos(getBaseContext());
        current=0;
        hasTime=0;
        allTime=play_list.get(current).getDuration();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(status==3){
                    mediaPlayer.start();
                }else if(status==2){
                    current=(current+1)%play_list.size();
                }else if(status==3){
                    current=(int)new Random().nextInt(play_list.size());
                }
            }
        });
    }

    public PlayService() {
    }


    /**
     * 播放音乐
     *
     * @param
     */
    private void play(int currentTime) {
        try {
            mediaPlayer.reset();// 把各项参数恢复到初始状态
            mediaPlayer.setDataSource(play_list.get(current).path);
            mediaPlayer.prepare(); // 进行缓冲
            mediaPlayer.setOnPreparedListener(new PreparedListener(currentTime));// 注册一个监听器

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停音乐
     */
    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isplay = true;
        }
    }

    private void resume() {
        if (isplay) {
            mediaPlayer.start();
            isplay = false;
        }
    }

    /**
     * 上一首
     */
    private void previous() {

        play(0);
    }

    /**
     * 下一首
     */
    private void next() {

        play(0);
    }

    /**
     * 停止音乐
     */
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare(); // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private final class PreparedListener implements MediaPlayer.OnPreparedListener {
        private int currentTime;

        public PreparedListener(int currentTime) {
            this.currentTime = currentTime;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.start(); // 开始播放
            if (currentTime > 0) { // 如果音乐不是从头播放
                mediaPlayer.seekTo(currentTime);
            }

        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
