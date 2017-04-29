import java.util.HashMap;

/**
 * Created by Jack on 28-Apr-17.
 */
public class LocationSubject implements Subject {

    private HashMap<String, Observer> observerArrayList;


    private double temperature;
    private double rainfall;
    private String timeStamp;
    private String location;

    public LocationSubject() {
        observerArrayList = new HashMap<>();
    }

    @Override
    public void attach(Observer observer) {
        LocationObserver locationObserver = (LocationObserver) observer;
        observerArrayList.put(locationObserver.getLocation(), observer);
    }

    @Override
    public void detach(Observer observer) {
        LocationObserver locationObserver = (LocationObserver) observer;
        observerArrayList.remove(locationObserver.getLocation());
    }

    @Override
    public void notifyObserver() {
        Observer observer = observerArrayList.get(this.location);
        observer.update();
    }

    public boolean locationExist(String location) {
        return observerArrayList.containsKey(location);
    }

    public void updateWeather(String location,String timeStamp, double temperature, double rainfall) {
        if (!locationExist(location)) {
            throw new NullPointerException("Location does not exist in the Array");
        }
        this.location = location;
        this.temperature = temperature;
        this.rainfall = rainfall;
        this.timeStamp = timeStamp;
        notifyObserver();
    }

    public double getRainfall() {
        return rainfall;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

}
