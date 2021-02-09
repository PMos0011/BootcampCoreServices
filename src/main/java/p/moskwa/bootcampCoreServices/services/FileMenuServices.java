package p.moskwa.bootcampCoreServices.services;

import p.moskwa.bootcampCoreServices.dataModel.SongDAO;
import p.moskwa.bootcampCoreServices.validator.Validator;
import p.moskwa.bootcampCoreServices.songsFileHandler.FileHandler;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileMenuServices {

    private final FileHandler fileHandler;
    private final Validator validator;

    public FileMenuServices() {
        fileHandler = new FileHandler();
        validator = new Validator();
    }

    public void openFileAction(JFileChooser fileChooser) {

        fileChooser.setDialogTitle("Wybierz plik");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            List<SongDAO> songDAOList = fileHandler.readSongsFromFile(file);
            validator.validateSongDAOList(songDAOList);
            System.out.println(songDAOList.size());
        }
    }

    public void openDirectoryAction(JFileChooser fileChooser) {

        fileChooser.setDialogTitle("Wybierz folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File directory = new File(fileChooser.getSelectedFile().getAbsolutePath());
            Set<File> files = getAllFilesFromSelectedDirectory(directory);
            files.forEach(file -> {
                List<SongDAO> songDAOList = fileHandler.readSongsFromFile(file);
                validator.validateSongDAOList(songDAOList);
                System.out.println(songDAOList.size());
            });
        }
    }

    public void exitApplication() {
        System.exit(0);
    }

    private Set<File> getAllFilesFromSelectedDirectory(File directory) {
        return Stream.of(Objects.requireNonNull(directory.listFiles()))
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }
}