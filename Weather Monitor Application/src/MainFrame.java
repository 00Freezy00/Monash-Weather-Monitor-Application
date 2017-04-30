import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Created by Freya on 27/04/2017.
 */
public class MainFrame{
    public JPanel mainPanel;    // TODO: check later
    private JButton fetchLocationListBtn;
    private JCheckBox showTemperatureCheckBox;
    private JCheckBox showRainfallCheckBox;
    private JList<String> locationListView;
    private JButton displayBtn;
    private JScrollPane scrollPane;
    private ArrayList<String> locationList;

    private DefaultListModel<String> model;

    public MainFrame() {
        // Initialise GUI elements
//        locationListView = new JList<>();
        scrollPane = new JScrollPane(locationListView);

        // List components
        model = new DefaultListModel<>();
        this.locationList = new ArrayList<String>();
        locationList.add("Melbourne");
        locationList.add("Clayton");
        locationList.add("Boxhill");
        model = new DefaultListModel();
        locationListView = new JList(model);
        for (int i=0; i<locationList.size();i++) {
            model.addElement(locationList.get(i));
        }
        locationListView.setModel(model);
//
        fetchLocationListBtn = new JButton();
        fetchLocationListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Creating and opening another window to display info
                WeatherFrame weatherDisplay = new WeatherFrame();
                // JFrame weatherDisplay = new JFrame("Melbourne");
                weatherDisplay.setTemperatureLabel("77.7");
                // weatherDisplay.setContentPane(new WeatherFrame().mainPanel);
                weatherDisplay.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                weatherDisplay.pack();
                weatherDisplay.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
