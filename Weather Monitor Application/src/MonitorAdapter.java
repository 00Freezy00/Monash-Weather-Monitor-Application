import javax.swing.*;
import java.awt.*;

/**
 * Created by Jack on 30-Apr-17.
 */
public class MonitorAdapter {
    private WeatherContent weatherContent;
    private boolean[] displayMode;

    public MonitorAdapter(boolean[]displayMode,LocationSubject locationSubject,String location){
        this.displayMode = displayMode;
        JFrame weatherFrame = new WeatherFrame("Monitor",locationSubject,location);
        weatherFrame.setBounds(100,100,450,300);
        this.weatherContent = new WeatherContent(locationSubject,location);
        weatherFrame.setContentPane(weatherContent.mainPanel);
        weatherFrame.setPreferredSize(new Dimension(300, 200));
        weatherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        weatherFrame.pack();
        displayOption();
        weatherFrame.setVisible(true);

    }

    public void displayTemperature(String temperature){
        try{
            Double.parseDouble(temperature);
        }
        catch(NumberFormatException ex){
            temperature = "-";
        }
        weatherContent.setTemperatureLabel(temperature);
    }

    public void displayRainFall(String rainFall){
        try{
            Double.parseDouble(rainFall);
        }
        catch(NumberFormatException ex){
            rainFall = "-";
        }
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
