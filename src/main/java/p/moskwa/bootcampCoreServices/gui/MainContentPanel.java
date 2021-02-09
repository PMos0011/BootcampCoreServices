package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;
import p.moskwa.bootcampCoreServices.services.MainContentService;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindow;

public class MainContentPanel{
    private final JPanel content;
    private final MainContentService mainContentService;

    public MainContentPanel() {
        content = new JPanel();
        content.setLayout(new FlowLayout(FlowLayout.LEFT));
        content.add(new JLabel("Nie mam jeszcze nic do wyświetlenia - "));
        content.add(new JLabel("wczytaj dane"));

        mainContentService = new MainContentService();
    }

    public JPanel getContent() {
        return content;
    }

    public void displayInvalidSongs(HashMap<String, List<SongDAO>> removedSongs) {
        content.removeAll();
        content.repaint();
        JPanel errorContent = new JPanel();
        errorContent.setLayout(new GridLayout(0, 1));
        removedSongs.forEach((fileName, songs) -> {
            errorContent.add(new JLabel("W pliku:"));
            errorContent.add(new JLabel(fileName));
            errorContent.add(new JLabel("ignoruje:"));
            songs
                    .forEach(value -> errorContent.add(new JLabel(value.toString())));
            errorContent.add(new JLabel(""));
        });
        JScrollPane jScrollPane = new JScrollPane(errorContent);
        content.add(jScrollPane);
        getMainWindow().getSideBar().displayErrorConfirmButton();
        getMainWindow().revalidate();
    }

    public void displaySongs(HashMap<String, HashMap<String, List<Song>>> songList) {
        content.removeAll();
        content.repaint();
        if (songList.keySet().size() < 1) {
            content.add(new JLabel("Wygląda nia to że nie mam nic do wyświetlenia"));
        } else {
            JPanel songPanel = new JPanel();
            songPanel.setLayout(new GridLayout(0, 1));
            songPanel.add(new JLabel("Piosenki:"));
            songList.forEach((category, authors) -> authors
                    .forEach((author, songs) -> songs.forEach(song ->
                            songPanel.add(createSongLabel(song))
                    )));
            JScrollPane jScrollPane = new JScrollPane(songPanel);
            jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            content.add(jScrollPane);
            getMainWindow().getSideBar().displaySongDetails(null);
        }
        getMainWindow().revalidate();
    }

    private JLabel createSongLabel(Song song) {
        JLabel songLabel = new JLabel(song.toString());
        songLabel.setName(song.getUid());
        songLabel.addMouseListener(mainContentService);
        songLabel.setOpaque(true);
        return songLabel;
    }
}