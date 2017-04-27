import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Freya on 27/04/2017.
 */
public class MainFrame {
    private JList locationListView;
    private JPanel mainPanel;
    private JButton fetchLocationListBtn;
    private JCheckBox showTemperatureCheckBox;
    private JCheckBox showRainfallCheckBox;
    private JTextArea infoText;
    private JButton displayBtn;
    // private ArrayList<Location> locationList;

    public void main(String[] args) {

        // Creating and opening the main frame
        JFrame frame = new JFrame("WeatherApp");    // Define JFrame
        frame.setContentPane(new MainFrame().mainPanel);     // Call panel inside frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        fetchLocationListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve list of locations and display them in locationListView.
                // TODO:
            }
        });

        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new window to display information of selecter location.
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
