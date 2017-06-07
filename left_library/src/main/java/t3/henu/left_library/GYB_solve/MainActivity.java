package t3.henu.left_library.GYB_solve;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import t3.henu.left_library.GYB_solve.Services.PlayService;
import t3.henu.left_library.R;


public class MainActivity extends AppCompatActivity {
    public static ImageButton btn_play,btn_menu;
    public static ImageView imageView;
    public static TextView t_songname,t_singer;
    public static PlayService.playBinder playBinder=null;
    public static ServiceConnection con=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            playBinder= (PlayService.playBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    protected Context mContext;
    private FrameLayout mContentContainer;
    private View mFloatView;
    private PopWindowMenu popWindowMenu;
    private RelativeLayout play_layout;
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mContext = this;
        ViewGroup mDecorView = (ViewGroup) getWindow().getDecorView();
        mContentContainer = (FrameLayout) ((ViewGroup) mDecorView.getChildAt(0)).getChildAt(1);
        mFloatView = LayoutInflater.from(getBaseContext()).inflate(R.layout.gyb_flowplaymusic, null);
        btn_menu= (ImageButton) mFloatView.findViewById(R.id.id_btn_liebiao);
        btn_play = (ImageButton) mFloatView.findViewById(R.id.id_flow_play);
        t_singer= (TextView) mFloatView.findViewById(R.id.id_textview_songsinger);
        t_songname=(TextView) mFloatView.findViewById(R.id.id_textview_songName);
        imageView= (ImageView) mFloatView.findViewById(R.id.id_imageview_album);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(),"播放",Toast.LENGTH_LONG).show();
                MainActivity.playBinder.setIsPlay();
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindowMenu=new PopWindowMenu(MainActivity.this,itemsOnClick);
                popWindowMenu.showAtLocation(MainActivity.this.findViewById(R.id.id_base_play),
                        Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        play_layout = (RelativeLayout)mFloatView.findViewById(R.id.id_base_play);
        play_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlayActivity.class));
            }
        });
    }

    private void toast(String s) {
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity= Gravity.BOTTOM;
        mContentContainer.addView(mFloatView, layoutParams);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     *
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        // intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        super.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(0, 0);
    }
}
