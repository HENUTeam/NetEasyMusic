package t3.henu.left_library;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import t3.henu.left_library.Services.PlayService;

/**
 * Created by 高逸博 on 2017/6/2.
 */

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    public static  ImageButton playingPre,playingPlay,playingNext;
    public ImageView disc,needle;
    public static TextView text_song_name,text_song_singer;
    private ObjectAnimator discAnimation,needleAnimation;
    private boolean isPlaying = true;
    private ImageButton imageButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initViews();
    }


    private void makeStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void initViews() {
        makeStatusBarTransparent();
        playingPre = (ImageButton) findViewById(R.id.playing_pre);
        playingPlay = (ImageButton) findViewById(R.id.playing_play);
        playingNext = (ImageButton) findViewById(R.id.playing_next);

        text_song_name= (TextView) findViewById(R.id.text_play_song);
        text_song_singer=(TextView)findViewById(R.id.text_play_singer);
        playingPre.setOnClickListener(this);
        playingPlay.setOnClickListener(this);
        playingNext.setOnClickListener(this);
        imageButton= (ImageButton) findViewById(R.id.id_play_back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //动画设置
    private void setAnimations() {

        discAnimation = ObjectAnimator.ofFloat(disc, "rotation", 0, 360);
        discAnimation.setDuration(20000);
        discAnimation.setInterpolator(new LinearInterpolator());
        discAnimation.setRepeatCount(ValueAnimator.INFINITE);

        needleAnimation = ObjectAnimator.ofFloat(needle, "rotation", 0, 25);
        needle.setPivotX(0);
        needle.setPivotY(0);
        needleAnimation.setDuration(800);
        needleAnimation.setInterpolator(new LinearInterpolator());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.playing_pre) {
            MainActivity.playBinder.pre();
            //播放中
        } else if (id == R.id.playing_play) {
            MainActivity.playBinder.setIsPlay();
            //下一曲
        } else if (id == R.id.playing_next) {
            MainActivity.playBinder.next();

        } else {
        }
    }


}
