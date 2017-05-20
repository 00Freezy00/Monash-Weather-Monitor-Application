/**
 * Created by Jack on 18/5/17.
 */
public interface MonitorAdapter {
    void displayTemperature(String temperature,String temperatureTimeStamp);

    void displayRainFall(String rainFall, String rainFallTimeStamp);

    void disposeMyself();

    void setLocationObserver(LocationObserver locationObserver);
}
