import javax.swing.*;
import java.awt.*;

/**
 * Created by Jack on 30-Apr-17.
 */
public class NormalDisplay implements MonitorAdapter {
    private WeatherFrame weatherFrame;
    private boolean[] displayMode;



    private LocationObserver locationObserver;

    public NormalDisplay(boolean[]displayMode,String location){
        this.displayMode = displayMode;
        weatherFrame = new WeatherFrame("Monitor",this,location);

        weatherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayOption();
        weatherFrame.pack();
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

    public void disposeMyself(){
        this.locationObserver.removeMonitorAdapter(this);
    }

    public void setLocationObserver(LocationObserver locationObserver) {
        this.locationObserver = locationObserver;
    }

}
