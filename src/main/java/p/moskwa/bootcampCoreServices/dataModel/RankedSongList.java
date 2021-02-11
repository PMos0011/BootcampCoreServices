package p.moskwa.bootcampCoreServices.dataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent songs ranking as collection
 */
public class RankedSongList {
    int place;
    List<Song> songList;

    /**
     * Creates a rankedSongList object with empty song collection
     */
    public RankedSongList() {
        songList = new ArrayList<>();
    }

    /**
     * @return ranking position of songs collection as int
     */
    public int getPlace() {
        return place;
    }

    /**
     * Sets ranking position of songs collection
     *
     * @param place
     */
    public void setPlace(int place) {
        this.place = place;
    }

    /**
     * @return songs as collection
     */
    public List<Song> getSongList() {
        return songList;
    }
}