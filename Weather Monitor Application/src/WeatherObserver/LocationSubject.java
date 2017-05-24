package WeatherObserver;

import UIInterpreter.MonitorAdapter;
import WeatherServiceAPI.MelbourneWeatherGrabber;
import WeatherServiceAPI.MelbourneWeatherTimeLapseGrabber;

import java.util.concurrent.ConcurrentHashMap;

/**
 * WeatherObserver.LocationSubject.java
 * A concrete subject class that stores all of the observers and notify them if needed
 * Author: Yifei (Freya) Gao, Yun Hao (Jack) Zhang
 */
public class LocationSubject extends Subject {


    private ConcurrentHashMap<String, Observer> observerHashMap; //Share it with WeatherGrabbers, it tells weather liveFeedGrabber what locations to grab
    private String temperature; //This is a temporary variable, should not be only used by observer when notifyObserver method is called
    private String rainfall; //This is a temporary variable, should only be only used by observer when notifyObserver method is called
    private String temperatureTimeStamp; //This is a temporary variable, should only be only used by observer when notifyObserver method is called
    private String rainfallTimeStamp;//This is a temporary variable, should only be only used by observer when notifyObserver method is called
    private String locationID; //This is a temporary variable, should only be only used by observer when notifyObserver method is called

    private MelbourneWeatherGrabber liveFeedGrabber; //It provides all functionality to grab Melbourne weather
    private MelbourneWeatherTimeLapseGrabber timeLapseGrabber;//It provides all functionality to grab Melbourne weather timeLapse service

    /**
     * A init function that starts the main controller of the observer pattern
     */
    public LocationSubject() throws Exception{
        observerHashMap = new ConcurrentHashMap<>();
        try {
            liveFeedGrabber = new MelbourneWeatherGrabber(this);
            timeLapseGrabber = new MelbourneWeatherTimeLapseGrabber(this);
            new Thread(liveFeedGrabber).start(); //Start a new thread to constantly feed in weather information
            new Thread(timeLapseGrabber).start();//Start a new thread to constantly feed in weather information
        } catch (Exception ex) {
            throw new Exception("Error: WeatherObserver.LocationSubject initialisation fail");
        }

    }

    /**
     * A getter for observerHashMap, mainly used by WeatherServiceAPI.MelbourneWeatherGrabber and WeatherServiceAPI.MelbourneWeatherTimeLapseGrabber to see which locationID to fetch
     *
     * @return A concurrentHashMap which stores all location WeatherObserver.Observer in LocationID -> Location Object
     */
    public ConcurrentHashMap<String, Observer> getObserverHashMap() {
        return observerHashMap;
    }

    /**
     * Add a observer to the notify list
     *
     * @param observer a observer it could be locationObserver
     */
    @Override
    public void attach(Observer observer) {
        LocationObserver locationObserver = (LocationObserver) observer;
        observerHashMap.put(locationObserver.getID(), observer);
    }

    /**
     * Remove a observer from the notify list
     *
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
     * Get all of the location string from a specified source
     *
     * @return all of location string in String array
     * @throws Exception Weather Service is unavailable, particularly get locations method
     */
    public String[] getLocations(String sourceIdentifier) throws Exception {
        switch (sourceIdentifier){
            case MelbourneWeatherGrabber.SOURCE_IDENTIFIER:
                return liveFeedGrabber.grabLocations();
            case MelbourneWeatherTimeLapseGrabber.SOURCE_IDENTIFIER:
                return timeLapseGrabber.grabLocations();
            default:
                throw new Exception("Error: Cannot identify the source");
        }
    }


    /**
     * Check if a locationID exists in the observerHashMap
     *
     * @param locationID A String that is made of location string + source identifier
     * @return true, It's inside false, It's not inside
     */
    public boolean locationExist(String locationID) {
        return observerHashMap.containsKey(locationID);
    }

    /**
     * Update the weather info on a particular observer
     *
     * @param locationID  A String that represents the name of the locationID
     * @param rainfallTimeStamp A String that represents when the rainfall is grabbed
     * @param temperatureTimeStamp A String that represents when the temperature is grabbed
     * @param temperature A String that represents temperature, The String could be null or a double
     * @param rainfall    A String that represents rainfall, The String could be null or a double
     */
    public void updateWeather(String locationID,String temperature,String temperatureTimeStamp, String rainfall,String rainfallTimeStamp) {
        if (!locationExist(locationID)) {
            throw new NullPointerException("Location does not exist in the Array");
        }
        //Set the updated info on temporary instance variable for the observer to get
        this.locationID = locationID;
        this.temperature = temperature;
        this.rainfall = rainfall;
        this.temperatureTimeStamp = temperatureTimeStamp;
        this.rainfallTimeStamp = rainfallTimeStamp;
        notifyObserver();//Tell the WeatherObserver.Observer to grab it
    }

    /**
     * A getter for rainfall that should only used by notified WeatherObserver.Observer
     *
     * @return A String that represents the rainfall
     */
    protected String getRainfall() {
        return rainfall;
    }

    /**
     * A getter for temperature that should only used by notified WeatherObserver.Observer
     *
     * @return A String that represents the temperature
     */
    protected String getTemperature() {
        return temperature;
    }

    /**
     * A getter for temperature timestamp that should only used by notified WeatherObserver.Observer
     *
     * @return A String that represents the temperatureTimeStamp
     */
    protected String getTemperatureTimeStamp() {
        return temperatureTimeStamp;
    }

    /**
     * A getter for rainfall timestamp that should only used by notified WeatherObserver.Observer
     *
     * @return A String that represents the rainfallTimeStamp
     */
    protected String getRainfallTimeStamp() {
        return rainfallTimeStamp;
    }

    /**
     * A convenient method to create a new locationID observer
     *
     * @param location A String that represents the name of the locationID
     * @return A observer wrap around WeatherObserver.LocationObserver
     * @throws Exception Cannot create a locationObserver
     */
    public Observer newLocationObserver(String location, String sourceIdentifier, MonitorAdapter monitorAdapter) throws Exception {
        LocationObserver locationObserver;
        switch (sourceIdentifier) {
            case MelbourneWeatherGrabber.SOURCE_IDENTIFIER:
                String[] rainfall = liveFeedGrabber.grabRainFall(location);
                String[] temperature = liveFeedGrabber.grabTemperature(location);
                locationObserver = new LocationObserver(this, location, temperature[1], temperature[0], rainfall[1],rainfall[0], sourceIdentifier, monitorAdapter);
                break;
            case MelbourneWeatherTimeLapseGrabber.SOURCE_IDENTIFIER:
                String[] weatherInfo = timeLapseGrabber.grabWeather(location);
                locationObserver = new LocationObserver(this, location, weatherInfo[1],weatherInfo[0], weatherInfo[2],weatherInfo[0], sourceIdentifier, monitorAdapter);
                break;
            default:
                throw new Exception("Error: Cannot identify the source");
        }
        monitorAdapter.setLocationObserver(locationObserver);
        return locationObserver;


    }

    /**
     * A convenient method to add monitor adapter to a existing locationObserver
     * @param locationID Key for retrieving the locationObserver
     * @param monitorAdapter The monitorAdapter that will be added into the locationObserver
     */
    public void addMonitorAdapter(String locationID, MonitorAdapter monitorAdapter) {
        if (!locationExist(locationID)) {
            throw new NullPointerException("Location does not exist in the Array");
        }
        LocationObserver locationObserver = (LocationObserver) this.observerHashMap.get(locationID);
        monitorAdapter.setLocationObserver(locationObserver);
        locationObserver.addMonitorAdapter(monitorAdapter);
    }

}
