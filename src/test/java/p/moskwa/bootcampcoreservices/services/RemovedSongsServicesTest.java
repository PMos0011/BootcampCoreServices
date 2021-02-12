package p.moskwa.bootcampcoreservices.services;

import org.junit.jupiter.api.*;
import p.moskwa.bootcampcoreservices.datamodel.SongDAO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RemovedSongsServicesTest {

    static RemovedSongsServices removedSongsServices;

    @BeforeAll
    static void beforeAllSetUp() {
        removedSongsServices = new RemovedSongsServices();
    }

    @Test
    @Order(1)
    void updateRemovedSongsList_shouldNotAddCollection() {
        List<SongDAO> emptyList = new ArrayList<>();
        removedSongsServices.updateRemovedSongsList(emptyList, "sample/file");

        assertEquals(0, removedSongsServices.getRemovedSongs().size());
    }

    @Test
    @Order(2)
    void updateRemovedSongsList_shouldAddCollection() {
        List<SongDAO> removedSongList = new ArrayList<>() {{
            add(new SongDAO("t", "au", "al", "c", "0"));
        }};

        removedSongsServices.updateRemovedSongsList(removedSongList, "sample/file");
        assertEquals(1, removedSongsServices.getRemovedSongs().size());
    }

    @Test
    @Order(3)
    void isErrorToDisplay_shouldReturnTrue() {
        assertTrue(removedSongsServices.isErrorToDisplay());
    }

    @Test
    @Order(5)
    void isErrorToDisplay_shouldReturnFalse() {
        assertFalse(removedSongsServices.isErrorToDisplay());
    }

    @Test
    @Order(4)
    void clearList() {
        removedSongsServices.clearList();
        assertEquals(0, removedSongsServices.getRemovedSongs().size());
    }
}
