package p.moskwa.bootcampCoreServices.gui;

import p.moskwa.bootcampCoreServices.eventHandlers.MenuEventHandlers;

import javax.swing.*;

public class MenuBar {

    private final JMenuBar menu;
    private final FileChooser fileChooser;
    private final MenuEventHandlers menuEventHandlers;

    public MenuBar() {
        fileChooser = new FileChooser();
        menuEventHandlers = new MenuEventHandlers();

        menu = menuConstructor();
    }

    public JMenuBar getMenu() {
        return menu;
    }

    private JMenuBar menuConstructor() {
        JMenuBar menu = new JMenuBar();
        menu.add(fileMenuConstructor());
        menu.add(new JMenu("Raporty"));

        return menu;
    }

    private JMenu fileMenuConstructor() {

        JMenuItem openFile = new JMenuItem("Otwórz plik");
        openFile.addActionListener(action -> menuEventHandlers.openFileAction(fileChooser.getFileChooser()));

        JMenuItem openDirectory = new JMenuItem("Otwórz folder");
        openDirectory.addActionListener(action -> menuEventHandlers.openDirectoryAction(fileChooser.getFileChooser()));

        JMenuItem exit = new JMenuItem("Zamknij");
        exit.addActionListener(action -> menuEventHandlers.exitApplication());

        JMenu fileMenu = new JMenu("Plik");
        fileMenu.add(openFile);
        fileMenu.add(openDirectory);
        fileMenu.add(exit);

        return fileMenu;
    }
}