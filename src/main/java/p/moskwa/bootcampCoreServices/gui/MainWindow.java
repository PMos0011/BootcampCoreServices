package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.services.RemoveSongsServices;
import p.moskwa.bootcampCoreServices.services.SongService;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private static MainWindow mainWindow;
    private final MainContentPanel mainContentPanel;
    private final SideBar sideBar;
    private final SongService songService;
    private final RemoveSongsServices removeSongsServices;

    public MainWindow() {
        MenuBar menuBar = new MenuBar();
        mainContentPanel = new MainContentPanel();
        sideBar = new SideBar();
        songService = new SongService();
        removeSongsServices = new RemoveSongsServices();

        this.setTitle("Bootcamp Core Services P. Moskwa");
        this.setLayout(new BorderLayout(20, 0));
        this.setSize(1366, 768);

        this.add(menuBar.getMenu(), BorderLayout.NORTH);
        this.add(mainContentPanel.getContent(), BorderLayout.CENTER);
        this.add(sideBar.getSideBarContent(), BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        mainWindow = this;
    }

    public static MainWindow getMainWindow() {
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

    public RemoveSongsServices getRemoveSongsServices() {
        return removeSongsServices;
    }

    public void updateMainContent() {
        if (removeSongsServices.isErrorToDisplay())
            mainContentPanel.displayInvalidSongs(removeSongsServices.getRemovedSongs());
        else
            displaySongs();
    }

    public void displaySongs() {
        mainContentPanel.displaySongs(songService.getSongList());
    }

    public void displaySongDetails(String songUid) {
        sideBar.displaySongDetails(
                songService.getSongFromUid(songUid)
        );
    }
}