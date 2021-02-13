package p.moskwa.bootcampcoreservices.gui;

import p.moskwa.bootcampcoreservices.songsfilehandler.SupportedFiles;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Arrays;

/**
 * Represent {@link JFileChooser} configured for the BootCampCoreServices application
 *
 * @since 1.0
 */
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
