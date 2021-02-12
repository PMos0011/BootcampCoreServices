package p.moskwa.bootcampcoreservices.gui;

import org.junit.jupiter.api.*;
import p.moskwa.bootcampcoreservices.datamodel.Categories;
import p.moskwa.bootcampcoreservices.datamodel.Song;
import p.moskwa.bootcampcoreservices.datamodel.SongDAO;
import p.moskwa.bootcampcoreservices.gui.services.SideBarServices;

import javax.swing.*;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SideBarTest {

    private static final String TITLE = "fak yea";
    private static final String AUTHOR = "blahblah";
    private static final String ALBUM = "blah";
    private static final String VOTES = "100";

    static MainWindow mw;
    static JPanel content;
    static JPanel sideBar;
    static SongDAO songToAdd;

    @BeforeAll
    static void beforeAllSetUp() {
        songToAdd = new SongDAO(TITLE, AUTHOR, ALBUM, Categories.ALTERNATIVE.getCategory(), VOTES);

        mw = new MainWindow();
        content = mw.getMainContentPanel().getContent();
        sideBar = mw.getSideBar().getSideBarContent();
    }

    @Test
    void displayNewSongForm() {
        mw.getSideBar().displayNewSongForm();
        int componentsCount = SongDAO.class.getDeclaredFields().length * 2 + 2;
        JPanel newSongPanel = (JPanel) sideBar.getComponent(0);
        assertEquals(componentsCount, newSongPanel.getComponentCount());
    }

    @Test
    @Order(1)
    void addNewSong() {
        JPanel newSongForm = fillNewSongForm();
        SideBarServices sideBarServices = new SideBarServices(new MockMessageDialog());
        sideBarServices.addSongToList(newSongForm);

        JPanel songPanel = getContentPanel(content);
        int componentCount = 2;

        assertEquals(componentCount, songPanel.getComponentCount());
        assertEquals(0, sideBar.getComponentCount());
    }

    @Test
    @Order(2)
    void displaySongDetails() {
        Song song = mw.getSongService().getSortedSongList()
                .get(Categories.ALTERNATIVE.name()).get(AUTHOR)
                .get(0);

        mw.getSideBar().displaySongDetails(song);
        JPanel songDetailPanel = (JPanel) sideBar.getComponent(0);
        JLabel albumLabel = (JLabel) songDetailPanel.getComponent(2);
        JLabel votesLabel = (JLabel) songDetailPanel.getComponent(4);
        assertEquals(7, songDetailPanel.getComponentCount());
        assertEquals("Album: " + ALBUM, albumLabel.getText());
        assertEquals("Głosy: " + VOTES, votesLabel.getText());
    }

    private JPanel fillNewSongForm() {
        JPanel newSongForm = new JPanel();
        for (Field field : songToAdd.getClass().getDeclaredFields()) {
            JComponent jField;
            try {
                if (field.getName().equals("category"))
                    jField = new JComboBox(Categories.values());
                else
                    jField = new JFormattedTextField(field.get(songToAdd));

                jField.setName(field.getName());
                newSongForm.add(jField);
            } catch (IllegalAccessException ignored) {
            }
        }
        return newSongForm;
    }

    private JPanel getContentPanel(JPanel content) {
        JScrollPane scrollPane = (JScrollPane) content.getComponent(0);
        JViewport vPort = (JViewport) scrollPane.getComponent(0);
        return (JPanel) vPort.getComponent(0);
    }
}
