import javax.swing.*;
import java.awt.*;

/**
 * Created by Jack on 30-Apr-17.
 */
public class MonitorAdapter {
    private WeatherFrame weatherFrame;
    private boolean[] displayMode;

    public MonitorAdapter(boolean[]displayMode,LocationSubject locationSubject,String location){
        this.displayMode = displayMode;
        weatherFrame = new WeatherFrame("Monitor",locationSubject,location);
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
        weatherFrame.setTemperatureLabel(temperature+"°C");
    }

    public void displayRainFall(String rainFall){
        try{
            Double.parseDouble(rainFall);
        }
        catch(NumberFormatException ex){
            rainFall = "-";
        }
        weatherFrame.setRainfallLabel(rainFall+"mm");
    }

    public void displayLastUpdated(String timeStamp){
        weatherFrame.setLastUpdated(timeStamp);
    }

    private void displayOption(){
        if (!displayMode[0]){
            weatherFrame.disableTemperatureData();
        }
        if (!displayMode[1]){
            weatherFrame.disableRainData();
        }
    }
}
