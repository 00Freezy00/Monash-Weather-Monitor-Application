import org.jibx.binding.Run;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jack on 28-Apr-17.
 */
public class LocationSubject extends Subject {


    private ConcurrentHashMap<String, Observer> observerHashMap;


    private String temperature;
    private String rainfall;
    private String timeStamp;
    private String location;
    private WeatherGrabber grabber;

    public LocationSubject() {
        observerHashMap = new ConcurrentHashMap<>();

        try {
            grabber = new WeatherGrabber(this);
            new Thread(grabber).start();
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    public ConcurrentHashMap<String, Observer> getObserverHashMap() {
        return observerHashMap;
    }

    @Override
    public void attach(Observer observer) {
        LocationObserver locationObserver = (LocationObserver) observer;
        observerHashMap.put(locationObserver.getLocation(), observer);
    }

    @Override
    public void detach(Observer observer) {
        LocationObserver locationObserver = (LocationObserver) observer;
        observerHashMap.remove(locationObserver.getLocation());
    }

    @Override
    public void notifyObserver() {
        Observer observer = observerHashMap.get(this.location);
        observer.update();
    }

    public String[] getLocations() throws Exception {
        return grabber.grabLocations();
    }

    public Observer newLocationObserver(String location) throws Exception {
        return grabber.newLocationObserver(location);
    }

    public boolean locationExist(String location) {
        return observerHashMap.containsKey(location);
    }

    public void updateWeather(String location, String timeStamp, String temperature, String rainfall) {
        if (!locationExist(location)) {
            throw new NullPointerException("Location does not exist in the Array");
        }
        this.location = location;
        this.temperature = temperature;
        this.rainfall = rainfall;
        this.timeStamp = timeStamp;
        notifyObserver();
    }

    public String getRainfall() {
        return rainfall;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

}
