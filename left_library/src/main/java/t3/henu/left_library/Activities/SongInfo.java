package t3.henu.left_library.Activities;

/**
 * Created by 高逸博 on 2017/4/14.
 */

public class SongInfo {
    public long Id,size,duration;
    public String song,path,singer;

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

    public long getId() {
        return Id;
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
        int i=song.lastIndexOf('.');
        String song1="";
        if(i<song.length()){
             song1=song.substring(0,song.length()-4);
        }

        return song1;
    }
}
