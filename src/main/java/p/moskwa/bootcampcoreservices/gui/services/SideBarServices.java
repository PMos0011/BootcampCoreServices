package p.moskwa.bootcampcoreservices.gui.services;

import p.moskwa.bootcampcoreservices.datamodel.Categories;
import p.moskwa.bootcampcoreservices.datamodel.Song;
import p.moskwa.bootcampcoreservices.datamodel.SongDAO;
import p.moskwa.bootcampcoreservices.gui.MessageDialog;
import p.moskwa.bootcampcoreservices.gui.SideBar;
import p.moskwa.bootcampcoreservices.validator.Validator;

import javax.swing.*;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;

import static javax.swing.JOptionPane.*;
import static p.moskwa.bootcampcoreservices.gui.MainWindow.getMainWindowInstance;

/**
 * Side bar content container services
 *
 * @since 1.0
 */
public class SideBarServices {

    private final MessageDialog messageDialog;

    private final String TITLE = "Dodaj piosenkę";
    private final String WARNING = "Coś jest nie tak z Twoją piosenką. Nie dodam!";
    private final String INFO = "Piosenka dodana! Jak nie wierzysz, sprawdź na liście.";
    private final String CONFIRM = "Piosenka jest już na liście ale jak chcesz to mogę zsumować głosy.";

    public SideBarServices(MessageDialog messageDialog) {
        this.messageDialog = messageDialog;
    }

    /**
     * Adds one vote to song by invoking addVotesToSong method
     *
     * @param song    song to increment votes count
     * @param sideBar calling method side bar
     * @see p.moskwa.bootcampcoreservices.services.SongService
     */
    public void addVote(Song song, SideBar sideBar) {
        getMainWindowInstance().getSongService().addVotesToSong(song, 1);
        sideBar.displaySongDetails(song);
    }

    /**
     * Adds song votes by invoking resetSongVotes method
     *
     * @param song    song to reset votes
     * @param sideBar calling method side bar
     * @see p.moskwa.bootcampcoreservices.services.SongService
     */
    public void resetVotes(Song song, SideBar sideBar) {
        getMainWindowInstance().getSongService().resetSongVotes(song);
        sideBar.displaySongDetails(song);
    }

    /**
     * Validates new song, adds it to song collection by invoking validator methods
     * and updateSongList method
     *
     * @param form input form with new song data
     * @see p.moskwa.bootcampcoreservices.services.SongService
     * @see Validator
     */
    public void addSongToList(JPanel form) {
        SongDAO newSong = createSongDaoFromForm(form);

        Validator validator = new Validator();
        if (validator.isSongDAOInvalid(newSong) || validator.isSongVotesInvalid(newSong))
            messageDialog.showMessage(TITLE, WARNING, WARNING_MESSAGE);
        else {
            if (getMainWindowInstance().getSongService().updateSongList(newSong, false))
                messageDialog.showMessage(TITLE, INFO, INFORMATION_MESSAGE);
            else {
                if (messageDialog.showConfirmDialog(TITLE, CONFIRM) == YES_OPTION)
                    getMainWindowInstance().getSongService().updateSongList(newSong, true);
            }
            getMainWindowInstance().displaySongs();
        }
    }

    private SongDAO createSongDaoFromForm(JPanel form) {
        SongDAO newSong = new SongDAO();

        for (Field field : newSong.getClass().getFields()) {
            Component jComponent = Arrays.stream(form.getComponents())
                    .filter(component -> {
                        if (component.getName() != null)
                            return component.getName().equals(field.getName());
                        return false;
                    })
                    .findFirst()
                    .orElse(null);          //This should never happen

            if (jComponent != null) {
                try {
                    if (jComponent.getName().equals("category")) {
                        JComboBox combo = (JComboBox) jComponent;
                        Categories category = (Categories) combo.getSelectedItem();
                        assert category != null;            //this should never happen
                        field.set(newSong, category.name());
                    } else {
                        JTextField jTextFieldField = (JTextField) jComponent;
                        field.set(newSong, jTextFieldField.getText().trim());
                    }
                } catch (IllegalAccessException ignored) {
                }
            }
        }
        return newSong;
    }
}
