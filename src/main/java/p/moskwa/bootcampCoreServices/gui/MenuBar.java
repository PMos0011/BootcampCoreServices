package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.dataModel.Categories;
import p.moskwa.bootcampCoreServices.services.FileMenuServices;

import javax.swing.*;

public class MenuBar {
    private final JMenuBar menu;
    private final FileChooser fileChooser;
    private final FileMenuServices fileMenuServices;

    public MenuBar() {
        fileChooser = new FileChooser();
        fileMenuServices = new FileMenuServices();

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
        openFile.addActionListener(action -> fileMenuServices.openFileAction(fileChooser.getFileChooser()));

        JMenuItem openDirectory = new JMenuItem("Otwórz folder");
        openDirectory.addActionListener(action -> fileMenuServices.openDirectoryAction(fileChooser.getFileChooser()));

        JMenuItem exit = new JMenuItem("Zamknij");
        exit.addActionListener(action -> fileMenuServices.exitApplication());

        JMenu fileMenu = new JMenu("Plik");
        fileMenu.add(openFile);
        fileMenu.add(openDirectory);
        fileMenu.add(exit);

        return fileMenu;
    }

    private JMenu songMenuConstructor(){
        JMenu songMenu = new JMenu("Piosenki");

        JMenuItem addSong = new JMenuItem("Dodaj piosenkę");
        songMenu.add(addSong);
        JMenuItem clearVotes = new JMenuItem("Wyzeruj głosy");
        songMenu.add(clearVotes);

        return songMenu;
    }

    private JMenu reportMenuConstructor(){
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

        return reportMenu;
    }
}