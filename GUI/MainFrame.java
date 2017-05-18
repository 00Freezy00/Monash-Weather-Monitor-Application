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
	private JList locationList;
	private JButton loadLocationsButton;
	private JCheckBox tempCheck;
	private JCheckBox rainCheck;
	private JButton displayLiveButton;
	private JLabel promptLabel;
	private JButton displayChangeButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
