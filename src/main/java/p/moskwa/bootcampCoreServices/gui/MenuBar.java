package p.moskwa.bootcampCoreServices.gui;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.Songs;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class MenuBar implements ActionListener {

    private final JMenuBar menu;

    public MenuBar() {
        menu = new JMenuBar();
        menu.add(fileMenuConstructor());
        menu.add(new JMenu("Raporty"));
        menu.add(new JMenu("Pomoc"));
    }

    public JMenuBar getMenu() {
        return menu;
    }

    private JMenu fileMenuConstructor() {

        JMenuItem openFile = new JMenuItem("Otwórz plik");
        openFile.addActionListener(this);
        JMenuItem openDirectory = new JMenuItem("Otwórz folder");
        JMenuItem exit = new JMenuItem("Zamknij");

        JMenu fileMenu = new JMenu("Plik");
        fileMenu.add(openFile);
        fileMenu.add(openDirectory);
        fileMenu.add(exit);

        return fileMenu;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(
                new FileNameExtensionFilter("Obsługiwane pliki", "xml", "csv"));
        fileChooser.setCurrentDirectory(new File("./SampleFiles"));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

            String extension = file.getPath();
            extension = extension.substring(extension.lastIndexOf(".") + 1).toLowerCase();
            System.out.println(extension);
            switch (extension) {
                case "xml":
                    XmlMapper mapperXml = new XmlMapper();
                    try {
                        Songs songs = mapperXml.readValue(file, Songs.class);
                        System.out.println(songs.getSongs().get(1).getVotes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "csv":
                    CsvMapper mapper = new CsvMapper();
                    try {
                        MappingIterator<Song> personIter = mapper.readerWithSchemaFor(Song.class).readValues(file);
                        List<Song> son = personIter.readAll().stream().skip(1).collect(Collectors.toList());
                        System.out.println(son.get(2).getVotes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("da fuck?");
                    break;
            }


        }
    }
}
