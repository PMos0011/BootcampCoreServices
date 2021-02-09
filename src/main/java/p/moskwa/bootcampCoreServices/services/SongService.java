package p.moskwa.bootcampCoreServices.services;

import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;
import p.moskwa.bootcampCoreServices.dataModel.SongList;
import p.moskwa.bootcampCoreServices.mappers.SongMapper;

import java.util.*;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindow;

public class SongService {
    private final SongList songList;

    public SongService() {
        songList = new SongList();
    }

    public void updateSongListAndRefreshView(List<SongDAO> songDAOList) {
        updateSongList(songDAOList);
        getMainWindow().updateMainContent();
    }

    public HashMap<String, HashMap<String, List<Song>>> getSongList() {
        return songList.getSongListHashMap();
    }

    public void updateSongList(List<SongDAO> songDAOList) {
        HashMap<String, HashMap<String, List<Song>>> songListHashMap = songList.getSongListHashMap();

        songDAOList.forEach(songDAO -> {
                    Song newSong = SongMapper.INSTANCE.songDAOToSong(songDAO);
                    if (!songListHashMap.containsKey(newSong.getCategory().name())) {
                        addNewCategoryToList(songListHashMap, newSong);
                    } else {
                        if (!songListHashMap.get(newSong.getCategory().name()).containsKey(newSong.getAuthor())) {
                            addNewAuthorToList(songListHashMap.get(newSong.getCategory().name()), newSong);
                        } else {
                            addSongToList(songListHashMap.get(newSong.getCategory().name()).get(newSong.getAuthor()),
                                    newSong);
                        }
                    }
                }
        );
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
}