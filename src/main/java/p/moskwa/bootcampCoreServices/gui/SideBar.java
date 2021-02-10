package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.dataModel.Categories;
import p.moskwa.bootcampCoreServices.dataModel.Song;
import p.moskwa.bootcampCoreServices.dataModel.SongDAO;
import p.moskwa.bootcampCoreServices.gui.services.SideBarServices;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static p.moskwa.bootcampCoreServices.dataModel.TranslateSongFieldsName.translateFieldNameToPl;
import static p.moskwa.bootcampCoreServices.gui.MainWindow.getMainWindowInstance;

public class SideBar extends InterfaceClear {
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
        clearView(sideBar);

        JButton confirmErrorButton = new JButton("ok, mam to");
        confirmErrorButton.addActionListener(action -> getMainWindowInstance().displaySongs());
        sideBar.add(confirmErrorButton);
    }

    public void displaySongDetails(Song song) {
        clearView(sideBar);

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
            addVoice.addActionListener(action -> sideBarServices.addVoice(song, this));
            songDetails.add(addVoice);
            JButton resetVotes = new JButton("Wyzeruj głosy");
            resetVotes.addActionListener(action -> sideBarServices.resetVotes(song, this));
            songDetails.add(resetVotes);

            sideBar.add(songDetails);
        }
        getMainWindowInstance().revalidate();
    }

    public void clearSidebar(){
        clearView(sideBar);
    }

    public void displayNewSongForm() {
        clearView(sideBar);

        JPanel newSongForm = new JPanel();
        newSongForm.setBackground(Color.LIGHT_GRAY);
        newSongForm.setLayout(new GridLayout(0, 1));
        newSongForm.setPreferredSize(new Dimension(250, 350));
        newSongForm.add(new JLabel("Dodaj piosenkę"));

        for (Field field : SongDAO.class.getFields()) {
            JComponent jField;
            JLabel jLabel = new JLabel(translateFieldNameToPl(field.getName()));

            if (field.getName().equals("category"))
                jField = new JComboBox(Categories.values());
            else
                jField = new JFormattedTextField();

            jField.setName(field.getName());
            newSongForm.add(jLabel);
            newSongForm.add(jField);
        }

        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(action -> sideBarServices.addSongToList(newSongForm));
        newSongForm.add(addButton);

        sideBar.add(newSongForm);
        getMainWindowInstance().revalidate();
    }
}