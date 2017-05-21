package t3.henu.left_library.Activities;

import java.io.Serializable;

/**
 * Created by 高逸博 on 2017/4/14.
 */

public class SongInfo implements Serializable {
    private long Id,size,duration;
    private String song,path,singer;

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
        //int i=song.lastIndexOf('.');
        //String song1="";
        if(song.contains(".mp3")){
            song=song.replace(".mp3","");
        }
        if(song.contains(".MP#")){
            song=song.replace(".MP3","");
        }

        return song;
    }
}
