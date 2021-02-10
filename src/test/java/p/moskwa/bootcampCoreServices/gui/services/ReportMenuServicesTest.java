package p.moskwa.bootcampCoreServices.gui.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import p.moskwa.bootcampCoreServices.dataModel.Categories;
import p.moskwa.bootcampCoreServices.dataModel.RankedSongList;
import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;
import p.moskwa.bootcampCoreServices.services.SongService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportMenuServicesTest {

    SongService songService;
    static ReportMenuServices reportMenuServices;
    static List<SongDAO> songsFromFile1;
    static List<SongDAO> songsFromFile2;
    static List<SongDAO> songsFromFile3;

    @BeforeAll
    static void beforeAllSetUp() {
        reportMenuServices = new ReportMenuServices();

        songsFromFile1 = new ArrayList<>() {{
            add(new SongDAO("tit1", "r_auth1", "album", Categories.RAP.getCategory(), "5"));        //the same
            add(new SongDAO("tit2", "r_auth1", "album", Categories.RAP.getCategory(), "20"));
            add(new SongDAO("tit3", "r_auth2", "album", Categories.RAP.getCategory(), "80"));       //the same
            add(new SongDAO("tit4", "r_auth2", "album", Categories.RAP.getCategory(), "8"));
            add(new SongDAO("tit5", "r_auth3", "album", Categories.RAP.getCategory(), "16"));       //the same
            add(new SongDAO("tit6", "r_auth2", "album", Categories.RAP.getCategory(), "5"));        //the same
            add(new SongDAO("tit7", "r_auth3", "album", Categories.RAP.getCategory(), "21"));
        }};

        songsFromFile2 = new ArrayList<>() {{
            add(new SongDAO("tit1", "d_auth1", "album", Categories.DOWNTEMPO.getCategory(), "80"));
            add(new SongDAO("tit2", "d_auth1", "album", Categories.DOWNTEMPO.getCategory(), "2"));      //the same x2
            add(new SongDAO("tit1", "d_auth5", "album", Categories.DOWNTEMPO.getCategory(), "16"));
            add(new SongDAO("tit2", "d_auth5", "album", Categories.DOWNTEMPO.getCategory(), "2"));
        }};

        songsFromFile3 = new ArrayList<>() {{
            add(new SongDAO("tit1", "e_auth1", "album", Categories.ELECTRONIC.getCategory(), "5"));
            add(new SongDAO("tit2", "e_auth1", "album", Categories.ELECTRONIC.getCategory(), "2"));
            add(new SongDAO("tit3", "e_auth5", "album", Categories.ELECTRONIC.getCategory(), "4"));
            add(new SongDAO("tit4", "e_auth5", "album", Categories.ELECTRONIC.getCategory(), "6"));     //the same
            add(new SongDAO("tit1", "e_auth5", "album", Categories.ELECTRONIC.getCategory(), "3"));
            add(new SongDAO("tit2", "e_auth5", "album", Categories.ELECTRONIC.getCategory(), "6"));
        }};
    }

    @BeforeEach
    void beforeEachSetUp() {
        songService = new SongService();
    }

    @Test
    void createReportOneFileTop3() {
        songService.updateSongList(songsFromFile1);
        List<Song> sortedList = songService.getSortedSongList(Categories.RAP.name());
        List<RankedSongList> rankingList = reportMenuServices.createRankingList(sortedList, 3);

        assertEquals(3, rankingList.size());
    }

    @Test
    void createReportOneFileTop3ExAequo() {
        songService.updateSongList(songsFromFile3);
        List<Song> sortedList = songService.getSortedSongList(Categories.ELECTRONIC.name());
        List<RankedSongList> rankingList = reportMenuServices.createRankingList(sortedList, 3);

        assertEquals(2, rankingList.size());
        assertEquals(2, rankingList.get(0).getSongList().size());
        assertNull(rankingList.stream().filter(list -> list.getPlace() == 2).findAny().orElse(null));
    }

    @Test
    void createReportAll() {
        songService.updateSongList(songsFromFile1);
        songService.updateSongList(songsFromFile2);
        songService.updateSongList(songsFromFile3);

        List<Song> sortedList = songService.getSortedSongList(null);
        List<RankedSongList> rankingList = reportMenuServices.createRankingList(sortedList, 0);

        assertEquals(10, rankingList.size());
        assertEquals(2, rankingList.get(0).getSongList().size());
        assertEquals(3, rankingList.get(rankingList.size()-1).getSongList().size());
        assertEquals("r_auth2", rankingList.get(0).getSongList().stream()
                .filter(song -> song.getAuthor()
                        .equals("r_auth2"))
                .findFirst()
                .get()
                .getAuthor());
        assertEquals("d_auth1", rankingList.get(0).getSongList().stream()
                .filter(song -> song.getAuthor()
                        .equals("d_auth1"))
                .findFirst()
                .get()
                .getAuthor());
        assertNull(rankingList.stream().filter(list -> list.getPlace() == 2).findAny().orElse(null));
        assertNull(rankingList.stream().filter(list -> list.getPlace() == 6).findAny().orElse(null));
    }
}