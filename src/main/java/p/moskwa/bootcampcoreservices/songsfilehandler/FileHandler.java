package p.moskwa.bootcampcoreservices.songsfilehandler;

import p.moskwa.bootcampcoreservices.datamodel.SongDAO;

import java.io.File;
import java.util.List;

/**
 * Song file handler
 *
 * @since 1.0
 */
public class FileHandler {

    /**
     * Determines instance of file handler depending on input file
     *
     * @param file input song file
     * @return unverified SongDAO as collection
     * @see SongDAO
     * @see CSVSongsFileHandler
     * @see XMLSongsFileHandler
     * @see SupportedFiles
     */
    public List<SongDAO> readSongsFromFile(File file) {
        SupportedFiles extension = getExtension(file);

        if (extension != null) {
            switch (extension) {
                case CSV:
                    return new CSVSongsFileHandler().readSongsFromFile(file);
                case XML:
                    return new XMLSongsFileHandler().readSongsFromFile(file);
                default:
                    throw new IllegalStateException("Unexpected value: " + extension);
            }
        }
        return null;
    }

    private SupportedFiles getExtension(File file) {
        String extension = file.getPath();
        extension = extension.substring(extension.lastIndexOf(".") + 1).toUpperCase();
        try {
            return SupportedFiles.valueOf(extension);
        } catch (Exception ignored) {
        }
        return null;
    }
}
