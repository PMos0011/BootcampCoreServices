package p.moskwa.bootcampCoreServices.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import p.moskwa.bootcampCoreServices.dataModel.Categories;
import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;
import p.moskwa.bootcampCoreServices.mappers.SongMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SongServiceTest {
    SongService songService;
    static List<SongDAO> songsFromFile1;
    static List<SongDAO> songsFromFile2;
    static SongDAO songToUidTest;

    @BeforeAll
    static void beforeAllSetUp() {
        songToUidTest = new SongDAO("tit1", "auth5", "album", Categories.DOWNTEMPO.getCategory(), "3");

        songsFromFile1 = new ArrayList<>() {{
            add(new SongDAO("tit1", "auth1", "album", Categories.RAP.getCategory(), "5"));
            add(new SongDAO("tit2", "auth1", "album", Categories.RAP.getCategory(), "2"));
            add(new SongDAO("tit3", "auth2", "album", Categories.RAP.getCategory(), "7"));
            add(new SongDAO("tit4", "auth2", "album", Categories.RAP.getCategory(), "8"));
            add(new SongDAO("tit5", "auth3", "album", Categories.REGGAE.getCategory(), "6"));
        }};

        songsFromFile2 = new ArrayList<>() {{
            add(new SongDAO("tit1", "auth1", "album", Categories.RAP.getCategory(), "5"));
            add(new SongDAO("tit2", "auth1", "album", Categories.RAP.getCategory(), "2"));
            add(songToUidTest);
            add(new SongDAO("tit2", "auth5", "album", Categories.DOWNTEMPO.getCategory(), "6"));
        }};
    }

    @BeforeEach
    void beforeEachSetUp() {
        songService = new SongService();
    }

    @Test
    void updateSongListOneFile() {
        songService.updateSongList(songsFromFile1);
        HashMap<String, HashMap<String, List<Song>>> songListMap = songService.getGroupedSongList();
        List<Song> auth1SongsSet = songListMap.get(Categories.RAP.name()).get("auth1");
        List<Song> auth3SongsSet = songListMap.get(Categories.REGGAE.name()).get("auth3");

        assertEquals(2, songListMap.size());
        assertTrue(songListMap.containsKey(Categories.REGGAE.name()));
        assertEquals(2, auth1SongsSet.size());
        assertEquals("tit2", auth1SongsSet.get(1).getTitle());
        assertEquals(6, auth3SongsSet.get(0).getVotes());
    }

    @Test
    void updateSongListTwoFiles() {
        songService.updateSongList(songsFromFile1);
        songService.updateSongList(songsFromFile2);

        HashMap<String, HashMap<String, List<Song>>> songListMap = songService.getGroupedSongList();
        List<Song> auth1SongsSet = songListMap.get(Categories.RAP.name()).get("auth1");

        assertEquals(3, songListMap.size());
        assertEquals(2, auth1SongsSet.size());
        assertEquals(10, auth1SongsSet.get(0).getVotes());
    }

    @Test
    void getSongFromUid() {
        songService.updateSongList(songsFromFile2);
        HashMap<String, HashMap<String, List<Song>>> songListMap = songService.getGroupedSongList();

        String songUid = SongMapper.INSTANCE.songDAOToSong(songToUidTest).getUid();
        Song songFromUid = songService.getSongFromUid(songUid);
        Song songFromList = songListMap.get(Categories.DOWNTEMPO.name()).get("auth5").get(0);

        assertEquals(songFromList.getTitle(), songFromUid.getTitle());
        assertEquals(songFromList.getAuthor(), songFromUid.getAuthor());
        assertEquals(songFromList.getAlbum(), songFromUid.getAlbum());
        assertEquals(songFromList.getCategory().name(), songFromUid.getCategory().name());
    }

    @Test
    void getSortedSongListAllCategory(){
        songService.updateSongList(songsFromFile1);

        List<Song> sortedList = songService.getGroupedSongList(null);
        assertEquals(5,sortedList.size());
    }

    @Test
    void getSortedSongListOneCategory(){
        songService.updateSongList(songsFromFile1);

        List<Song> sortedList = songService.getGroupedSongList(Categories.REGGAE.name());
        assertEquals(1,sortedList.size());
    }
}