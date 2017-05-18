import java.util.ArrayList;
import java.util.Iterator;

/**
 * LocationObserver.java
 * A concrete observer class which stores all of the information (location, timestamp, temperature, rainfall) of a location, It should use an adapter to talk to the GUI
 * Author: Yi Fei (Freya) Gao, Yun Hao (Jack) Zhang
 */
public class LocationObserver implements Observer {

    //NOTE: These data are all string, they need a adapter to translate to a desire form
    private String location;
    private String temperature;
    private String rainfall;
    private String timeStamp;
    private LocationSubject locationSubject;
    private ArrayList<MonitorAdapter> monitorAdapterArrayList;
    private String sourceIdentifier;

    /**
     * A init function that requires all of the relevant information to construct the location observer
     * @param locationSubject: A locationSubject class that is a subject which will inform the observer class
     * @param location A String that represents the name of the location
     * @param timeStamp A String that represents the information's retrieve date
     * @param temperature A String that represents temperature, The String could be null or a double
     * @param rainfall A String that represents rainfall, The String could be null or a double
     */
    public LocationObserver(LocationSubject locationSubject, String location, String timeStamp, String temperature, String rainfall,String sourceIdentifier,MonitorAdapter monitorAdapter) {
        this.monitorAdapterArrayList = new ArrayList<>();
        this.locationSubject = locationSubject;
        this.location = location;
        this.rainfall = rainfall;
        this.temperature = temperature;
        this.timeStamp = timeStamp;
        this.monitorAdapterArrayList.add(monitorAdapter);
        this.sourceIdentifier = sourceIdentifier;
        passToAdapter(monitorAdapter);
    }

    //NOTE: No setter required, the only setter here should be update method

    /**
     * A getter for location
     * @return A String that represents the name of the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * A getter for temperature
     * @return A String that represents temperature, The String could be null or a double
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * A getter for rainfall
     * @return A String that represents rainfall, The String could be null or a double
     */
    public String getRainfall() {
        return rainfall;
    }

    /**
     * A getter for timeStamp
     * @return A String that represents the information's retrieve date
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    public void addMonitorAdapter(MonitorAdapter monitorAdapter) {this.monitorAdapterArrayList.add(monitorAdapter);
        passToAdapter(monitorAdapter);
    }
    /**
     * This method updates rainfall, temperature, timeStamp, and it should inform adapter about the update
     */


    public String getID() { return location + sourceIdentifier; }

    @Override
    public void update() {
        this.rainfall = this.locationSubject.getRainfall();
        this.temperature = this.locationSubject.getTemperature();
        this.timeStamp = this.locationSubject.getTimeStamp();
        updateMonitorAdapterArray();
    }

    private void updateMonitorAdapterArray(){
        Iterator<MonitorAdapter> itr = this.monitorAdapterArrayList.iterator();//This is to fix the concurrent update problem
        while(itr.hasNext()){
            MonitorAdapter currentMonitorAdapter = itr.next();
            passToAdapter(currentMonitorAdapter);
        }
    }

    private void passToAdapter(MonitorAdapter monitorAdapter){
        monitorAdapter.displayTemperature(this.temperature);
        monitorAdapter.displayRainFall(this.rainfall);
        monitorAdapter.displayLastUpdated(this.timeStamp);
    }
}
