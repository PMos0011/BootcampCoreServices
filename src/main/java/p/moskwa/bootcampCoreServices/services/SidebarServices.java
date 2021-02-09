package p.moskwa.bootcampCoreServices.services;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindow;

public class SidebarServices {

    public void confirmSongError(){
        System.out.println("zawieszka");
        getMainWindow().displaySong();
    }
}