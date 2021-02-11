package p.moskwa.bootcampCoreServices.gui.services;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindowInstance;

/**
 * Song menu services
 *
 * @since 1.0
 */
public class SongMenuServices {

    /**
     * Invokes displayNewSongForm method
     *
     * @see p.moskwa.bootcampCoreServices.gui.SideBar
     */
    public void displayAddNewSongForm() {
        getMainWindowInstance().getSideBar().displayNewSongForm();
    }

    /**
     * Invokes resetAllSongVotes method
     *
     * @see p.moskwa.bootcampCoreServices.services.SongService
     */
    public void resetAllSongsVotes() {
        getMainWindowInstance().getSongService().resetAllSongVotes();
        showAllSongs();
    }

    /**
     * Invokes displaySongs
     *
     * @see p.moskwa.bootcampCoreServices.gui.MainWindow
     */
    public void showAllSongs() {
        getMainWindowInstance().displaySongs();
    }
}
