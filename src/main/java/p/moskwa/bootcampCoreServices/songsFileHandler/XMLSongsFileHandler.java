package p.moskwa.bootcampCoreServices.songsFileHandler;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;
import p.moskwa.bootcampCoreServices.dataModel.SongsDAO;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLSongsFileHandler implements SongsFileHandler {

    @Override
    public List<SongDAO> readSongsFromFile(File file) {
        try {
            return new XmlMapper().readValue(file, SongsDAO.class).getSongs();
        } catch (IOException ignored) {
        }
        return null;
    }
}