package p.moskwa.bootcampcoreservices.mappers;

import org.junit.jupiter.api.Test;
import p.moskwa.bootcampcoreservices.datamodel.Categories;
import p.moskwa.bootcampcoreservices.datamodel.Song;
import p.moskwa.bootcampcoreservices.datamodel.SongDAO;

import static org.junit.jupiter.api.Assertions.*;
import static p.moskwa.bootcampcoreservices.datamodel.Song.UID_SPLITTER;

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

        String[] uid = song.getUid().split(UID_SPLITTER);
        assertEquals(Categories.ROCK.name(), uid[0]);
        assertEquals(AUTHOR, uid[1]);
        assertEquals(TITLE, uid[2]);
    }
}