import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/*
 * Created by JFormDesigner on Thu May 18 13:24:10 EST 2017
 */


/**
 * An init function for MainFrame. Disables the display weather button by default at first, and adds
 * listeners to GUI elements.
// * @param locationSubject A locationSubject class that is a subject which will inform the observer class.
 */
public class MainFrame extends JFrame implements ActionListener, ListSelectionListener, ItemListener {

	private LocationSubject locationSubject;
	private String[] selectedLocationList;
	private boolean[] weatherDisplaySelection = {false,false};//temp,rainfall

	public MainFrame(LocationSubject locationSubject) {
		initComponents();
		this.locationSubject = locationSubject;
		displayLiveButton.setEnabled(false);
		loadLocationsButton.addActionListener(this);
		locationList.addListSelectionListener(this);
		tempCheck.addItemListener(this);
		rainCheck.addItemListener(this);
		displayLiveButton.addActionListener(this);
		displayChangeButton.addActionListener(this);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		scrollPane = new JScrollPane();
		locationList = new JList<>();
		loadLocationsButton = new JButton();
		tempCheck = new JCheckBox();
		rainCheck = new JCheckBox();
		displayLiveButton = new JButton();
		promptLabel = new JLabel();
		displayChangeButton = new JButton();

		//======== this ========
		setTitle("Melbourne Weather Application");
		Container contentPane = getContentPane();

		//======== scrollPane ========
		{
			scrollPane.setViewportView(locationList);
		}

		//---- loadLocationsButton ----
		loadLocationsButton.setText("Load Locations");

		//---- tempCheck ----
		tempCheck.setText("Temperature");

		//---- rainCheck ----
		rainCheck.setText("Rainfall");

		//---- displayLiveButton ----
		displayLiveButton.setText("Display Live Information");

		//---- promptLabel ----
		promptLabel.setText("<HTML>Display:</HTML>");

		//---- displayChangeButton ----
		displayChangeButton.setText("Display Change Over Time");

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(38, 38, 38)
							.addComponent(loadLocationsButton))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(displayLiveButton))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(displayChangeButton)))
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(20, 20, 20)
							.addGroup(contentPaneLayout.createParallelGroup()
								.addComponent(rainCheck)
								.addComponent(tempCheck)))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(promptLabel, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
					.addGap(0, 220, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(loadLocationsButton)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(promptLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(tempCheck)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(rainCheck)
					.addGap(18, 18, 18)
					.addComponent(displayLiveButton)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(displayChangeButton)
					.addContainerGap(11, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane scrollPane;
	private JList<String> locationList;
	private JButton loadLocationsButton;
	private JCheckBox tempCheck;
	private JCheckBox rainCheck;
	private JButton displayLiveButton;
	private JLabel promptLabel;
	private JButton displayChangeButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	/**
	 * The main function as the entry point of the application. Creates the MainFrame GUI window
	 * and displays it.
	 * @param args
	 * @throws Exception Creating new JFrame failed
	 */
	public static void main(String[] args) throws Exception {

		LocationSubject locationSubject = new LocationSubject();
		try {
			JFrame mainFrame = new MainFrame(locationSubject);
			mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			mainFrame.pack();
			mainFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadLocationsButton){
			try{
				String[] locationList = this.locationSubject.getLocations();//Load locations
				DefaultListModel<String> model = new DefaultListModel<>();
				for (String location: locationList){
					model.addElement(location);
				}
				this.locationList.setModel(model);
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Load location failed","Web Service Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == displayLiveButton) {
			if (!weatherDisplaySelection[0] & !weatherDisplaySelection[1]){
				JOptionPane.showMessageDialog(this, "Select one or more information to display","Selection Error",JOptionPane.ERROR_MESSAGE);
			}else{
				for (String location: selectedLocationList){
					try {
						String locationID = location + MelbourneWeatherGrabber.SOURCE_IDENTIFIER;
						MonitorAdapter monitorAdapter = new NormalDisplay(weatherDisplaySelection, location);
						if (!this.locationSubject.locationExist(locationID)){
							this.locationSubject.attach(this.locationSubject.newLocationObserver(location,MelbourneWeatherGrabber.SOURCE_IDENTIFIER,monitorAdapter));
						}else{
							this.locationSubject.addMonitorAdapter(locationID,monitorAdapter);
						}
					}catch (Exception ex){
						JOptionPane.showMessageDialog(this, "Initialise Location: " +location +" failed!!","Selection Error",JOptionPane.ERROR_MESSAGE);
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
			if (!locationList.getSelectedValuesList().isEmpty()) {
				selectedLocationList = locationList.getSelectedValuesList().toArray(new String[locationList.getSelectedValuesList().size()]);
				displayLiveButton.setEnabled(true);
				displayChangeButton.setEnabled(true);
			} else {
				displayLiveButton.setEnabled(false);
				displayChangeButton.setEnabled(false);
			}
		}
	}

	/**
	 * Detects if check boxes for temperature and rainfall are ticked or not.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItemSelectable() == tempCheck){
			if(e.getStateChange() == ItemEvent.DESELECTED){
				weatherDisplaySelection[0] = false;
			}else{
				weatherDisplaySelection[0] = true;
			}

		}else if (e.getItemSelectable() == rainCheck){
			if(e.getStateChange() == ItemEvent.DESELECTED){
				weatherDisplaySelection[1] = false;
			}else{
				weatherDisplaySelection[1] = true;
			}
		}
	}

}
