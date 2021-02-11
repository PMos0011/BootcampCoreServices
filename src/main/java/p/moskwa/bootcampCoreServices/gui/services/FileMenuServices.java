package p.moskwa.bootcampCoreServices.gui.services;

import org.jetbrains.annotations.NotNull;
import p.moskwa.bootcampCoreServices.services.SongDAOServices;

import javax.swing.*;
import java.io.File;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindowInstance;

/**
 * File menu services
 *
 * @since 1.0
 */
public class FileMenuServices {
    private final SongDAOServices songDAOServices;

    public FileMenuServices() {
        songDAOServices = new SongDAOServices();
    }

    /**
     * Processes selected file
     *
     * @param fileChooser application fileChooser
     */
    public void openFileAction(JFileChooser fileChooser) {
        fileChooser.setDialogTitle("Wybierz plik");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            getMainWindowInstance().getRemoveSongsServices().clearList();
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            songDAOServices.handleSongListFromFile(file);
            getMainWindowInstance().updateContent();
        }
    }

    /**
     * Processes files from selected directory
     *
     * @param fileChooser application fileChooser
     */
    public void openDirectoryAction(JFileChooser fileChooser) {
        fileChooser.setDialogTitle("Wybierz folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            getMainWindowInstance().getRemoveSongsServices().clearList();
            File directory = new File(fileChooser.getSelectedFile().getAbsolutePath());
            Set<File> files = getAllFilesFromSelectedDirectory(directory);
            files.forEach(songDAOServices::handleSongListFromFile);
            getMainWindowInstance().updateContent();
        }
    }

    /**
     * Exits application
     */
    public void exitApplication() {
        System.exit(0);
    }

    private Set<File> getAllFilesFromSelectedDirectory(@NotNull File directory) {
        return Stream.of(directory.listFiles())
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }
}