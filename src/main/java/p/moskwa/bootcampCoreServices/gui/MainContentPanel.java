package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class MainContentPanel {
    private final JPanel content;

    public MainContentPanel() {
        content = new JPanel();
        content.setLayout(new FlowLayout(FlowLayout.LEFT));
        content.add(new JLabel("Nie mam jeszcze nic do wyświetlenia - "));
        content.add(new JLabel("wczytaj dane"));
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
        MainWindow.getMainWindow().getSideBar().displayErrorConfirmButton();
        MainWindow.getMainWindow().revalidate();
    }

    public void displaySongs(HashMap<String, HashMap<String, List<Song>>> songList) {
        content.removeAll();
        content.repaint();
        if (songList.keySet().size() < 1) {
            content.add(new JLabel("Wygląda nia to że nie mam nic do wyświetlenia"));
        } else {
            JPanel songPanel = new JPanel();
            songPanel.setLayout(new GridLayout(0, 1));
            songPanel.add(new Label("Piosenki:"));
            songList.forEach((category, authors) -> authors
                    .forEach((author, songs) -> {
                        songs.forEach(song -> songPanel.add(new JLabel(song.toString())));
                    }));
            JScrollPane jScrollPane = new JScrollPane(songPanel);
            content.add(jScrollPane);
        }
        MainWindow.getMainWindow().revalidate();
    }
}