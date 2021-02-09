package p.moskwa.bootcampCoreServices.mappers;

import org.junit.jupiter.api.Test;
import p.moskwa.bootcampCoreServices.dataModel.Categories;
import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

import static org.junit.jupiter.api.Assertions.*;

class SongMapperTest {
    private final String TITLE = "MyTitle";
    private final String AUTHOR = "AnyAuthor";
    private final String ALBUM = "TheBest";
    private final String CATEGORY = "Rock";
    private final String VOTES = "10";

    @Test
    void songDAOToSong() {
        SongDAO songDAO = new SongDAO(TITLE, AUTHOR, ALBUM, CATEGORY, VOTES);

        Song song = SongMapper.INSTANCE.songDAOToSong(songDAO);

        assertEquals(TITLE, song.getTitle());
        assertEquals(AUTHOR, song.getAuthor());
        assertEquals(ALBUM, song.getAlbum());
        assertEquals(Categories.valueOf(CATEGORY.toUpperCase()), song.getCategory());
        assertEquals(Integer.parseInt(VOTES), song.getVotes());
    }
}