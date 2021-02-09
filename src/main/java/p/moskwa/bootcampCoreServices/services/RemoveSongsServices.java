package p.moskwa.bootcampCoreServices.services;

import p.moskwa.bootcampCoreServices.dataModel.RemovedSongList;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

import java.util.HashMap;
import java.util.List;

public class RemoveSongsServices {

    private final RemovedSongList removedSongList;

    public RemoveSongsServices() {
        removedSongList = new RemovedSongList();
    }

    public void updateRemovedSongsList(List<SongDAO> removedSongsList, String fileName) {
        if (removedSongsList.size() > 0)
            removedSongList.getRemovedSongs().put(fileName, removedSongsList);
    }

    public void clearList() {
        removedSongList.setRemovedSongs(new HashMap<>());
    }

    public boolean isErrorToDisplay() {
        return removedSongList.getRemovedSongs().size() > 0;
    }

    public HashMap<String, List<SongDAO>> getRemovedSongs(){
        return removedSongList.getRemovedSongs();
    }
}