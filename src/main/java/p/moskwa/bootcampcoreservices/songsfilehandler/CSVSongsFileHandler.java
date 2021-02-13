package p.moskwa.bootcampcoreservices.songsfilehandler;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import p.moskwa.bootcampcoreservices.datamodel.SongDAO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * .csv file handler
 *
 * @see SongsFileHandler
 */
public class CSVSongsFileHandler implements SongsFileHandler {

    /**
     * Reads SongDAO object data from .csv file.
     * File must have columns name in header
     *
     * @param file .csv file with song data to read
     * @return unverified SongDAOs as collection
     * @see SongDAO
     */
    @Override
    public List<SongDAO> readSongsFromFile(File file) {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        try {
            MappingIterator<SongDAO> songsIterator = new CsvMapper()
                    .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                    .readerFor(SongDAO.class)
                    .with(schema)
                    .readValues(file);

            return new ArrayList<>(songsIterator.readAll());
        } catch (IOException ignored) {
        }
        return null;
    }
}
