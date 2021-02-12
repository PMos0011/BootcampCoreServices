package p.moskwa.bootcampcoreservices.gui;

import org.junit.jupiter.api.*;
import p.moskwa.bootcampcoreservices.datamodel.Categories;
import p.moskwa.bootcampcoreservices.datamodel.SongDAO;
import p.moskwa.bootcampcoreservices.gui.services.ReportMenuServices;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainContentPanelTest {

    static MainWindow mw;
    static JPanel content;
    static JPanel sideBar;
    static List<SongDAO> songsFromFile1;

    @BeforeAll
    static void beforeAllSetUp() {
        songsFromFile1 = new ArrayList<>() {{
            add(new SongDAO("tit1", "r_auth1", "album", Categories.RAP.getCategory(), "5"));
            add(new SongDAO("tit2", "r_auth1", "album", Categories.RAP.getCategory(), "20"));
            add(new SongDAO("tit3", "r_auth2", "album", Categories.RAP.getCategory(), "80"));
            add(new SongDAO("tit4", "r_auth2", "album", Categories.RAP.getCategory(), "8"));
            add(new SongDAO("tit5", "r_auth3", "album", Categories.RAP.getCategory(), "16"));
            add(new SongDAO("tit6", "r_auth2", "album", Categories.RAP.getCategory(), "5"));
            add(new SongDAO("tit7", "r_auth3", "album", Categories.RAP.getCategory(), "21"));
        }};

        mw = new MainWindow();
        content = mw.getMainContentPanel().getContent();
        sideBar = mw.getSideBar().getSideBarContent();
        mw.getSongService().updateSongList(songsFromFile1);
    }

    @Test
    void displayInvalidSongs() {
        List<SongDAO> removedSongList1 = new ArrayList<>() {{
            add(new SongDAO("t1", "au1", "al1", "c", "0"));
        }};
        List<SongDAO> removedSongList2 = new ArrayList<>() {{
            add(new SongDAO("t2", "au2", "al2", "c", "0"));
        }};

        HashMap<String, List<SongDAO>> errorList = new HashMap<>();
        errorList.put("sample/file1", removedSongList1);
        errorList.put("sample/file2", removedSongList2);

        int componentCount = errorList.size() * 3;
        componentCount += removedSongList1.size() + 1;
        componentCount += removedSongList2.size() + 1;

        mw.getMainContentPanel().displayInvalidSongs(errorList);
        JPanel errorContent = getContentPanel(content);

        assertEquals(componentCount, errorContent.getComponentCount());
        assertEquals(1, sideBar.getComponentCount());
    }

    @Test
    void displaySongs() {
        mw.displaySongs();
        JPanel songPanel = getContentPanel(content);
        int componentCount = songsFromFile1.size() + 1;

        assertEquals(componentCount, songPanel.getComponentCount());
        assertEquals(0, sideBar.getComponentCount());
    }

    @Test
    void displayReport() {
        ReportMenuServices reportMenuServices = new ReportMenuServices();

        JMenuItem allSongsReport = new JMenuItem("Wszystkie piosenki");
        ActionEvent action = new ActionEvent(allSongsReport, 1, null);

        JRadioButton allRadioB = new JRadioButton("Pełen ranking");
        allRadioB.setSelected(true);
        ButtonGroup reportButtonsGroup = new ButtonGroup();
        reportButtonsGroup.add(allRadioB);

        reportMenuServices.createReport(action, reportButtonsGroup);
        JPanel reportPanel = getContentPanel(content);

        int componentCount = songsFromFile1.size() * 4 + 4;
        assertEquals(componentCount, reportPanel.getComponentCount());
        assertEquals(0, sideBar.getComponentCount());
    }

    private JPanel getContentPanel(JPanel content) {
        JScrollPane scrollPane = (JScrollPane) content.getComponent(0);
        JViewport vPort = (JViewport) scrollPane.getComponent(0);
        return (JPanel) vPort.getComponent(0);
    }

}