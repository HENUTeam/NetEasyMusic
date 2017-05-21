package t3.henu.left_library.Activities;

/**
 * Created by 高逸博 on 2017/4/14.
 */

public class SongInfo {
    private long Id,size,duration;
    private String song;
    private String path;
    private String singer;
    private String Lrc;
    private String pic_big;
    private String pic_small;

    public String getPic_big() {
        return pic_big;
    }

    public void setPic_big(String pic_big) {
        this.pic_big = pic_big;
    }

    public String getPic_small() {
        return pic_small;
    }

    public void setPic_small(String pic_small) {
        this.pic_small = pic_small;
    }
    public String getLrc() {
        return Lrc;
    }
    public void setLrc(String lrc) {
        Lrc = lrc;
    }

    public long getId() {
        return Id;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getSong() {
        if(song.contains(".mp3")){
            song=song.replace(".mp3","");
        }
        if(song.contains(".MP#")){
            song=song.replace(".MP3","");
        }

        return song;
    }
}
