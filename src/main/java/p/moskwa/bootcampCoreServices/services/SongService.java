package p.moskwa.bootcampCoreServices.services;

import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;
import p.moskwa.bootcampCoreServices.mappers.SongMapper;

import java.util.*;
import java.util.stream.Collectors;

import static p.moskwa.bootcampCoreServices.dataModel.Song.UID_SPLITTER;

public class SongService {
    private final HashMap<String, HashMap<String, List<Song>>> songListHashMap;

    public SongService() {
        songListHashMap = new HashMap<>();
    }

    public HashMap<String, HashMap<String, List<Song>>> getGroupedSongList() {
        return songListHashMap;
    }

    public void updateSongList(List<SongDAO> songDAOList) {
        songDAOList.forEach(songDAO -> updateSongList(songDAO, true));
    }

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

    public Song getSongFromUid(String songUid) {
        String[] uid = songUid.split(UID_SPLITTER);
        return songListHashMap.get(uid[0]).get(uid[1])
                .stream()
                .filter(song -> song.getTitle().equals(uid[2]))
                .findFirst().orElse(null);
    }

    public void addSongToList(List<Song> songs, Song newSong) {
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

    public void addVoicesToSong(Song song, int voices) {
        song.setVotes(song.getVotes() + voices);
    }

    public void resetSongVotes(Song song) {
        song.setVotes(0);
    }

    public void resetAllSongVotes() {
        songListHashMap.forEach((category, authors) -> authors
                .forEach((author, songs) -> songs.forEach(song ->
                        song.setVotes(0))
                ));
    }

    public List<Song> getGroupedSongList(String category) {
        List<Song> listToSort = getSongListFromHashMap(category);

        return listToSort.stream()
                .sorted(Comparator.comparingInt(Song::getVotes).reversed())
                .collect(Collectors.toList());
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