/**
 * Created by Jack on 18/5/17.
 */
public interface MonitorAdapter {
    void displayTemperature(String temperature);

    void displayRainFall(String rainFall);

    void disposeMyself();

    void displayLastUpdated(String timeStamp);

    void setLocationObserver(LocationObserver locationObserver);
}
