package p.moskwa.bootcampcoreservices.gui;

import javax.swing.JOptionPane;

/**
 * Application default message dialogs
 *
 * @since 1.0
 */
public class DefaultMessageDialog implements MessageDialog {
    /**
     * Shows message dialog with user-specified type
     *
     * @param title   message dialog title as {@link String}
     * @param message message as {@link String}
     * @param type    message dialog type as int
     * @see JOptionPane
     */
    @Override
    public void showMessage(String title, String message, int type) {
        JOptionPane.showMessageDialog(null,
                message,
                title,
                type);
    }

    /**
     * Shows confirm dialog
     *
     * @param title   confirm dialog title as {@link String}
     * @param message message as {@link String}
     * @return selected option as int
     * @see JOptionPane
     */
    @Override
    public int showConfirmDialog(String title, String message) {
        return JOptionPane.showConfirmDialog(null,
                message,
                title,
                JOptionPane.YES_NO_OPTION);
    }
}
