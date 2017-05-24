package UI;

import UIInterpreter.MonitorAdapter;
import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * An abstract class for the GUI class
 * Author: Yifei (Freya) Gao, Yun Hao (Jack) Zhang
 */
public abstract class MonitorFrame extends JFrame {
    private MonitorAdapter monitorAdapter;

    protected MonitorFrame(String title, MonitorAdapter monitorAdapter) {
        super(title);
        this.monitorAdapter = monitorAdapter;
        initComponents();
    }

    /**
     * This method store all of the configuration of the GUI
     */
    abstract void initComponents();

    /**
     * This method handles all window event
     * @param e Windows event
     */
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            try {
                monitorAdapter.disposeMyself();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            dispose();
        }
    }


}
