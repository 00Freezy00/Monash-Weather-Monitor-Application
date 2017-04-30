import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


/**
 * Created by Freya on 27/04/2017.
 */
public class MainFrame implements ActionListener, ListSelectionListener,ItemListener{
    public JPanel mainPanel;
    private JButton fetchLocationListBtn;
    private JCheckBox showTemperatureCheckBox;
    private JCheckBox showRainfallCheckBox;
    private JList<String> locationListView;
    private JButton displayBtn;
    private JScrollPane scrollPane;
    private LocationSubject locationSubject;
    private String[] selectedList;
    private boolean[] weatherDisplaySelection = {false,false};

    public MainFrame(LocationSubject locationSubject){
       this.locationSubject = locationSubject;
       displayBtn.setEnabled(false);
       fetchLocationListBtn.addActionListener(this);
       locationListView.addListSelectionListener(this);
       showTemperatureCheckBox.addItemListener(this);
       showRainfallCheckBox.addItemListener(this);
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
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()){
            if (!locationListView.getSelectedValuesList().isEmpty()){
                selectedList = locationListView.getSelectedValuesList().toArray(new String[locationListView.getSelectedValuesList().size()]);
                displayBtn.setEnabled(true);
            }else{
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
