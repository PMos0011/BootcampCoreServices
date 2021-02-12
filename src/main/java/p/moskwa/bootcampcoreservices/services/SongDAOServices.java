package p.moskwa.bootcampcoreservices.services;

import p.moskwa.bootcampcoreservices.datamodel.SongDAO;
import p.moskwa.bootcampcoreservices.songsfilehandler.FileHandler;
import p.moskwa.bootcampcoreservices.validator.Validator;

import java.io.File;
import java.util.List;

import static p.moskwa.bootcampcoreservices.gui.MainWindow.getMainWindowInstance;

/**
 * SongDAO services
 *
 * @see SongDAO
 * @since 1.0
 */
public class SongDAOServices {

    private final FileHandler fileHandler;
    private final Validator validator;

    public SongDAOServices() {
        fileHandler = new FileHandler();
        validator = new Validator();
    }

    /**
     * Separates invalid songDAOs and updates collections of valid and invalid songDAOs
     *
     * @param file file with song data
     */
    public void handleSongListFromFile(File file) {
        List<SongDAO> songDAOList = fileHandler.readSongsFromFile(file);
        List<SongDAO> removedSongList = validator.validateSongDAOList(songDAOList);
        getMainWindowInstance().getRemoveSongsServices().updateRemovedSongsList(removedSongList, file.getPath());
        getMainWindowInstance().getSongService().updateSongList(songDAOList);
    }
}
