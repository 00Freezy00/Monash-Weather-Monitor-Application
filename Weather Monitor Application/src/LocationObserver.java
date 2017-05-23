import java.util.ArrayList;
import java.util.Iterator;

/**
 * LocationObserver.java
 * A concrete observer class which stores all of the information (location, timestamp, temperature, rainfall) of a location, It should use an adapter to talk to the GUI
 * Author: Yifei (Freya) Gao, Yun Hao (Jack) Zhang
 */
public class LocationObserver implements Observer {

    //NOTE: These data are all string, they need a adapter to translate to a desire form
    private String location;
    private String temperature;
    private String rainfall;
    private String temperatureTimeStamp;
    private String rainfallTimeStamp;
    private LocationSubject locationSubject;
    private ArrayList<MonitorAdapter> monitorAdapterArrayList;
    private String sourceIdentifier;

    /**
     * A init function that requires all of the relevant information to construct the location observer
     *
     * @param locationSubject: A locationSubject class that is a subject which will inform the observer class
     * @param location         A String that represents the name of the location
     * @param temperatureTimeStamp        A String that represents the information's retrieve date
     * @param temperature      A String that represents temperature, The String could be null or a double
     * @param rainfall         A String that represents rainfall, The String could be null or a double
     */
    public LocationObserver(LocationSubject locationSubject, String location, String temperature,String temperatureTimeStamp, String rainfall,String rainfallTimeStamp, String sourceIdentifier, MonitorAdapter monitorAdapter) {
        this.monitorAdapterArrayList = new ArrayList<>();
        this.locationSubject = locationSubject;
        this.location = location;
        this.rainfall = rainfall;
        this.temperature = temperature;
        this.temperatureTimeStamp = temperatureTimeStamp;
        this.rainfallTimeStamp = rainfallTimeStamp;
        this.monitorAdapterArrayList.add(monitorAdapter);
        this.sourceIdentifier = sourceIdentifier;
        passToAdapter(monitorAdapter);
    }

    //NOTE: No setter required, the only setter here should be update method

    /**
     * A getter for location
     *
     * @return A String that represents the name of the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * A getter for temperature
     *
     * @return A String that represents temperature, The String could be null or a double
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * A getter for rainfall
     *
     * @return A String that represents rainfall, The String could be null or a double
     */
    public String getRainfall() {
        return rainfall;
    }

    /**
     * A getter for temperature time stamp
     * @return A String that represents temperature time stamp
     */
    public String getTemperatureTimeStamp() {
        return temperatureTimeStamp;
    }

    /**
     * A getter for rainfall time stamp
     * @return A String that represents rainfall time stamp
     */
    public String getRainfallTimeStamp() {
        return rainfallTimeStamp;
    }

    /**
     * This method will add monitorAdapter to the array, all monitoradapters are relied on this locationObserver
     * @param monitorAdapter A monitor adapter
     */
    public void addMonitorAdapter(MonitorAdapter monitorAdapter) {
        this.monitorAdapterArrayList.add(monitorAdapter);
        passToAdapter(monitorAdapter);
    }

    /**
     * This method will construct a unique string as the hashmap's key
     * @return A string that will be used as a key for hashmap
     */
    public String getID() {
        return location + sourceIdentifier;
    }

    /**
     * This method updates rainfall, temperature, timeStamp, and it should inform adapter about the update
     */
    @Override
    public void update() {
        this.rainfall = this.locationSubject.getRainfall();
        this.temperature = this.locationSubject.getTemperature();
        this.temperatureTimeStamp = this.locationSubject.getTemperatureTimeStamp();
        this.rainfallTimeStamp = this.locationSubject.getRainfallTimeStamp();
        updateMonitorAdapterArray();
    }

    /**
     * Update all of the monitorAdapters in the array with latest information
     */
    private void updateMonitorAdapterArray() {
        Iterator<MonitorAdapter> itr = this.monitorAdapterArrayList.iterator();//This is to fix the concurrent update problem
        while (itr.hasNext()) {
            MonitorAdapter currentMonitorAdapter = itr.next();
            passToAdapter(currentMonitorAdapter);
        }
    }

    /**
     * Passing updates to individual monitor adapter
     * @param monitorAdapter A monitor adapter that is currently connected to a monitorGUI
     */
    private void passToAdapter(MonitorAdapter monitorAdapter) {
        monitorAdapter.displayTemperature(this.temperature,this.temperatureTimeStamp);
        monitorAdapter.displayRainFall(this.rainfall,this.rainfallTimeStamp);
    }

    /**
     * Remove a monitorAdapter from the array, If there is no adapter inside of the array, it will detach itself from the LocationSubject's hashmap
     * @param monitorAdapter A monitor adapter that is currently connected to a monitorGUI
     */
    public void removeMonitorAdapter(MonitorAdapter monitorAdapter) {
        monitorAdapterArrayList.remove(monitorAdapter);
        if (monitorAdapterArrayList.size() == 0) {//If no monitor adapter in the Arraylist
            this.locationSubject.detach(this);//Location subject detach this object
        }
    }
}
