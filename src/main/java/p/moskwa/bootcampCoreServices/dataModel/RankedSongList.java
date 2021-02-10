package p.moskwa.bootcampCoreServices.dataModel;

import java.util.ArrayList;
import java.util.List;

public class RankedSongList {
    int place;
    List<Song> songList;

    public RankedSongList(){
        songList = new ArrayList<>();
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
}
