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
	private int sourceSelection = 0;	// 0 for MelbourneWeather2, 1 for MelbourneTimeLapse
	private boolean[] weatherDisplaySelection = {false,false};//temp,rainfall

	public MainFrame(LocationSubject locationSubject) {
		initComponents();
		this.locationSubject = locationSubject;
		displayLiveButton.setEnabled(false);
		loadLocationsButton.addActionListener(this);
		locationList.addListSelectionListener(this);
		weather2Radio.addActionListener(this);
		weatherTimeLapseRadio.addActionListener(this);
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
		displayChangeButton = new JButton();
		sourceLabel = new JLabel();
		weather2Radio = new JRadioButton();
		weatherTimeLapseRadio = new JRadioButton();
		displayLabel = new JLabel();

		//======== this ========
		setTitle("Melbourne Weather Application");
		setMinimumSize(new Dimension(450, 310));
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

		//---- displayChangeButton ----
		displayChangeButton.setText("Display Change Over Time");

		//---- sourceLabel ----
		sourceLabel.setText("Source:");

		//---- weather2Radio ----
		weather2Radio.setText("MelbourneWeather2");
		weather2Radio.setSelected(true);

		//---- weatherTimeLapseRadio ----
		weatherTimeLapseRadio.setText("MelbourneWeatherTimeLapse");

		//---- displayLabel ----
		displayLabel.setText("Display:");

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
				contentPaneLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(contentPaneLayout.createParallelGroup()
										.addComponent(sourceLabel)
										.addComponent(displayLabel)
										.addGroup(contentPaneLayout.createSequentialGroup()
												.addGap(10, 10, 10)
												.addGroup(contentPaneLayout.createParallelGroup()
														.addComponent(weatherTimeLapseRadio)
														.addComponent(weather2Radio)
														.addComponent(tempCheck)
														.addComponent(rainCheck)
														.addGroup(contentPaneLayout.createSequentialGroup()
																.addGap(21, 21, 21)
																.addComponent(loadLocationsButton))))
										.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createParallelGroup()
												.addComponent(displayLiveButton)
												.addComponent(displayChangeButton)))
								.addGap(18, 18, 18)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
				contentPaneLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(sourceLabel)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(weather2Radio)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(weatherTimeLapseRadio)
								.addGap(14, 14, 14)
								.addComponent(loadLocationsButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(displayLabel)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(tempCheck)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(rainCheck)
								.addGap(18, 18, Short.MAX_VALUE)
								.addComponent(displayLiveButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(displayChangeButton)
								.addGap(16, 16, 16))
						.addComponent(scrollPane)
		);
		pack();
		setLocationRelativeTo(getOwner());

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(weather2Radio);
		buttonGroup1.add(weatherTimeLapseRadio);
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
	private JLabel sourceLabel;
	private JRadioButton weather2Radio;
	private JRadioButton weatherTimeLapseRadio;
	private JLabel displayLabel;
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
		if (e.getSource() == weather2Radio){
			if (sourceSelection != 0) {
				sourceSelection = 0;
				DefaultListModel<String> model = new DefaultListModel<>();
				this.locationList.setModel(model);
			}
		} else if (e.getSource() == weatherTimeLapseRadio){
			if (sourceSelection != 1) {
				sourceSelection = 1;
				DefaultListModel<String> model = new DefaultListModel<>();
				this.locationList.setModel(model);
			}
		} else if (e.getSource() == loadLocationsButton){
			try{
				if (sourceSelection == 0) {
					// Load MelbourneWeather2 locations
					String[] locationList = this.locationSubject.getLocations();		// TODO: add source identifier
					DefaultListModel<String> model = new DefaultListModel<>();
					for (String location: locationList){
						model.addElement(location);
					}
					this.locationList.setModel(model);
				} else if (sourceSelection == 1) {
					// Load MelbourneWeatherTimeLapse locations
					String[] locationList = this.locationSubject.getLocations();		// TODO: add source identifier
					DefaultListModel<String> model = new DefaultListModel<>();
					for (String location: locationList){
						model.addElement(location);
					}
					this.locationList.setModel(model);
				}

				String[] locationList = this.locationSubject.getLocations();//Load locations
				DefaultListModel<String> model = new DefaultListModel<>();
				for (String location: locationList){
					model.addElement(location);
				}
				this.locationList.setModel(model);
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Load location failed","Web Service Error",JOptionPane.ERROR_MESSAGE);
			}
		} else if(e.getSource() == displayLiveButton) {
			if (!weatherDisplaySelection[0] & !weatherDisplaySelection[1]){
				JOptionPane.showMessageDialog(this, "Select one or more information to display","Selection Error",JOptionPane.ERROR_MESSAGE);
			} else {
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
						JOptionPane.showMessageDialog(this, "Initialise Location: " +location +" failed.","Selection Error",JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			}
		} else if (e.getSource() == displayChangeButton) {
			if (!weatherDisplaySelection[0] & !weatherDisplaySelection[1]) {
				JOptionPane.showMessageDialog(this, "Select one or more information to display", "Selection Error", JOptionPane.ERROR_MESSAGE);

			} else {
				for (String location: selectedLocationList) {
					try {
						// TODO: create monitors
						JOptionPane.showMessageDialog(this, "Will show live change over time...","Live change over time",JOptionPane.PLAIN_MESSAGE);
					} catch (Exception ex){
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
