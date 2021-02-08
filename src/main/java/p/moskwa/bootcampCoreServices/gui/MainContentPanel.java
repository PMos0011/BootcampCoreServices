package p.moskwa.bootcampCoreServices.gui;

import javax.swing.*;

public class MainContentPanel {

    private JPanel content;

    public MainContentPanel() {
        content = new JPanel();
        content.add(new JLabel("zawartość"));
    }

    public JPanel getContent() {
        return content;
    }
}
