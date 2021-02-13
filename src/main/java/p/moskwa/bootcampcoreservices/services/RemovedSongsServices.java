package p.moskwa.bootcampcoreservices.services;

import p.moskwa.bootcampcoreservices.datamodel.SongDAO;

import java.util.HashMap;
import java.util.List;

/**
 * Removed song services
 *
 * @since 1.0
 */
public class RemovedSongsServices {

    private HashMap<String, List<SongDAO>> removedSongs;

    public RemovedSongsServices() {
        removedSongs = new HashMap<>();
    }

    /**
     * Updates removed songDAOs collection
     *
     * @param removedSongsList removed songsDAOs collection
     * @param fileName         source file name
     */
    public void updateRemovedSongsList(List<SongDAO> removedSongsList, String fileName) {
        if (removedSongsList.size() > 0) {
            removedSongs.put(fileName, removedSongsList);
        }
    }

    /**
     * Creates new empty removed songs collection
     */
    public void clearList() {
        removedSongs = new HashMap<>();
    }

    /**
     * Checks removed songs collection size
     *
     * @return false if collection is empty
     */
    public boolean isErrorToDisplay() {
        return removedSongs.size() > 0;
    }

    /**
     * Returns removed songsDAO collection
     */
    public HashMap<String, List<SongDAO>> getRemovedSongs() {
        return removedSongs;
    }
}
