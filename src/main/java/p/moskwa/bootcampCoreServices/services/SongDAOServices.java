package p.moskwa.bootcampCoreServices.services;

import p.moskwa.bootcampCoreServices.dataModel.SongDAO;
import p.moskwa.bootcampCoreServices.songsFileHandler.FileHandler;
import p.moskwa.bootcampCoreServices.validator.Validator;

import java.io.File;
import java.util.List;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindow;

public class SongDAOServices {

    private final FileHandler fileHandler;
    private final Validator validator;

    public SongDAOServices() {
        fileHandler = new FileHandler();
        validator = new Validator();
    }

    public void handleSongListFromFile(File file) {
        List<SongDAO> songDAOList = fileHandler.readSongsFromFile(file);
        List<SongDAO> removedSongList = validator.validateSongDAOList(songDAOList);
        getMainWindow().getRemoveSongsServices().updateRemovedSongsList(removedSongList,file.getPath());
        getMainWindow().getSongService().updateSongListAndRefreshView(songDAOList);
    }
}