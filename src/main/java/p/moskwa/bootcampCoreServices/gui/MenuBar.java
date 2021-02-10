package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.dataModel.Categories;
import p.moskwa.bootcampCoreServices.gui.services.FileMenuServices;
import p.moskwa.bootcampCoreServices.gui.services.ReportMenuServices;
import p.moskwa.bootcampCoreServices.gui.services.SongMenuServices;

import javax.swing.*;

public class MenuBar extends FileChooser {
    private final JMenuBar menu;
    private final FileMenuServices fileMenuServices;
    private final SongMenuServices songMenuServices;
    private final ReportMenuServices reportMenuServices;

    public MenuBar() {
        fileMenuServices = new FileMenuServices();
        songMenuServices = new SongMenuServices();
        reportMenuServices = new ReportMenuServices();
        menu = menuConstructor();
    }

    public JMenuBar getMenu() {
        return menu;
    }

    private JMenuBar menuConstructor() {
        JMenuBar menu = new JMenuBar();
        menu.add(fileMenuConstructor());
        menu.add(songMenuConstructor());
        menu.add(reportMenuConstructor());

        return menu;
    }

    private JMenu fileMenuConstructor() {
        JMenuItem openFile = new JMenuItem("Otwórz plik");
        openFile.addActionListener(action -> fileMenuServices.openFileAction(getFileChooser()));

        JMenuItem openDirectory = new JMenuItem("Otwórz folder");
        openDirectory.addActionListener(action -> fileMenuServices.openDirectoryAction(getFileChooser()));

        JMenuItem exit = new JMenuItem("Zamknij");
        exit.addActionListener(action -> fileMenuServices.exitApplication());

        JMenu fileMenu = new JMenu("Plik");
        fileMenu.add(openFile);
        fileMenu.add(openDirectory);
        fileMenu.add(exit);

        return fileMenu;
    }

    private JMenu songMenuConstructor() {
        JMenu songMenu = new JMenu("Piosenki");

        JMenuItem addSong = new JMenuItem("Dodaj piosenkę");
        addSong.addActionListener(action -> songMenuServices.displayAddNewSongForm());
        songMenu.add(addSong);
        JMenuItem resetVotes = new JMenuItem("Wyzeruj głosy");
        resetVotes.addActionListener(action -> songMenuServices.resetAllSongsVotes());
        songMenu.add(resetVotes);
        JMenuItem showSongs = new JMenuItem("Wyświetl piosenki");
        showSongs.addActionListener(action -> songMenuServices.showAllSongs());
        songMenu.add(showSongs);

        return songMenu;
    }

    private JMenu reportMenuConstructor() {
        JMenu reportMenu = new JMenu("Raporty");

        JRadioButton top3RadioB = new JRadioButton("Top 3");
        top3RadioB.setActionCommand("3");
        top3RadioB.setSelected(true);
        JRadioButton top10RadioB = new JRadioButton("Top 10");
        top10RadioB.setActionCommand("10");
        JRadioButton allRadioB = new JRadioButton("Pełen ranking");

        ButtonGroup reportButtonsGroup = new ButtonGroup();
        reportButtonsGroup.add(top3RadioB);
        reportButtonsGroup.add(top10RadioB);
        reportButtonsGroup.add(allRadioB);

        reportMenu.add(top3RadioB);
        reportMenu.add(top10RadioB);
        reportMenu.add(allRadioB);
        reportMenu.add(new JSeparator());

        JMenuItem allSongsReport = new JMenuItem("Wszystkie piosenki");
        allSongsReport.addActionListener(action -> reportMenuServices.createReport(action, reportButtonsGroup));
        JMenu categoryReport = new JMenu("Raport kategorii");
        for (Categories category : Categories.values()) {
            JMenuItem categoryItem = new JMenuItem(category.getCategory());
            categoryItem.setName(category.name());
            categoryItem.addActionListener(action -> reportMenuServices.createReport(action, reportButtonsGroup));
            categoryReport.add(categoryItem);
        }

        reportMenu.add(allSongsReport);
        reportMenu.add(categoryReport);

        return reportMenu;
    }
}