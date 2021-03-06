package p.moskwa.bootcampcoreservices.gui;

import p.moskwa.bootcampcoreservices.datamodel.Categories;
import p.moskwa.bootcampcoreservices.datamodel.Song;
import p.moskwa.bootcampcoreservices.datamodel.SongDAO;
import p.moskwa.bootcampcoreservices.gui.services.SideBarServices;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridLayout;
import java.lang.reflect.Field;

import static p.moskwa.bootcampcoreservices.gui.MainWindow.getMainWindowInstance;

/**
 * Application side bar container
 *
 * @since 1.0
 */
public class SideBar extends InterfaceClear {
    private final JPanel sideBar;
    private final SideBarServices sideBarServices;

    public SideBar() {
        sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(300, 768));
        sideBar.setBackground(Color.LIGHT_GRAY);

        sideBarServices = new SideBarServices(new DefaultMessageDialog());
    }

    /**
     * Returns side bar container
     *
     * @return content as {@link JPanel}
     */
    public JPanel getSideBarContent() {
        return sideBar;
    }

    /**
     * Creates and displays error confirmation content
     */
    public void displayErrorConfirmButton() {
        clearView(sideBar);

        JButton confirmErrorButton = new JButton("ok, mam to");
        confirmErrorButton.addActionListener(action -> getMainWindowInstance().displaySongs());
        sideBar.add(confirmErrorButton);
    }

    /**
     * Creates and displays song detail content
     *
     * @param song selected song
     */
    public void displaySongDetails(Song song) {
        clearView(sideBar);

        if (song != null) {
            JPanel songDetails = new JPanel();
            songDetails.setBackground(Color.LIGHT_GRAY);
            songDetails.setLayout(new GridLayout(0, 1));

            song.getSongDetailsInPolish(song)
                    .forEach(detail -> songDetails.add(new JLabel(detail)));

            JButton addVote = new JButton("Zagłosuj");
            addVote.addActionListener(action -> sideBarServices.addVote(song, this));
            songDetails.add(addVote);
            JButton resetVotes = new JButton("Wyzeruj głosy");
            resetVotes.addActionListener(action -> sideBarServices.resetVotes(song, this));
            songDetails.add(resetVotes);

            sideBar.add(songDetails);
        }
        getMainWindowInstance().revalidate();
    }

    /**
     * Creates and displays new song form
     */
    public void displayNewSongForm() {
        clearView(sideBar);

        JPanel newSongForm = new JPanel();
        newSongForm.setBackground(Color.LIGHT_GRAY);
        newSongForm.setLayout(new GridLayout(0, 1));
        newSongForm.setPreferredSize(new Dimension(250, 350));
        newSongForm.add(new JLabel("Dodaj piosenkę"));


        for (Field field : SongDAO.class.getFields()) {
            JComponent jField;
            JLabel jLabel = new JLabel(SongDAO.translateFieldNameToPolish(field.getName()));

            if (field.getName().equals("category")) {
                jField = new JComboBox(Categories.values());
            } else {
                jField = new JFormattedTextField();
            }

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

    /**
     * Removes all content from side bar container
     */
    public void clearSidebar() {
        clearView(sideBar);
    }
}
