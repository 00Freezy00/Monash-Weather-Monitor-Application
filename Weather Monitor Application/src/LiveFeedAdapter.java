import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * LiveFeedAdapter.java
 * An adapter class which it translates the data from LocationObserver.java and pass it to the weatherFrame UI
 * Author: Yi Fei (Freya) Gao, Yun Hao (Jack) Zhang
 */
public class LiveFeedAdapter implements MonitorAdapter {
    private WeatherFrame weatherFrame;
    private boolean[] displayMode;
    private LocationObserver locationObserver;

    /**
     * An init function that requires all of the relevant information to construct a LiveFeedAdapter
     * @param displayMode A boolean array which represents user's selection. Index 0 represents disable temperature? Index 1 represents disable rainfall?
     * @param location A string represents the location name
     * @param source A string represents which data source it's coming from
     */
    public LiveFeedAdapter(boolean[] displayMode, String location, String source) {
        this.displayMode = displayMode;
        weatherFrame = new WeatherFrame(source + " Live Feed", this, location);

        weatherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayOption();
        weatherFrame.pack();
        weatherFrame.setVisible(true);
    }

    /**
     * Translate the data from location Observer to a desire form and pass it to weatherFrame
     * @param temperature A String represents the temperature of a location
     */
    public void displayTemperature(String temperature,String temperatureTimeStamp) {
        try {
            Double.parseDouble(temperature);//Checking if it's a double
        } catch (NumberFormatException ex) {
            temperature = "-";
        }
        weatherFrame.setTemperatureLabel(temperature + "°C");
        weatherFrame.setTempTimestampLabel(temperatureTimeStamp);
    }
    /**
     * Translate the data from location Observer to a desire form and pass it to weatherFrame
     * @param rainFall A String represents the rainfall of a location
     */
    public void displayRainFall(String rainFall, String rainFallTimeStamp) {
        try {
            Double.parseDouble(rainFall); //Checking if it's a double
        } catch (NumberFormatException ex) {
            rainFall = "-";
        }
        weatherFrame.setRainfallLabel(rainFall + " mm");
        weatherFrame.setRainTimestampLabel(rainFallTimeStamp);
    }


    /**
     * Choose whether to disable one or more panel/s
     */
    private void displayOption() {
        if (!displayMode[0]) {
            weatherFrame.disableTemperatureData();
        }
        if (!displayMode[1]) {
            weatherFrame.disableRainData();
        }
    }

    /**
     * A method provided to weatherFrame to close the adapter
     */
    public void disposeMyself() {
        this.locationObserver.removeMonitorAdapter(this);
    }

    public void setLocationObserver(LocationObserver locationObserver) {
        this.locationObserver = locationObserver;
    }

}
