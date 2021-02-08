package p.moskwa.bootcampCoreServices.dataModel;

import java.util.HashMap;
import java.util.List;

public class RemovedElements {
    private final HashMap<String, List<SongDAO>> removedSongs;

    public RemovedElements() {
        removedSongs = new HashMap<>();
    }

    public HashMap<String, List<SongDAO>> getRemovedSongs() {
        return removedSongs;
    }
}