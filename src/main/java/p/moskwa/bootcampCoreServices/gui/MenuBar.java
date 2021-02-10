package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.dataModel.Categories;
import p.moskwa.bootcampCoreServices.gui.services.FileMenuServices;
import p.moskwa.bootcampCoreServices.gui.services.SongMenuServices;

import javax.swing.*;

public class MenuBar extends FileChooser {
    private final JMenuBar menu;
    private final FileMenuServices fileMenuServices;
    private final SongMenuServices songMenuServices;

    public MenuBar() {
        fileMenuServices = new FileMenuServices();
        songMenuServices = new SongMenuServices();
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

        return songMenu;
    }

    private JMenu reportMenuConstructor() {
        JMenu reportMenu = new JMenu("Raporty");

        JMenuItem allSongsReport = new JMenuItem("Wszystkie piosenki");
        JMenu categoryReport = new JMenu("Raport kategorii");

        for (Categories category : Categories.values()) {
            JMenuItem categoryItem = new JMenuItem(category.getCategory());
            categoryItem.setName(category.name());
            categoryReport.add(categoryItem);
        }

        reportMenu.add(allSongsReport);
        reportMenu.add(categoryReport);
        reportMenu.add(categoryReport);

        return reportMenu;
    }
}