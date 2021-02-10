package p.moskwa.bootcampCoreServices.gui.services;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindowInstance;

public class SongMenuServices {

    public void displayAddNewSongForm(){
        getMainWindowInstance().getSideBar().displayNewSongForm();
    }

    public void resetAllSongsVotes(){
        getMainWindowInstance().getSongService().resetAllSongVotes();
        showAllSongs();
    }

    public void showAllSongs(){
        getMainWindowInstance().displaySongs();
    }
}
