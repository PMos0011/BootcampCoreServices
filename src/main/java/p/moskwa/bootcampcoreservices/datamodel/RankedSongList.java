package p.moskwa.bootcampcoreservices.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent songs ranking as collection
 *
 * @since 1.0
 */
public class RankedSongList {
    private int place;
    private final List<Song> songList;

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
     * @param place ranking position as int
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
