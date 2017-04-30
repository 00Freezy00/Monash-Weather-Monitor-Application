import java.util.concurrent.ConcurrentHashMap;

/**
 * LocationSubject.java
 * A concrete subject class that stores all of the observers and notify them if needed
 * Author: Yi Fei (Freya) Gao, Yun Hao (Jack) Zhang
 */
public class LocationSubject extends Subject {


    private ConcurrentHashMap<String, Observer> observerHashMap; //Share it with WeatherGrabber, it tells weather grabber what locations to grab
    private String temperature; //This is a temporary variable, should not be only used by observer when notifyObserver method is called
    private String rainfall; //This is a temporary variable, should only be only used by observer when notifyObserver method is called
    private String timeStamp; //This is a temporary variable, should only be only used by observer when notifyObserver method is called
    private String location; //This is a temporary variable, should only be only used by observer when notifyObserver method is called
    private MelbourneWeatherGrabber grabber; //It provides all functionality to grab Melbourne weather

    /**
     * A init function that starts the main controller of the observer pattern
     */
    public LocationSubject() {
        observerHashMap = new ConcurrentHashMap<>();
        try {
            grabber = new MelbourneWeatherGrabber(this);
            new Thread(grabber).start(); //Start a new thread to constantly feed in weather information
        } catch (Exception ex) {
            System.out.print(ex);
            //TODO: Should tell the GUI initialisation failed
        }

    }

    /**
     * A getter for observerHashMap, mainly used by MelbourneWeatherGrabber to see which location to fetch
     * @return
     */
    public ConcurrentHashMap<String, Observer> getObserverHashMap() {
        return observerHashMap;
    }

    /**
     * Add a observer to the notify list
     * @param observer a observer it could be locationObserver
     */
    @Override
    public void attach(Observer observer) {
        LocationObserver locationObserver = (LocationObserver) observer;
        observerHashMap.put(locationObserver.getLocation(), observer);
    }

    /**
     * Remove a observer from the notify list
     * @param observer a observer it could be locationObserver
     */
    @Override
    public void detach(Observer observer) {
        LocationObserver locationObserver = (LocationObserver) observer;
        observerHashMap.remove(locationObserver.getLocation());
    }

    /**
     * Notify the target observer by invoke the update function on the observer
     */
    @Override
    public void notifyObserver() {
        Observer observer = observerHashMap.get(this.location);
        observer.update();
    }

    /**
     * Get all of the location available
     * @return all of location in String array
     * @throws Exception Weather Service is unavailable, particularly location
     */
    public String[] getLocations() throws Exception {
        return grabber.grabLocations();
    }

    /**
     * A convenient method for GUI to create a Observer
     * @param location A String that represents the location
     * @return A observer wrap around LocationObserver
     * @throws Exception one or more get methods are unavailable
     */
    public Observer newLocationObserver(String location) throws Exception {
        return grabber.newLocationObserver(location);
    }

    /**
     * Check if a location exists in the observerHashMap
     * @param location A String that represents the location
     * @return true, It's inside false, It's not inside
     */
    public boolean locationExist(String location) {
        return observerHashMap.containsKey(location);
    }

    /**
     * Update the weather info on a particular observer
     * @param location A String that represents the name of the location
     * @param timeStamp A String that represents the information's retrieve date
     * @param temperature A String that represents temperature, The String could be null or a double
     * @param rainfall A String that represents rainfall, The String could be null or a double
     */
    public void updateWeather(String location, String timeStamp, String temperature, String rainfall) {
        if (!locationExist(location)) {
            throw new NullPointerException("Location does not exist in the Array");
        }
        //Set the updated info on temporary variable for the observer to get
        this.location = location;
        this.temperature = temperature;
        this.rainfall = rainfall;
        this.timeStamp = timeStamp;
        notifyObserver();//Tell the Observer to grab it
    }

    /**
     * A getter for rainfall that should only used by notified Observer
     * @return A String that represents the rainfall
     */
    public String getRainfall() {
        return rainfall;
    }

    /**
     * A getter for temperature that should only used by notified Observer
     * @return A String that represents the temperature
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * A getter for timestamp that should only used by notified Observer
     * @return A String that represents the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

}
