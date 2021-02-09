package p.moskwa.bootcampCoreServices.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {
    List<SongDAO> songDAOList;
    Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();

        SongDAO correctSong = new SongDAO("Imported", "Jessie Rayez", "Before Love...", "RnB", "10");
        SongDAO correctSongWithSpacesAfter = new SongDAO("Title     ", "Auth    ", "Album...    ", "Rock    ", "3    ");
        SongDAO invalidCategorySong = new SongDAO("invalid category", "Halsey", "Manic", "Country pop", "3");
        SongDAO invalidVotesSong = new SongDAO("invalid votes", "Halsey", "Manic", "Country pop", "k3");
        SongDAO invalidNegativeVotesSong = new SongDAO("negative votes", "Halsey", "Manic", "Country pop", "-5");
        SongDAO invalidSongEmptyAuthor = new SongDAO("invalid empty author", "", "Manic", "Alternative", "3");
        SongDAO invalidSongNullAlbum = new SongDAO("invalid null album", "Halsey", null, "Alternative", "3");
        SongDAO invalidSongNullVotes = new SongDAO("invalid null album", "Halsey", "Manic", "Alternative", null);

        songDAOList = new ArrayList<>() {{
            add(correctSong);
            add(correctSongWithSpacesAfter);
            add(invalidCategorySong);
            add(invalidVotesSong);
            add(invalidNegativeVotesSong);
            add(invalidSongEmptyAuthor);
            add(invalidSongNullAlbum);
            add(invalidSongNullVotes);
        }};
    }

    @Test
    void validateSongDAOList() {
        List<SongDAO> removedSongs = validator.validateSongDAOList(songDAOList);

        assertEquals(2, songDAOList.size());
        assertEquals(6, removedSongs.size());
        assertEquals("Title", songDAOList.get(1).title);
        assertEquals("invalid category", removedSongs.get(0).title);
    }
}