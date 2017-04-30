import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * Created by Freya on 27/04/2017.
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

    public static void main(String[] args) throws Exception {

        LocationSubject locationSubject = new LocationSubject();
        try {
            JFrame mainFrame = new JFrame("Weather Monitor Application");
            mainFrame.setContentPane(new MainContent(locationSubject).mainPanel);
            mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            mainFrame.pack();
            mainFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
                            MonitorAdapter monitorAdapter = new MonitorAdapter(weatherDisplaySelection, locationSubject, location);
                            this.locationSubject.attach(this.locationSubject.newLocationObserver(location, monitorAdapter));
                        }catch (Exception ex){
                            JOptionPane.showMessageDialog(mainPanel, "Initialise Location: " +location +" failed!!","Selection Error",JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                }
        }
        }

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
