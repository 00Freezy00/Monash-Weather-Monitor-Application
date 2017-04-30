import javax.swing.*;


/**
 * WeatherContent.java
 * A GUI class that displays the temperature and/or rainfall of the selected location.
 * Author: Yifei (Freya) Gao, Yun Hao (Jack) Zhang
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

    /**
     * An init function to link each window with its location subject.
     */
    public WeatherContent(LocationSubject locationSubject, String location){
        this.locationSubject = locationSubject;
        this.location = location;
        locationLabel.setText(location);
    }

    // Setters for GUI labels

    public void setTemperatureLabel(String temp) {
        tempLabel.setText(temp);
    }

    public void setRainfallLabel(String rainfall) {
        rainLabel.setText(rainfall);
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated.setText(lastUpdated);
    }

    // Functions to disable unticked data.

    public void disableRainData(){
        mainPanel.remove(rainLabel);
        mainPanel.remove(rainfallDisplayLabel);
    }

    public void disableTemperatureData(){
        mainPanel.remove(tempLabel);
        mainPanel.remove(tempDisplayLabel);
    }
}
