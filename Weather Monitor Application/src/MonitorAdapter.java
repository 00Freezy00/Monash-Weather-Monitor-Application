import javax.swing.*;

/**
 * Created by Jack on 30-Apr-17.
 */
public class MonitorAdapter {
    private WeatherContent weatherContent;
    private boolean[] displayMode;

    public MonitorAdapter(boolean[]displayMode,LocationSubject locationSubject,String location){
        this.displayMode = displayMode;
        JFrame WeatherFrame = new WeatherFrame("Monitor",locationSubject,location);
        this.weatherContent = new WeatherContent(locationSubject,location);
        WeatherFrame.setContentPane(weatherContent.mainPanel);
        WeatherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        WeatherFrame.pack();
        displayOption();
        WeatherFrame.setVisible(true);

    }

    public void displayTemperature(String temperature){
        weatherContent.setTemperatureLabel(temperature);
    }

    public void displayRainFall(String rainFall){
        weatherContent.setRainfallLabel(rainFall);
    }

    public void displayLastUpdated(String timeStamp){
        weatherContent.setLastUpdated(timeStamp);
    }

    private void displayOption(){
        if (!displayMode[0]){
            weatherContent.disableTemperatureData();
        }
        if (!displayMode[1]){
            weatherContent.disableRainData();
        }
    }
}
