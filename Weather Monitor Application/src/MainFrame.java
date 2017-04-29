import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by Freya on 27/04/2017.
 */
public class MainFrame extends JFrame{
    private JFrame frame = new JFrame("WeatherApp");
    private JList locationListView;
    private DefaultListModel model;
    public JPanel mainPanel;    // TODO: check later
    private JButton fetchLocationListBtn;
    private JCheckBox showTemperatureCheckBox;
    private JCheckBox showRainfallCheckBox;
    private JTextArea infoText;
    private JButton displayBtn;
    // private ArrayList<Location> locationList;

    public MainFrame(){
        System.out.print("ok");
        initialise();
        fetchLocationListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve list of locations and display them in locationListView.
                // TODO:
                // JList needs a model to load values
                DefaultListModel model = new DefaultListModel();
                model.addElement("Jane Doe");
                locationListView.setModel(model);
            }
        });

        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new window to display information of selected location.
            }
        });
    }

//    public static void main(String[] args) {
//
//    }

    public void initialise() {
        // Creating and opening the main frame
        // JFrame frame = new JFrame("WeatherApp");    // Define JFrame
        frame.setContentPane(new MainFrame().mainPanel);     // Call panel inside frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
