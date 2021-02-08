package p.moskwa.bootcampCoreServices.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {

        MenuBar menuBar = new MenuBar();
        MainContentPanel mainContentPanel = new MainContentPanel();

        this.setTitle("Bootcamp Core Services P. Moskwa");
        this.setLayout(new BorderLayout());
        this.setSize(1366, 768);

        this.add(menuBar.getMenu(), BorderLayout.NORTH);
        this.add(mainContentPanel.getContent(), BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}