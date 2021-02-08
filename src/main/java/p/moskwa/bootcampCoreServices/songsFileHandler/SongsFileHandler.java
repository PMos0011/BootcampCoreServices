package p.moskwa.bootcampCoreServices.songsFileHandler;

import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

import java.io.File;
import java.util.List;

public interface SongsFileHandler {

    List<SongDAO> readSongsFromFile(File file);
}