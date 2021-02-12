package p.moskwa.bootcampcoreservices.services;

import p.moskwa.bootcampcoreservices.datamodel.Song;
import p.moskwa.bootcampcoreservices.datamodel.SongDAO;
import p.moskwa.bootcampcoreservices.mappers.SongMapper;

import java.util.*;
import java.util.stream.Collectors;

import static p.moskwa.bootcampcoreservices.datamodel.Song.UID_SPLITTER;

/**
 * Song services
 *
 * @see Song
 * @since 1.0
 */
public class SongService {
    private final HashMap<String, HashMap<String, List<Song>>> songListHashMap;

    public SongService() {
        songListHashMap = new HashMap<>();
    }

    /**
     * Valid songs data as {@link HashMap} collection that contains
     * {@link p.moskwa.bootcampcoreservices.datamodel.Categories} name as key and
     * {@link HashMap} as values collection that contains
     * song Author as key and his songs as collection
     *
     * @return valid songs collection
     */
    public HashMap<String, HashMap<String, List<Song>>> getSortedSongList() {
        return songListHashMap;
    }

    /**
     * Force update song collection
     *
     * @param songDAOList valid SongDAOs collection to add to Song collection
     */
    public void updateSongList(List<SongDAO> songDAOList) {
        songDAOList.forEach(songDAO -> updateSongList(songDAO, true));
    }

    /**
     * Updates song collection
     *
     * @param songDAO     valid SongDAO object
     * @param forceUpdate true - if song exist sum votes
     * @return true if song added or votes update
     */
    public boolean updateSongList(SongDAO songDAO, boolean forceUpdate) {
        Song newSong = SongMapper.INSTANCE.songDAOToSong(songDAO);
        if (!songListHashMap.containsKey(newSong.getCategory().name())) {
            addNewCategoryToList(songListHashMap, newSong);
        } else {
            if (!songListHashMap.get(newSong.getCategory().name()).containsKey(newSong.getAuthor())) {
                addNewAuthorToList(songListHashMap.get(newSong.getCategory().name()), newSong);
            } else {
                if (forceUpdate)
                    addSongToList(songListHashMap.get(newSong.getCategory().name()).get(newSong.getAuthor()),
                            newSong);
                else
                    return false;
            }
        }
        return true;
    }

    /**
     * Finds song in song collection by song unique id
     *
     * @param songUid song unique id
     * @return song if found, null if not found
     */
    public Song getSongFromUid(String songUid) {
        String[] uid = songUid.split(UID_SPLITTER);
        return songListHashMap.get(uid[0]).get(uid[1])
                .stream()
                .filter(song -> song.getTitle().equals(uid[2]))
                .findFirst().orElse(null);
    }

    /**
     * Adds votes to song
     *
     * @param song  song to add vo count
     * @param votes votes to add
     */
    public void addVotesToSong(Song song, int votes) {
        song.setVotes(song.getVotes() + votes);
    }

    /**
     * Resets song votes
     *
     * @param song song to reset votes
     */
    public void resetSongVotes(Song song) {
        song.setVotes(0);
    }

    /**
     * Resets votes in all songs in collection
     */
    public void resetAllSongVotes() {
        songListHashMap.forEach((category, authors) -> authors
                .forEach((author, songs) -> songs.forEach(song ->
                        song.setVotes(0))
                ));
    }

    /**
     * Creates sorted by votes count songs as collections by given category
     *
     * @param category {@link p.moskwa.bootcampcoreservices.datamodel.Categories} as name, if null - all categories
     * @return songs as sorted collection from selected category
     */
    public List<Song> getSortedSongList(String category) {
        List<Song> listToSort = getSongListFromHashMap(category);

        return listToSort.stream()
                .sorted(Comparator.comparingInt(Song::getVotes).reversed())
                .collect(Collectors.toList());
    }

    private void addSongToList(List<Song> songs, Song newSong) {
        boolean isNewSong = true;

        for (Song song : songs) {
            if (song.getTitle().equals(newSong.getTitle())) {
                song.setVotes(song.getVotes() + newSong.getVotes());
                isNewSong = false;
                break;
            }
        }

        if (isNewSong)
            songs.add(newSong);
    }

    private List<Song> getSongListFromHashMap(String category) {
        List<Song> newSongList = new ArrayList<>();
        if (category != null) {
            if (songListHashMap.containsKey(category))
                songListHashMap.get(category)
                        .forEach((author, list) -> newSongList.addAll(list));

        } else
            songListHashMap.forEach((cat, authors) -> authors
                    .forEach((author, list) -> newSongList.addAll(list)));

        return newSongList;
    }

    private void addNewCategoryToList(HashMap<String, HashMap<String, List<Song>>> songListHashMap, Song newSong) {
        HashMap<String, List<Song>> authorSongs = new HashMap<>();
        songListHashMap.put(newSong.getCategory().name(),
                addNewAuthorToList(authorSongs, newSong));
    }

    private HashMap<String, List<Song>> addNewAuthorToList(HashMap<String, List<Song>> authorSongs, Song newSong) {
        List<Song> authorSongList = new ArrayList<>();
        authorSongList.add(newSong);
        authorSongs.put(newSong.getAuthor(), authorSongList);
        return authorSongs;
    }
}
