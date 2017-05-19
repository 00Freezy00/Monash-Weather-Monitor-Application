import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
/*
 * Created by JFormDesigner on Thu May 18 13:24:10 EST 2017
 */



/**
 * @author CodeCracker
 */
public class MainFrame extends JFrame  {

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		scrollPane = new JScrollPane();
		locationList = new JList();
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

			//---- locationList ----
			locationList.setFont(new Font("Arial", Font.PLAIN, 12));
			scrollPane.setViewportView(locationList);
		}

		//---- loadLocationsButton ----
		loadLocationsButton.setText("Load Locations");
		loadLocationsButton.setFont(new Font("Arial", Font.PLAIN, 12));

		//---- tempCheck ----
		tempCheck.setText("Temperature");
		tempCheck.setFont(new Font("Arial", Font.PLAIN, 12));

		//---- rainCheck ----
		rainCheck.setText("Rainfall");
		rainCheck.setFont(new Font("Arial", Font.PLAIN, 12));

		//---- displayLiveButton ----
		displayLiveButton.setText("Display Live Information");
		displayLiveButton.setFont(new Font("Arial", Font.PLAIN, 12));

		//---- displayChangeButton ----
		displayChangeButton.setText("Display Change Over Time");
		displayChangeButton.setFont(new Font("Arial", Font.PLAIN, 12));

		//---- sourceLabel ----
		sourceLabel.setText("Source:");
		sourceLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		//---- weather2Radio ----
		weather2Radio.setText("MelbourneWeather2");
		weather2Radio.setSelected(true);
		weather2Radio.setFont(new Font("Arial", Font.PLAIN, 12));

		//---- weatherTimeLapseRadio ----
		weatherTimeLapseRadio.setText("MelbourneWeatherTimeLapse");
		weatherTimeLapseRadio.setFont(new Font("Arial", Font.PLAIN, 12));

		//---- displayLabel ----
		displayLabel.setText("Display:");
		displayLabel.setFont(new Font("Arial", Font.PLAIN, 12));

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
	private JList locationList;
	private JButton loadLocationsButton;
	private JCheckBox tempCheck;
	private JCheckBox rainCheck;
	private JButton displayLiveButton;
	private JButton displayChangeButton;
	private JLabel sourceLabel;
	private JRadioButton weather2Radio;
	private JRadioButton weatherTimeLapseRadio;
	private JLabel displayLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
