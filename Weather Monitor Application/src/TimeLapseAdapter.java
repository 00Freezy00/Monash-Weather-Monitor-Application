import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Freya on 19/05/2017.
 */
public class TimeLapseAdapter implements MonitorAdapter {
    private WeatherTimeLapseFrame weatherTimeLapseFrame;
    private boolean[] displayMode;

    private LocationObserver locationObserver;

    public TimeLapseAdapter(boolean[]displayMode, String location, String source) {
        this.displayMode = displayMode;
        weatherTimeLapseFrame = new WeatherTimeLapseFrame(source + " Time Lapse", this, location);

        weatherTimeLapseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        displayOption();
        weatherTimeLapseFrame.pack();
        weatherTimeLapseFrame.setVisible(true);
    }

    @Override
    public void displayTemperature(String temperature) {

    }

    @Override
    public void displayRainFall(String rainFall) {

    }

    @Override
    public void disposeMyself() {
        this.locationObserver.removeMonitorAdapter(this);
    }

    @Override
    public void displayLastUpdated(String timeStamp){
        displayRetrievalTime();
//        weatherTimeLapseFrame.setRainTimestampLabel(timeStamp);
//        weatherTimeLapseFrame.setTempTimestampLabel(timeStamp);
    }

    private void displayRetrievalTime() {
        Date date = new Date();
        DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        weatherTimeLapseFrame.setLastUpdated(DateFormat.format(date));
    }

    @Override
    public void setLocationObserver(LocationObserver locationObserver) {
        this.locationObserver = locationObserver;
    }

    

}
