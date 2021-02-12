package p.moskwa.bootcampcoreservices.gui;

/**
 * Message dialogs behavior
 *
 * @since 1.0
 */
public interface MessageDialog {

    void showMessage(String title, String message, int type);

    int showConfirmDialog(String title, String message);
}
