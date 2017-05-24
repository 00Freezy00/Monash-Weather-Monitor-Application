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
		graphPanel = new JPanel();

		//======== this ========
		setMinimumSize(new Dimension(500, 330));
		Container contentPane = getContentPane();

		//======== graphPanel ========
		{
			graphPanel.setBorder(new TitledBorder("Time Lapse Graph"));
			graphPanel.setFont(new Font("Arial", Font.PLAIN, 12));
			graphPanel.setLayout(new BorderLayout());
		}

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(graphPanel, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(graphPanel, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
					.addContainerGap())
		);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel graphPanel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
