package p.moskwa.bootcampcoreservices.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TranslateSongFieldsNameTest {

    @Test
    void translateFieldNameToPl() {

        String title = SongDAO.class.getDeclaredFields()[0].getName();
        String author = SongDAO.class.getDeclaredFields()[1].getName();
        String album = SongDAO.class.getDeclaredFields()[2].getName();
        String category = SongDAO.class.getDeclaredFields()[3].getName();
        String votes = SongDAO.class.getDeclaredFields()[4].getName();

        assertEquals("Tytuł",TranslateSongFieldsName.translateFieldNameToPl(title));
        assertEquals("Wykonawca",TranslateSongFieldsName.translateFieldNameToPl(author));
        assertEquals("Album",TranslateSongFieldsName.translateFieldNameToPl(album));
        assertEquals("Gatunek",TranslateSongFieldsName.translateFieldNameToPl(category));
        assertEquals("Głosy",TranslateSongFieldsName.translateFieldNameToPl(votes));
        assertNull(TranslateSongFieldsName.translateFieldNameToPl("randomString"));
    }
}