package p.moskwa.bootcampcoreservices.gui.services;

import static p.moskwa.bootcampcoreservices.gui.MainWindow.getMainWindowInstance;

/**
 * Song menu services
 *
 * @since 1.0
 */
public class SongMenuServices {

    /**
     * Invokes displayNewSongForm method
     *
     * @see p.moskwa.bootcampcoreservices.gui.SideBar
     */
    public void displayAddNewSongForm() {
        getMainWindowInstance().getSideBar().displayNewSongForm();
    }

    /**
     * Invokes resetAllSongVotes method
     *
     * @see p.moskwa.bootcampcoreservices.services.SongService
     */
    public void resetAllSongsVotes() {
        getMainWindowInstance().getSongService().resetAllSongVotes();
        showAllSongs();
    }

    /**
     * Invokes displaySongs
     *
     * @see p.moskwa.bootcampcoreservices.gui.MainWindow
     */
    public void showAllSongs() {
        getMainWindowInstance().displaySongs();
    }
}
