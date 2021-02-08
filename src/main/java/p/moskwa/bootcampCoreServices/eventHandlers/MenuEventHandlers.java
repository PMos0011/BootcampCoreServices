package p.moskwa.bootcampCoreServices.eventHandlers;

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

public class MenuEventHandlers {

    public void openFileAction(JFileChooser fileChooser) {

        fileChooser.setDialogTitle("Wybierz plik");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            List<SongDAO> songDAOS = new FileHandler().readSongsFromFile(file);
            new Validator().validateSongDAOList(songDAOS, file.getName());
            System.out.println(songDAOS.size());
        }
    }

    public void openDirectoryAction(JFileChooser fileChooser) {

        fileChooser.setDialogTitle("Wybierz folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File directory = new File(fileChooser.getSelectedFile().getAbsolutePath());
            Set<File> files = getAllFilesFromSelectedDirectory(directory);
            FileHandler fileHandler = new FileHandler();
            Validator validator = new Validator();
            files.forEach(file -> {
                List<SongDAO> songDAOS = fileHandler.readSongsFromFile(file);
                validator.validateSongDAOList(songDAOS, file.getName());
                System.out.println(songDAOS.size());
            });
        }
    }

    private Set<File> getAllFilesFromSelectedDirectory(File directory) {
        return Stream.of(Objects.requireNonNull(directory.listFiles()))
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }

    public void exitApplication() {
        System.exit(0);
    }
}