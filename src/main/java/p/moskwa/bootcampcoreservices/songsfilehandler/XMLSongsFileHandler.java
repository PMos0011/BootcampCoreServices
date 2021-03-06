package p.moskwa.bootcampcoreservices.songsfilehandler;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import p.moskwa.bootcampcoreservices.datamodel.SongDAO;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * .xml file handler
 *
 * @see SongsFileHandler
 */
public class XMLSongsFileHandler implements SongsFileHandler {

    /**
     * Reads SongDAO object data from .xml file.
     *
     * @param file .xml file with song data to read
     * @return unverified SongDAOs as collection
     * @see SongDAO
     */
    @Override
    public List<SongDAO> readSongsFromFile(File file) {
        try {
            return new XmlMapper().readValue(file, SongsDAO.class).getSongs();
        } catch (IOException ignored) {
        }
        return null;
    }

    private static class SongsDAO {
        @JacksonXmlProperty(localName = "song")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<SongDAO> songDAOS;

        public List<SongDAO> getSongs() {
            return songDAOS;
        }
    }
}
