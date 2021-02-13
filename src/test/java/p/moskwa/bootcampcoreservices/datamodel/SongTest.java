package p.moskwa.bootcampcoreservices.datamodel;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {
    private final Categories CATEGORY_TO_TEST = Categories.ALTERNATIVE;
    private final String TITLE = "fak yea";
    private final String AUTHOR = "blahblah";
    private final String ALBUM = "blah";
    private final Integer VOTES = 100;

    @Test
    void getSongDetailsInPolish() {
        Song songToTest = new Song();
        songToTest.setTitle(TITLE);
        songToTest.setAuthor(AUTHOR);
        songToTest.setAlbum(ALBUM);
        songToTest.setCategory(CATEGORY_TO_TEST);
        songToTest.setVotes(VOTES);

        final String translatedAuthor = SongDAO.translateFieldNameToPolish("author") + ": " + songToTest.getAuthor();
        final String translatedCategory = SongDAO.translateFieldNameToPolish("category") + ": " + songToTest.getCategory().getCategory();

        List<String> translatedSongDetails = songToTest.getSongDetailsInPolish(songToTest);

        assertNotNull(translatedSongDetails.stream()
                .filter(detail -> detail.equals(translatedAuthor))
                .findAny().orElse(null));

        assertNotNull(translatedSongDetails.stream()
                .filter(detail -> detail.equals(translatedCategory))
                .findAny().orElse(null));
    }
}
