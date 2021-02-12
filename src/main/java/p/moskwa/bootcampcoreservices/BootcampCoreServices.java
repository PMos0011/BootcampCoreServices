package p.moskwa.bootcampcoreservices;

import p.moskwa.bootcampcoreservices.gui.MainWindow;

/**
 * The BootcampCoreServices program implements an application that
 * processes .xml and .csv files containing song data
 * and displays result via Swing Graphical User Interface.
 *
 * @author  Paweł Moskwa
 * @version 1.0
 */
public class BootcampCoreServices {

    public static void main(String[] args) {
        System.out.println("Starting");
        new MainWindow().showWindow();
    }
}
