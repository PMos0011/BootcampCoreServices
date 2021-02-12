package p.moskwa.bootcampcoreservices.gui;

import javax.swing.*;

public class MockMessageDialog implements MessageDialog {

    @Override
    public void showMessage(String title, String message, int type) {

    }

    @Override
    public int showConfirmDialog(String title, String message) {
        return JOptionPane.YES_OPTION;
    }
}
