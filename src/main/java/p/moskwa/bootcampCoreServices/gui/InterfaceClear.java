package p.moskwa.bootcampCoreServices.gui;

import javax.swing.*;

public abstract class InterfaceClear {

    protected void clearView(JComponent component){
        component.removeAll();
        component.repaint();
    }
}
