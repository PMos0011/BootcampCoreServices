package p.moskwa.bootcampCoreServices.services;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindow;

public class FileMenuServices {

private final SongDAOServices songDAOServices;

    public FileMenuServices() {
       songDAOServices = new SongDAOServices();
    }

    public void openFileAction(JFileChooser fileChooser) {
        fileChooser.setDialogTitle("Wybierz plik");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            getMainWindow().getRemoveSongsServices().clearList();
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            songDAOServices.handleSongListFromFile(file);
        }
    }

    public void openDirectoryAction(JFileChooser fileChooser) {
        fileChooser.setDialogTitle("Wybierz folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            getMainWindow().getRemoveSongsServices().clearList();
            File directory = new File(fileChooser.getSelectedFile().getAbsolutePath());
            Set<File> files = getAllFilesFromSelectedDirectory(directory);
            files.forEach(songDAOServices::handleSongListFromFile);
        }
    }

    public void exitApplication() {
        System.exit(0);
    }

    private Set<File> getAllFilesFromSelectedDirectory(@NotNull File directory) {
        return Stream.of(directory.listFiles())
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }
}