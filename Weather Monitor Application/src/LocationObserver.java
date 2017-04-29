/**
 * Created by Jack on 28-Apr-17.
 */
public class LocationObserver implements Observer {


    private String location;
    private double temperature;
    private double rainfall;
    private String timeStamp;
    private LocationSubject locationSubject;

    public LocationObserver(LocationSubject locationSubject, String location,String timeStamp, double temperature, double rainfall) {
        this.locationSubject = locationSubject;
        this.location = location;
        this.rainfall = temperature;
        this.temperature = rainfall;
        this.timeStamp = timeStamp;
    }


    public String getLocation() {
        return location;
    }


    public double getTemperature() {
        return temperature;
    }


    public double getRainfall() {
        return rainfall;
    }

    public String getTimeStamp() {return timeStamp;}

    @Override
    public void update() {
        this.rainfall = this.locationSubject.getRainfall();
        this.temperature = this.locationSubject.getTemperature();
        this.timeStamp = this.locationSubject.getTimeStamp();
        System.out.print(location + " @ " + this.timeStamp
                + ":\n\tTemperature:\t" + this.temperature
                + "\n\tRainfall:\t" + this.rainfall
                + "\n\n");

    }
}
