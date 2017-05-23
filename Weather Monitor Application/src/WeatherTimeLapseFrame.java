import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

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

            GroupLayout graphPanelLayout = new GroupLayout(graphPanel);
            graphPanel.setLayout(graphPanelLayout);
            graphPanelLayout.setHorizontalGroup(
                    graphPanelLayout.createParallelGroup()
                            .addGap(0, 577, Short.MAX_VALUE)
            );
            graphPanelLayout.setVerticalGroup(
                    graphPanelLayout.createParallelGroup()
                            .addGap(0, 305, Short.MAX_VALUE)
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(graphPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(graphPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel graphPanel;
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

    public void setGraphPanel(JFreeChart graph) {
        this.graphPanel.setLayout(new java.awt.BorderLayout());//TODO: Make it border layout in the jfd
        ChartPanel CP = new ChartPanel(graph);
        graphPanel.add(CP,BorderLayout.CENTER);
        graphPanel.validate();
    }
}
