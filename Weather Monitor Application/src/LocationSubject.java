import java.util.concurrent.ConcurrentHashMap;

/**
 * LocationSubject.java
 * A concrete subject class that stores all of the observers and notify them if needed
 * Author: Yi Fei (Freya) Gao, Yun Hao (Jack) Zhang
 */
public class LocationSubject extends Subject {


    private ConcurrentHashMap<String, Observer> observerHashMap; //Share it with WeatherGrabber, it tells weather liveFeedGrabber what locations to grab
    private String temperature; //This is a temporary variable, should not be only used by observer when notifyObserver method is called
    private String rainfall; //This is a temporary variable, should only be only used by observer when notifyObserver method is called
    private String timeStamp; //This is a temporary variable, should only be only used by observer when notifyObserver method is called
    private String locationID; //This is a temporary variable, should only be only used by observer when notifyObserver method is called
    private MelbourneWeatherGrabber liveFeedGrabber; //It provides all functionality to grab Melbourne weather
    private MelbourneWeatherTimeLapseGrabber timeLapseGrabber;

    /**
     * A init function that starts the main controller of the observer pattern
     */
    public LocationSubject() {
        observerHashMap = new ConcurrentHashMap<>();
        try {
            liveFeedGrabber = new MelbourneWeatherGrabber(this);
            timeLapseGrabber = new MelbourneWeatherTimeLapseGrabber(this);
            new Thread(liveFeedGrabber).start(); //Start a new thread to constantly feed in weather information
            new Thread(timeLapseGrabber).start();
        } catch (Exception ex) {
            System.out.print(ex);
            //TODO: Should tell the GUI initialisation failed
        }

    }

    /**
     * A getter for observerHashMap, mainly used by MelbourneWeatherGrabber to see which locationID to fetch
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
        observerHashMap.put(locationObserver.getID(), observer);
    }

    /**
     * Remove a observer from the notify list
     * @param observer a observer it could be locationObserver
     */
    @Override
    public void detach(Observer observer) {
        LocationObserver locationObserver = (LocationObserver) observer;
        observerHashMap.remove(locationObserver.getID());
    }

    /**
     * Notify the target observer by invoke the update function on the observer
     */
    @Override
    public void notifyObserver() {
        Observer observer = observerHashMap.get(this.locationID);
        observer.update();
    }

    /**
     * Get all of the locationID available
     * @return all of locationID in String array
     * @throws Exception Weather Service is unavailable, particularly locationID
     */
    public String[] getLocations(String sourceIdenitfier) throws Exception {
        if (sourceIdenitfier.equals(MelbourneWeatherGrabber.SOURCE_IDENTIFIER)){
            return liveFeedGrabber.grabLocations();
        }else if (sourceIdenitfier.equals(MelbourneWeatherTimeLapseGrabber.SOURCE_IDENTIFIER)){
            return timeLapseGrabber.grabLocations();
        }

    }


    /**
     * Check if a locationID exists in the observerHashMap
     * @param locationID A String that represents the locationID
     * @return true, It's inside false, It's not inside
     */
    public boolean locationExist(String locationID) {
        return observerHashMap.containsKey(locationID);
    }

    /**
     * Update the weather info on a particular observer
     * @param locationID A String that represents the name of the locationID
     * @param timeStamp A String that represents the information's retrieve date
     * @param temperature A String that represents temperature, The String could be null or a double
     * @param rainfall A String that represents rainfall, The String could be null or a double
     */
    public void updateWeather(String locationID, String timeStamp, String temperature, String rainfall) {
        if (!locationExist(locationID)) {
            throw new NullPointerException("Location does not exist in the Array");
        }
        //Set the updated info on temporary variable for the observer to get
        this.locationID = locationID;
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

    /**
     * Create a new locationID observer
     * @param location A String that represents the name of the locationID
     * @return A observer wrap around LocationObserver
     * @throws Exception
     */
    public Observer newLocationObserver(String location,String sourceIdentifier,MonitorAdapter monitorAdapter) throws Exception {
        LocationObserver locationObserver;
        if (sourceIdentifier.equals(MelbourneWeatherGrabber.SOURCE_IDENTIFIER)){
            String[] rainfall = liveFeedGrabber.grabRainFall(location);
            String[] temperature = liveFeedGrabber.grabTemperature(location);
            locationObserver= new LocationObserver(this, location, temperature[0], temperature[1], rainfall[1],sourceIdentifier,monitorAdapter);
            monitorAdapter.setLocationObserver(locationObserver);
            return locationObserver;
        }else if (sourceIdentifier.equals(MelbourneWeatherTimeLapseGrabber.SOURCE_IDENTIFIER)){
            String[] weatherInfo = timeLapseGrabber.grabWeather(location);
            locationObserver = new LocationObserver(this, location, weatherInfo[0], weatherInfo[1], weatherInfo[2],sourceIdentifier,monitorAdapter);
            monitorAdapter.setLocationObserver(locationObserver);
            return locationObserver;
        }else{
            throw new Exception("What source identifier did you just pass it to me??");
        }
    }
    //TODO: put in ID instead of the locationID string

    public void addMonitorAdapter(String locationID,MonitorAdapter monitorAdapter){
        if (!locationExist(locationID)) {
            throw new NullPointerException("Location does not exist in the Array");
        }
        LocationObserver locationObserver = (LocationObserver) this.observerHashMap.get(locationID);
        locationObserver.addMonitorAdapter(monitorAdapter);
    }

}
