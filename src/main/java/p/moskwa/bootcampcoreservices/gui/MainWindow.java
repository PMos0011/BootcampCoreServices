package p.moskwa.bootcampcoreservices.gui;

import p.moskwa.bootcampcoreservices.services.RemovedSongsServices;
import p.moskwa.bootcampcoreservices.services.SongService;

import javax.swing.*;
import java.awt.*;

/**
 * Application root container
 *
 * @since 1.0
 */
public class MainWindow extends JFrame {

    private static MainWindow mainWindow;
    private final MainContentPanel mainContentPanel;
    private final SideBar sideBar;
    private final SongService songService;
    private final RemovedSongsServices removedSongsServices;

    public MainWindow() {
        MenuBar menuBar = new MenuBar();
        mainContentPanel = new MainContentPanel();
        sideBar = new SideBar();
        songService = new SongService();
        removedSongsServices = new RemovedSongsServices();

        this.setTitle("Bootcamp Core Services P. Moskwa");
        this.setLayout(new BorderLayout(20, 0));
        this.setSize(1366, 768);

        this.add(menuBar.getMenu(), BorderLayout.NORTH);
        this.add(mainContentPanel.getContent(), BorderLayout.CENTER);
        this.add(sideBar.getSideBarContent(), BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow = this;
    }

    public void showWindow() {
        this.setVisible(true);
    }

    public static MainWindow getMainWindowInstance() {
        return mainWindow;
    }

    public MainContentPanel getMainContentPanel() {
        return mainContentPanel;
    }

    public SideBar getSideBar() {
        return sideBar;
    }

    public SongService getSongService() {
        return songService;
    }

    public RemovedSongsServices getRemoveSongsServices() {
        return removedSongsServices;
    }

    public void updateContent() {
        if (removedSongsServices.isErrorToDisplay())
            mainContentPanel.displayInvalidSongs(removedSongsServices.getRemovedSongs());
        else
            displaySongs();
    }

    public void displaySongs() {
        mainContentPanel.displaySongs(songService.getSortedSongList());
    }

    public void displaySongDetails(String songUid) {
        sideBar.displaySongDetails(
                songService.getSongFromUid(songUid));
    }
}
