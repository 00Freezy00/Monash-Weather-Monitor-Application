import javax.swing.*;


/**
 * Created by Freya on 30/04/2017.
 */
public class WeatherContent extends JFrame{
    private JLabel locationLabel;
    private JLabel tempLabel;
    private JLabel rainLabel;
    public JPanel mainPanel;
    private JLabel lastUpdated;
    private JLabel tempDisplayLabel;
    private JLabel rainfallDisplayLabel;
    private LocationSubject locationSubject;
    private String location;

    public WeatherContent(LocationSubject locationSubject, String location){
        this.locationSubject = locationSubject;
        this.location = location;
        locationLabel.setText(location);
    }

    public void setTemperatureLabel(String temp) {
        tempLabel.setText(temp);
    }

    public void setRainfallLabel(String rainfall) {
        rainLabel.setText(rainfall);
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated.setText(lastUpdated);
    }

    public void disableRainData(){
        mainPanel.remove(rainLabel);
        mainPanel.remove(rainfallDisplayLabel);
    }

    public void disableTemperatureData(){
        mainPanel.remove(tempLabel);
        mainPanel.remove(tempDisplayLabel);
    }
}
