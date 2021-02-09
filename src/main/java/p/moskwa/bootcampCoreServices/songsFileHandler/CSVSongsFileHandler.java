package p.moskwa.bootcampCoreServices.songsFileHandler;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CSVSongsFileHandler implements SongsFileHandler {

    @Override
    public List<SongDAO> readSongsFromFile(File file) {
        try {
            MappingIterator<SongDAO> songsIterator = new CsvMapper().readerWithSchemaFor(SongDAO.class).readValues(file);
            return songsIterator.readAll()
                    .stream()
                    .skip(1)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}