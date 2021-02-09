package p.moskwa.bootcampCoreServices.services;

import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.gui.SideBar;

public class SideBarServices {

    public void addVoice(Song song, SideBar sideBar) {
        song.setVotes(song.getVotes() + 1);
        sideBar.displaySongDetails(song);
    }

    public void resetVotes(Song song, SideBar sideBar) {
        song.setVotes(0);
        sideBar.displaySongDetails(song);
    }
}
