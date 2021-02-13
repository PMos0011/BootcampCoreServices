package p.moskwa.bootcampcoreservices.gui;

import javax.swing.*;

/**
 * Message dialogs behavior
 *
 * @since 1.0
 */
public interface MessageDialog {
    /**
     * Shows message dialog with user-specified type
     *
     * @param title   message dialog title as {@link String}
     * @param message message as {@link String}
     * @param type    message dialog type as int
     * @see JOptionPane
     */
    void showMessage(String title, String message, int type);

    /**
     * Shows confirm dialog
     *
     * @param title   confirm dialog title as {@link String}
     * @param message message as {@link String}
     * @return selected option as int
     * @see JOptionPane
     */
    int showConfirmDialog(String title, String message);
}
