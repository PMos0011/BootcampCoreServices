package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.services.SideBarServices;

import javax.swing.*;
import java.awt.*;

import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindow;

public class SideBar {
    private final JPanel sideBar;
    private final SideBarServices sideBarServices;

    public SideBar() {
        sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(300, 768));
        sideBar.setBackground(Color.LIGHT_GRAY);

        sideBarServices = new SideBarServices();
    }

    public JPanel getSideBarContent() {
        return sideBar;
    }

    public void displayErrorConfirmButton() {
        sideBar.removeAll();
        sideBar.repaint();
        JButton confirmErrorButton = new JButton("przeczytałem błędy");
        confirmErrorButton.addActionListener(action -> getMainWindow().displaySongs());
        sideBar.add(confirmErrorButton);
    }

    public void displaySongDetails(Song song) {
        sideBar.removeAll();
        sideBar.repaint();
        if (song != null) {
            JPanel songDetails = new JPanel();
            songDetails.setBackground(Color.LIGHT_GRAY);
            songDetails.setLayout(new GridLayout(0, 1));

            songDetails.add(new JLabel("Tytuł: " + song.getTitle()));
            songDetails.add(new JLabel("Wykonawca: " + song.getAuthor()));
            songDetails.add(new JLabel("Album: " + song.getAlbum()));
            songDetails.add(new JLabel("Gatunek: " + song.getCategory().getCategory()));
            songDetails.add(new JLabel("głosy: " + song.getVotes()));

            JButton addVoice = new JButton("Zagłosuj");
            addVoice.addActionListener(action -> sideBarServices.addVoice(song,this));
            songDetails.add(addVoice);
            JButton resetVotes = new JButton("Wyzeruj głosy");
            resetVotes.addActionListener(action -> sideBarServices.resetVotes(song,this));
            songDetails.add(resetVotes);

            sideBar.add(songDetails);
        }
        getMainWindow().revalidate();
    }
}