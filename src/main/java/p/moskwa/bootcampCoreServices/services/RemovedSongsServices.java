package p.moskwa.bootcampCoreServices.services;

import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

import java.util.HashMap;
import java.util.List;

public class RemovedSongsServices {

    private HashMap<String, List<SongDAO>> removedSongs;

    public void updateRemovedSongsList(List<SongDAO> removedSongsList, String fileName) {
        if (removedSongsList.size() > 0)
            removedSongs.put(fileName, removedSongsList);
    }

    public void clearList() {
        removedSongs = new HashMap<>();
    }

    public boolean isErrorToDisplay() {
        return removedSongs.size() > 0;
    }

    public HashMap<String, List<SongDAO>> getRemovedSongs() {
        return removedSongs;
    }
}