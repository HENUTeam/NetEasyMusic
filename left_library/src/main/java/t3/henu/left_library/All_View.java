package t3.henu.left_library;

import android.widget.ImageView;
import android.widget.TextView;

public class All_View{
    private TextView singer,song;
    private ImageView imageView;

    public TextView getSinger() {
        return singer;
    }

    public void setSinger(TextView singer) {
        this.singer = singer;
    }

    public TextView getSong() {
        return song;
    }

    public void setSong(TextView song) {
        this.song = song;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public All_View(TextView singer, TextView song, ImageView imageView) {
        this.singer = singer;
        this.song = song;
        this.imageView = imageView;
    }
}
