package p.moskwa.bootcampcoreservices.songsfilehandler;

import p.moskwa.bootcampcoreservices.datamodel.SongDAO;

import java.io.File;
import java.util.List;

/**
 * File handler public methods
 *
 * @since 1.0
 */
public interface SongsFileHandler {

    /**
     * Reads song data from file
     *
     * @param file file with song data
     * @return unverified SongDAOs as collection
     */
    List<SongDAO> readSongsFromFile(File file);
}
