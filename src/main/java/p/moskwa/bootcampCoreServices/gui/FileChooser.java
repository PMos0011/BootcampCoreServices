package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.songsFileHandler.SupportedFiles;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Arrays;

public class FileChooser {
    private final JFileChooser fileChooser;

    public FileChooser() {
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(
                new FileNameExtensionFilter("Obsługiwane pliki",
                        Arrays.stream(SupportedFiles.values()).map(Enum::name).toArray(String[]::new)
                ));
        fileChooser.setCurrentDirectory(new File("."));
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}