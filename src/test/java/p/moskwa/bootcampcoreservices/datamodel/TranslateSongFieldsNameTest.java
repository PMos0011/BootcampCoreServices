package p.moskwa.bootcampcoreservices.datamodel;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class TranslateSongFieldsNameTest {

    @Test
    void translateFieldNameToPl() {

        for (Field field : SongDAO.class.getDeclaredFields())
            assertNotNull(SongDAO.translateFieldNameToPolish(field.getName()));

        assertEquals("Wykonawca", SongDAO.translateFieldNameToPolish("author"));
        assertEquals("Gatunek", SongDAO.translateFieldNameToPolish("category"));
    }
}
