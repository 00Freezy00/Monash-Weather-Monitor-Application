import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by Jack on 30-Apr-17.
 */
public class LiveFeedAdapter implements MonitorAdapter {
    private WeatherFrame weatherFrame;
    private boolean[] displayMode;

    private LocationObserver locationObserver;

    public LiveFeedAdapter(boolean[]displayMode, String location, String source){
        this.displayMode = displayMode;
        weatherFrame = new WeatherFrame(source + " Live Feed",this,location);

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
        weatherFrame.setRainfallLabel(rainFall+" mm");
    }

    public void displayLastUpdated(String timeStamp){
        displayRetrievalTime();
        weatherFrame.setRainTimestampLabel(timeStamp);
        weatherFrame.setTempTimestampLabel(timeStamp);
    }

    public void displayRetrievalTime(){
        Date date = new Date();
        DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        weatherFrame.setLastUpdated(DateFormat.format(date));
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
