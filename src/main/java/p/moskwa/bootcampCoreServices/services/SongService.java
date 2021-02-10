package p.moskwa.bootcampCoreServices.services;

import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;
import p.moskwa.bootcampCoreServices.dataModel.SongList;
import p.moskwa.bootcampCoreServices.mappers.SongMapper;

import java.util.*;

import static p.moskwa.bootcampCoreServices.dataModel.Song.UID_SPLITTER;

public class SongService {
    private final SongList songList;

    public SongService() {
        songList = new SongList();
    }

    public HashMap<String, HashMap<String, List<Song>>> getSongList() {
        return songList.getSongListHashMap();
    }

    public void updateSongList(List<SongDAO> songDAOList) {
        songDAOList.forEach(songDAO -> updateSongList(songDAO, true));
    }

    public boolean updateSongList(SongDAO songDAO, boolean forceUpdate) {
        Song newSong = SongMapper.INSTANCE.songDAOToSong(songDAO);
        if (!songList.getSongListHashMap().containsKey(newSong.getCategory().name())) {
            addNewCategoryToList(songList.getSongListHashMap(), newSong);
        } else {
            if (!songList.getSongListHashMap().get(newSong.getCategory().name()).containsKey(newSong.getAuthor())) {
                addNewAuthorToList(songList.getSongListHashMap().get(newSong.getCategory().name()), newSong);
            } else {
                if (forceUpdate)
                    addSongToList(songList.getSongListHashMap().get(newSong.getCategory().name()).get(newSong.getAuthor()),
                            newSong);
                else
                    return false;
            }
        }
        return true;
    }

    public Song getSongFromUid(String songUid) {
        String[] uid = songUid.split(UID_SPLITTER);
        return songList.getSongListHashMap().get(uid[0]).get(uid[1])
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
        songList.getSongListHashMap().forEach((category, authors) -> authors
                .forEach((author, songs) -> songs.forEach(song ->
                        song.setVotes(0))
                ));
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