import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * Created by Jack on 24-May-17.
 */
public abstract class MonitorFrame extends JFrame{
    private MonitorAdapter monitorAdapter;
    public  MonitorFrame(String title, MonitorAdapter monitorAdapter, String location){
        super(title);
        this.monitorAdapter = monitorAdapter;
        initComponents();
    }

    protected void initComponents(){

    }
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
