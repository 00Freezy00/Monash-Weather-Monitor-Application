import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
/*
 * Created by JFormDesigner on Thu May 18 17:06:10 EST 2017
 */


public class WeatherFrame extends MonitorFrame {

    private MonitorAdapter monitorAdapter;

    public WeatherFrame(String title, MonitorAdapter monitorAdapter, String location) {
        super(title,monitorAdapter,location);
        this.locationLabel.setText(location);
    }

    protected void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tempPanel = new JPanel();
        tempLabel = new JLabel();
        label2 = new JLabel();
        tempTimestampLabel = new JLabel();
        locationLabel = new JLabel();
        rainPanel = new JPanel();
        rainLabel = new JLabel();
        label8 = new JLabel();
        rainTimestampLabel = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(400, 180));
        Container contentPane = getContentPane();

        //======== tempPanel ========
        {
            tempPanel.setBorder(new TitledBorder("Temperature"));
            tempPanel.setFont(new Font("Arial", Font.PLAIN, 12));

            //---- tempLabel ----
            tempLabel.setText("-.-\u00b0C");
            tempLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            tempLabel.setHorizontalAlignment(SwingConstants.CENTER);

            //---- label2 ----
            label2.setText("Last updated:");
            label2.setFont(new Font("Arial", Font.PLAIN, 12));

            //---- tempTimestampLabel ----
            tempTimestampLabel.setText("31/01/2000 11:59:59");
            tempTimestampLabel.setHorizontalTextPosition(SwingConstants.LEADING);
            tempTimestampLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            GroupLayout tempPanelLayout = new GroupLayout(tempPanel);
            tempPanel.setLayout(tempPanelLayout);
            tempPanelLayout.setHorizontalGroup(
                    tempPanelLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, tempPanelLayout.createSequentialGroup()
                                    .addGap(68, 68, 68)
                                    .addComponent(tempLabel, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addGap(64, 64, 64))
                            .addGroup(GroupLayout.Alignment.TRAILING, tempPanelLayout.createSequentialGroup()
                                    .addComponent(label2)
                                    .addGap(18, 18, 18)
                                    .addComponent(tempTimestampLabel, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            tempPanelLayout.setVerticalGroup(
                    tempPanelLayout.createParallelGroup()
                            .addGroup(tempPanelLayout.createSequentialGroup()
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tempLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                    .addGap(14, 14, 14)
                                    .addGroup(tempPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label2)
                                            .addComponent(tempTimestampLabel))
                                    .addGap(21, 21, 21))
            );
        }

        //---- locationLabel ----
        locationLabel.setText("Location");
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        //======== rainPanel ========
        {
            rainPanel.setBorder(new TitledBorder("Rainfall"));
            rainPanel.setFont(new Font("Arial", Font.PLAIN, 12));

            //---- rainLabel ----
            rainLabel.setText("-.- mm");
            rainLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            rainLabel.setHorizontalAlignment(SwingConstants.CENTER);

            //---- label8 ----
            label8.setText("Last updated:");
            label8.setFont(new Font("Arial", Font.PLAIN, 12));

            //---- rainTimestampLabel ----
            rainTimestampLabel.setText("31/01/2000 11:59:59");
            rainTimestampLabel.setHorizontalTextPosition(SwingConstants.LEADING);
            rainTimestampLabel.setFont(new Font("Arial", Font.PLAIN, 12));

            GroupLayout rainPanelLayout = new GroupLayout(rainPanel);
            rainPanel.setLayout(rainPanelLayout);
            rainPanelLayout.setHorizontalGroup(
                    rainPanelLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, rainPanelLayout.createSequentialGroup()
                                    .addGap(68, 68, 68)
                                    .addComponent(rainLabel, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addGap(64, 64, 64))
                            .addGroup(GroupLayout.Alignment.TRAILING, rainPanelLayout.createSequentialGroup()
                                    .addComponent(label8)
                                    .addGap(18, 18, 18)
                                    .addComponent(rainTimestampLabel, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                    .addContainerGap())
            );
            rainPanelLayout.setVerticalGroup(
                    rainPanelLayout.createParallelGroup()
                            .addGroup(rainPanelLayout.createSequentialGroup()
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rainLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                    .addGap(14, 14, 14)
                                    .addGroup(rainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label8)
                                            .addComponent(rainTimestampLabel))
                                    .addGap(21, 21, 21))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(tempPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(rainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(locationLabel, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 14, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(locationLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tempPanel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rainPanel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel tempPanel;
    private JLabel tempLabel;
    private JLabel label2;
    private JLabel tempTimestampLabel;
    private JLabel locationLabel;
    private JPanel rainPanel;
    private JLabel rainLabel;
    private JLabel label8;
    private JLabel rainTimestampLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    
    // Setters for GUI labels

    public void setTemperatureLabel(String temp) {
        tempLabel.setText(temp);
    }

    public void setRainfallLabel(String rainfall) {
        rainLabel.setText(rainfall);
    }

    public void setTempTimestampLabel(String tempTimestamp) {
        this.tempTimestampLabel.setText(tempTimestamp);
    }

    public void setRainTimestampLabel(String rainTimestamp) {
        this.rainTimestampLabel.setText(rainTimestamp);
    }


    // Functions to disable unticked data.
    public void disableTemperatureData() {
        this.remove(tempPanel);
    }

    public void disableRainData() {
        this.remove(rainPanel);
    }
}
