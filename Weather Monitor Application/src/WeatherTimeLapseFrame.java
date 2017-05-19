import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
/*
 * Created by JFormDesigner on Fri May 19 13:31:11 EST 2017
 */


public class WeatherTimeLapseFrame extends JFrame {

    private MonitorAdapter monitorAdapter;

    public WeatherTimeLapseFrame(String title, MonitorAdapter monitorAdapter, String location) {
        super(title);
        this.monitorAdapter = monitorAdapter;
        initComponents();
        this.locationLabel.setText(location);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        locationLabel = new JLabel();
        graphPanel = new JPanel();
        retrievedLabel = new JLabel();
        lastUpdatedLabel = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(500, 330));
        Container contentPane = getContentPane();

        //---- locationLabel ----
        locationLabel.setText("Location");
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        //======== graphPanel ========
        {
            graphPanel.setBorder(new TitledBorder("Time Lapse Graph"));
            graphPanel.setFont(new Font("Arial", Font.PLAIN, 12));

            GroupLayout graphPanelLayout = new GroupLayout(graphPanel);
            graphPanel.setLayout(graphPanelLayout);
            graphPanelLayout.setHorizontalGroup(
                    graphPanelLayout.createParallelGroup()
                            .addGap(0, 622, Short.MAX_VALUE)
            );
            graphPanelLayout.setVerticalGroup(
                    graphPanelLayout.createParallelGroup()
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
                                        .addComponent(graphPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(locationLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(graphPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private JPanel graphPanel;
    private JLabel retrievedLabel;
    private JLabel lastUpdatedLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            try {
                monitorAdapter.disposeMyself();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            dispose();
        }
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdatedLabel.setText(lastUpdated);
    }

    public void setGraphPanel(JPanel graph) {
        this.graphPanel = graph;
    }
}
