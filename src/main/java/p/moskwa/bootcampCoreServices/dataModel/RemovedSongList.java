package p.moskwa.bootcampCoreServices.dataModel;

import java.util.HashMap;
import java.util.List;

public class RemovedSongList {
    private HashMap<String, List<SongDAO>> removedSongs;

    public HashMap<String, List<SongDAO>> getRemovedSongs() {
        return removedSongs;
    }

    public void setRemovedSongs(HashMap<String, List<SongDAO>> removedSongs) {
        this.removedSongs = removedSongs;
    }
}