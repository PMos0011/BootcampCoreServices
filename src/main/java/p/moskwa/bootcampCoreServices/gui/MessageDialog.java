package p.moskwa.bootcampCoreServices.gui;

import javax.swing.*;

public class MessageDialog {
    public static void showMessage(String title, String message, int type){
        JOptionPane.showMessageDialog(null,
                message,
                title,
                type);
    }

    public static int showConfirmDialog(String title, String message){
        return JOptionPane.showConfirmDialog(null,
                message,
                title,
                JOptionPane.YES_NO_OPTION);
    }
}