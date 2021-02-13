package p.moskwa.bootcampcoreservices.gui;

import p.moskwa.bootcampcoreservices.datamodel.RankedSongList;
import p.moskwa.bootcampcoreservices.datamodel.Song;
import p.moskwa.bootcampcoreservices.datamodel.SongDAO;
import p.moskwa.bootcampcoreservices.gui.services.MainContentService;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static p.moskwa.bootcampcoreservices.gui.MainWindow.getMainWindowInstance;

/**
 * Application main content container
 *
 * @see InterfaceClear
 * @since 1.0
 */
public class MainContentPanel extends InterfaceClear {
    private final String TABULATOR = "    ";

    private final JPanel content;
    private final MainContentService mainContentService;

    public MainContentPanel() {
        content = new JPanel();
        content.setLayout(new FlowLayout(FlowLayout.LEFT));
        content.add(new JLabel("Nie mam jeszcze nic do wyświetlenia - "));
        content.add(new JLabel("załaduj może jakiś plik"));

        mainContentService = new MainContentService();
    }

    /**
     * Returns main content container
     *
     * @return content as {@link JPanel}
     */
    public JPanel getContent() {
        return content;
    }

    /**
     * Creates and displays content with rejected songs
     *
     * @param removedSongs rejected songs as collection
     * @see p.moskwa.bootcampcoreservices.services.RemovedSongsServices
     */
    public void displayInvalidSongs(HashMap<String, List<SongDAO>> removedSongs) {
        clearView(content);

        JPanel errorContent = new JPanel();
        errorContent.setLayout(new GridLayout(0, 1));
        AtomicReference<Integer> heightCounter = new AtomicReference<>(0);

        removedSongs.forEach((fileName, songs) -> {
            errorContent.add(new JLabel("W pliku:"));
            errorContent.add(new JLabel(fileName));
            errorContent.add(new JLabel("ignoruje z powodu błędów:"));
            heightCounter.updateAndGet(v -> v + 60);

            songs.forEach(value -> {
                errorContent.add(new JLabel(value.toString()));
                heightCounter.updateAndGet(v -> v + 20);
            });
            errorContent.add(new JLabel(""));
            heightCounter.updateAndGet(v -> v + 20);
        });
        JScrollPane jScrollPane = createJScrollPane(errorContent, heightCounter.get());
        content.add(jScrollPane);
        getMainWindowInstance().getSideBar().displayErrorConfirmButton();
        getMainWindowInstance().revalidate();
    }

    /**
     * Creates and displays content with valid songs
     *
     * @param songList valid songs as collection
     * @see p.moskwa.bootcampcoreservices.services.SongService
     */
    public void displaySongs(HashMap<String, HashMap<String, List<Song>>> songList) {
        clearView(content);

        if (songList.size() < 1) {
            content.add(new JLabel(TABULATOR + "Wygląda na to że nie mam nic do wyświetlenia. No cóż, może następnym razem coś będzie."));
        } else {
            JPanel songPanel = new JPanel();
            songPanel.setLayout(new GridLayout(0, 1));
            AtomicReference<Integer> heightCounter = new AtomicReference<>(20);

            songPanel.add(new JLabel(TABULATOR + "Piosenki:"));
            songList.forEach((category, authors) -> authors
                    .forEach((author, songs) -> songs.forEach(song -> {
                                songPanel.add(createSongLabel(song));
                                heightCounter.updateAndGet(v -> v + 21);
                            }
                    )));

            JScrollPane jScrollPane = createJScrollPane(songPanel, heightCounter.get());
            content.add(jScrollPane);
            getMainWindowInstance().getSideBar().displaySongDetails(null);
        }
        getMainWindowInstance().revalidate();
    }

    /**
     * Creates and displays content with songs ranking report
     *
     * @param rankingList {@link RankedSongList} as collection
     * @see p.moskwa.bootcampcoreservices.gui.services.ReportMenuServices
     */
    public void displayReport(List<RankedSongList> rankingList) {
        clearView(content);
        if (rankingList.size() < 1) {
            content.add(new JLabel(TABULATOR + "Wygląda na to że nie mam nic do wyświetlenia. No cóż, może następnym razem coś będzie."));
        } else {
            JPanel reportPanel = new JPanel();
            reportPanel.setLayout(new GridLayout(0, 4));
            AtomicReference<Integer> heightCounter = new AtomicReference<>(20);

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
                        heightCounter.updateAndGet(v -> v + 20);
                    }));

            JScrollPane jScrollPane = createJScrollPane(reportPanel, heightCounter.get());
            jScrollPane.setName("tralalala");
            content.add(jScrollPane);
        }
        getMainWindowInstance().revalidate();
    }

    private JScrollPane createJScrollPane(JPanel panel, int height) {
        JScrollPane jScrollPane = new JScrollPane(panel);

        if (height > 700) {
            height = 700;
        }

        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        jScrollPane.setPreferredSize(new Dimension(1000, height));

        return jScrollPane;
    }

    private JLabel createSongLabel(Song song) {
        JLabel label = new JLabel(TABULATOR + song.toString() + TABULATOR);
        label.setName(song.getUid());
        label.addMouseListener(mainContentService);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setPreferredSize(new Dimension(990, 20));
        return label;
    }
}
