import javax.swing.*;

/**
 * Created by Freya on 30/04/2017.
 */
public class WeatherFrame extends JFrame {
    private JLabel locationLabel;
    private JLabel tempLabel;
    private JLabel rainLabel;
    public JPanel mainPanel;
    private JLabel tempUpdated;
    private JLabel rainUpdated;

    public WeatherFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setContentPane(mainPanel);
        locationLabel.setText("Melbourne");
    }

    public void setLocationLabel(String location) {
        locationLabel.setText(location);
    }

    public void setTemperatureLabel(String temp) {
        tempLabel.setText(temp + "C");
    }

    public void setRainfallLabel(String rainfall) {
        rainLabel.setText(rainfall + "mm");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
