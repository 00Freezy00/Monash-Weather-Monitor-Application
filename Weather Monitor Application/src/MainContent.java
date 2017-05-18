import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * MainContent.java
 * A GUI class displayed upon starting the program. Allows the user to display locations, and show the temperature
 * and/rainfall of selected location in an newly opened WeatherContent window.
 * Author: Yifei (Freya) Gao, Yun Hao (Jack) Zhang
 */
public class MainContent extends JFrame implements ActionListener, ListSelectionListener,ItemListener{
    public JPanel mainPanel;
    private JButton fetchLocationListBtn;
    private JCheckBox showTemperatureCheckBox;
    private JCheckBox showRainfallCheckBox;
    private JList<String> locationListView;
    private JButton displayBtn;
    private JScrollPane scrollPane;
    private LocationSubject locationSubject;
    private String[] selectedLocationList;
    private boolean[] weatherDisplaySelection = {false,false};//temp,rainfall

    /**
     * The main function as the entry point of the application. Creates the MainContent GUI window
     * and displays it.
     * @param args
     * @throws Exception Creating new JFrame failed
     */
    public static void main(String[] args) throws Exception {

        LocationSubject locationSubject = new LocationSubject();
        try {
            JFrame mainFrame = new JFrame("Weather Monitor Application");
            mainFrame.setContentPane(new MainContent(locationSubject).mainPanel);
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.pack();
            mainFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();//NOTE: It should not happen
        }
    }

    /**
     * An init function for MainContent. Disables the display weather button by default at first, and adds
     * listeners to GUI elements.
     * @param locationSubject A locationSubject class that is a subject which will inform the observer class.
     */
    public MainContent(LocationSubject locationSubject){
       this.locationSubject = locationSubject;
       displayBtn.setEnabled(false);
       fetchLocationListBtn.addActionListener(this);
       locationListView.addListSelectionListener(this);
       showTemperatureCheckBox.addItemListener(this);
       showRainfallCheckBox.addItemListener(this);
       displayBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fetchLocationListBtn){
            try{
                String[] locationList = this.locationSubject.getLocations();//Load locations
                DefaultListModel<String> model = new DefaultListModel<>();
                for (String location: locationList){
                    model.addElement(location);
                }
                locationListView.setModel(model);
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(mainPanel, "Load location failed","Web Service Error",JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == displayBtn) {
                if (!weatherDisplaySelection[0] & !weatherDisplaySelection[1]){
                    JOptionPane.showMessageDialog(mainPanel, "Select one or more information to display","Selection Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    for (String location: selectedLocationList){
                        try {
                            String locationID = location + MelbourneWeatherGrabber.SOURCE_IDENTIFIER;
                            MonitorAdapter monitorAdapter = new MonitorAdapter(weatherDisplaySelection, locationSubject, location);
                            if (!this.locationSubject.locationExist(locationID)){
                                this.locationSubject.attach(this.locationSubject.newLocationObserver(location,MelbourneWeatherGrabber.SOURCE_IDENTIFIER,monitorAdapter));
                            }else{
                                this.locationSubject.addMonitorAdapter(locationID,monitorAdapter);
                            }
                        }catch (Exception ex){
                            JOptionPane.showMessageDialog(mainPanel, "Initialise Location: " +location +" failed!!","Selection Error",JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                }
        }
        }

    /**
     * Enables the display weather button once a location is selected.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (!locationListView.getSelectedValuesList().isEmpty()) {
                selectedLocationList = locationListView.getSelectedValuesList().toArray(new String[locationListView.getSelectedValuesList().size()]);
                displayBtn.setEnabled(true);
            } else {
                displayBtn.setEnabled(false);
            }
        }
    }

    /**
     * Detects if check boxes for temperature and rainfall are ticked or not.
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItemSelectable() == showTemperatureCheckBox){
            if(e.getStateChange() == ItemEvent.DESELECTED){
                weatherDisplaySelection[0] = false;
            }else{
                weatherDisplaySelection[0] = true;
            }

        }else if (e.getItemSelectable() == showRainfallCheckBox){
            if(e.getStateChange() == ItemEvent.DESELECTED){
                weatherDisplaySelection[1] = false;
            }else{
                weatherDisplaySelection[1] = true;
            }
        }
    }
}
