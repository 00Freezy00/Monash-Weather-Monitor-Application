import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Freya on 27/04/2017.
 */
public class MainFrame{
    public JPanel mainPanel;    // TODO: check later
    private JButton fetchLocationListBtn;
    private JCheckBox showTemperatureCheckBox;
    private JCheckBox showRainfallCheckBox;
    private JList locationListView;
    private JButton displayBtn;
    // private ArrayList<Location> locationList;

    public MainFrame(){

    }

    public static void main(String[] args){
        JFrame mainFrame = new JFrame("Weather Monitor Application");
        mainFrame.setContentPane(new MainFrame().mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
