package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.dataModel.RankedSongList;
import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;
import p.moskwa.bootcampCoreServices.gui.services.MainContentService;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindowInstance;

public class MainContentPanel extends InterfaceClear {
    private final String TABULATOR = "    ";

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
        clearView(content);

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
        getMainWindowInstance().getSideBar().displayErrorConfirmButton();
        getMainWindowInstance().revalidate();
    }

    public void displaySongs(HashMap<String, HashMap<String, List<Song>>> songList) {
        clearView(content);

        if (songList.size() < 1) {
            content.add(new JLabel(TABULATOR + "Wygląda nia to że nie mam nic do wyświetlenia"));
        } else {
            JPanel songPanel = new JPanel();
            songPanel.setLayout(new GridLayout(0, 1));
            songPanel.add(new JLabel(TABULATOR + "Piosenki:"));
            songList.forEach((category, authors) -> authors
                    .forEach((author, songs) -> songs.forEach(song ->
                            songPanel.add(createSongLabel(song))
                    )));
            JScrollPane jScrollPane = new JScrollPane(songPanel);
            jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            content.add(jScrollPane);
            getMainWindowInstance().getSideBar().displaySongDetails(null);
        }
        getMainWindowInstance().revalidate();
    }

    public void displayReport(List<RankedSongList> rankingList) {
        clearView(content);
        if (rankingList.size() < 1) {
            content.add(new JLabel(TABULATOR + "Wygląda nia to że nie mam nic do wyświetlenia"));
        } else {
            JPanel reportPanel = new JPanel();
            reportPanel.setLayout(new GridLayout(0, 4));
            reportPanel.add(new JLabel("Miejsce"));
            reportPanel.add(new JLabel("Autor"));
            reportPanel.add(new JLabel(TABULATOR + "Tytuł"));
            reportPanel.add(new JLabel(TABULATOR + "Głosy"));
            
            rankingList.forEach(list ->
                    list.getSongList().forEach(song -> {
                        reportPanel.add(new JLabel(String.valueOf(list.getPlace())));
                        reportPanel.add((new JLabel(song.getAuthor())));
                        reportPanel.add((new JLabel(TABULATOR + song.getTitle())));
                        reportPanel.add(new JLabel(TABULATOR + song.getVotes().toString()));
                    }));

            JScrollPane jScrollPane = new JScrollPane(reportPanel);
            jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            content.add(jScrollPane);
        }
        getMainWindowInstance().revalidate();
    }

    private JLabel createSongLabel(Song song) {
        JLabel songLabel = new JLabel(TABULATOR + song.toString() + TABULATOR);
        songLabel.setName(song.getUid());
        songLabel.addMouseListener(mainContentService);
        songLabel.setOpaque(true);
        return songLabel;
    }
}