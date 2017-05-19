import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Fri May 19 13:31:11 EST 2017
 */



/**
 * @author CodeCracker
 */
public class WeatherTimeLapseFrame extends JFrame {
	public WeatherTimeLapseFrame() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		locationLabel = new JLabel();
		tempPanel = new JPanel();
		retrievedLabel = new JLabel();
		lastUpdatedLabel = new JLabel();

		//======== this ========
		setMinimumSize(new Dimension(500, 330));
		Container contentPane = getContentPane();

		//---- locationLabel ----
		locationLabel.setText("Location");
		locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		locationLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		//======== tempPanel ========
		{
			tempPanel.setBorder(new TitledBorder("Time Lapse Graph"));
			tempPanel.setFont(new Font("Arial", Font.PLAIN, 12));

			GroupLayout tempPanelLayout = new GroupLayout(tempPanel);
			tempPanel.setLayout(tempPanelLayout);
			tempPanelLayout.setHorizontalGroup(
				tempPanelLayout.createParallelGroup()
					.addGap(0, 622, Short.MAX_VALUE)
			);
			tempPanelLayout.setVerticalGroup(
				tempPanelLayout.createParallelGroup()
					.addGap(0, 203, Short.MAX_VALUE)
			);
		}

		//---- retrievedLabel ----
		retrievedLabel.setText("Retrieved:");
		retrievedLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		//---- lastUpdatedLabel ----
		lastUpdatedLabel.setText("31/01/2000 11:59:59");
		lastUpdatedLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		lastUpdatedLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lastUpdatedLabel.setFont(new Font("Arial", Font.PLAIN, 12));

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(140, 140, 140)
					.addComponent(locationLabel, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
					.addGap(143, 143, 143))
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(10, 10, 10)
							.addComponent(retrievedLabel)
							.addGap(18, 18, 18)
							.addComponent(lastUpdatedLabel, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
							.addGap(0, 274, Short.MAX_VALUE))
						.addComponent(tempPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(locationLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(tempPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(3, 3, 3)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addComponent(retrievedLabel)
						.addComponent(lastUpdatedLabel))
					.addContainerGap())
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel locationLabel;
	private JPanel tempPanel;
	private JLabel retrievedLabel;
	private JLabel lastUpdatedLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
