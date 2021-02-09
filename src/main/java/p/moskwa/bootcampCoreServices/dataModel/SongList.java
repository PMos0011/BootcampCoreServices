package p.moskwa.bootcampCoreServices.dataModel;

import java.util.HashMap;
import java.util.List;

public class SongList {
    private final HashMap<String, HashMap<String, List<Song>>> songListHashMap;

    public SongList() {
        songListHashMap = new HashMap<>();
    }

    public HashMap<String, HashMap<String, List<Song>>> getSongListHashMap() {
        return songListHashMap;
    }
}