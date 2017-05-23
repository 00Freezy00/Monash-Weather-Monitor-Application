import MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub;
import MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.GetLocationsResponse;
import MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.GetWeather;
import MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.GetWeatherResponse;

import java.text.DecimalFormat;
/**
 * MelbourneWeatherTimeLapseGrabber.java
 * An API like class that provides all necessary methods of the service
 * Author: Yifei (Freya) Gao, Yun Hao (Jack) Zhang
 */
public class MelbourneWeatherTimeLapseGrabber extends WeatherGrabber {
    private LocationSubject locationSubject;
    private MelbourneWeatherTimeLapseStub melbourneWeatherTimeLapse;
    public static final String SOURCE_IDENTIFIER = "_MelbourneWeatherTimeLapse";
    /**
     * A init function that construct MelbourneWeatherGrabber
     *
     * @param locationSubject Main controller of observer pattern
     * @throws Exception Initialise melbourne weather service failed
     */
    public MelbourneWeatherTimeLapseGrabber(LocationSubject locationSubject) throws Exception {
        this.locationSubject = locationSubject;
        this.melbourneWeatherTimeLapse = new MelbourneWeatherTimeLapseStub();
    }

    /**
     * Extra method that only available on this api,
     *@param location A String that represents the name of the location
     * @return An array 0 is timestamp, 1 is temperature, 2 is rainfall
     * @throws Exception Weather Service is unavailable, particularly rainFall
     */
    public String[] grabWeather(String location) throws Exception {
        GetWeather getWeather = new MelbourneWeatherTimeLapseStub.GetWeather();
        getWeather.setLocation(location);
        GetWeatherResponse weatherResponse = melbourneWeatherTimeLapse.getWeather(getWeather);
        String[] weatherInfo = weatherResponse.get_return();
        weatherInfo[1] = kelvinToCelsius(weatherInfo[1]);
        weatherInfo[2] = cmToMM(weatherInfo[2]);
        return weatherResponse.get_return();
    }

    /**
     * Grab rainfall by the location from the weather service
     *
     * @param location A String that represents the name of the location
     * @return An array 0 is timestamp, 1 is rainfall
     * @throws Exception Weather Service is unavailable, particularly rainFall
     */
    @Override
    public String[] grabRainFall(String location) throws Exception {
        GetWeather getWeather = new MelbourneWeatherTimeLapseStub.GetWeather();
        getWeather.setLocation(location);
        GetWeatherResponse weatherResponse = melbourneWeatherTimeLapse.getWeather(getWeather);
        String[] weatherArray = weatherResponse.get_return();
        return new String[]{weatherArray[0], weatherArray[2]};
    }

    /**
     * Grab temperature by the location from the weather service
     *
     * @param location A String that represents the name of the location
     * @return An array 0 is timestamp, 1 is temperature
     * @throws Exception Weather Service is unavailable, particularly Temperature
     */
    @Override
    public String[] grabTemperature(String location) throws Exception {
        GetWeather getWeather = new MelbourneWeatherTimeLapseStub.GetWeather();
        getWeather.setLocation(location);
        GetWeatherResponse weatherResponse = melbourneWeatherTimeLapse.getWeather(getWeather);
        String[] weatherArray = weatherResponse.get_return();
        return new String[]{weatherArray[0], weatherArray[1]};
    }

    /**
     * Get all of the location available from the weather service
     *
     * @return All of location in String array
     * @throws Exception Weather Service is unavailable, particularly location
     */
    @Override
    public String[] grabLocations() throws Exception {
        GetLocationsResponse locationsResponse = this.melbourneWeatherTimeLapse.getLocations();
        return locationsResponse.get_return();
    }

    @Override
    public void run() {
        while (true) {
            for (String locationID : locationSubject.getObserverHashMap().keySet()) {
                if (locationID.endsWith(SOURCE_IDENTIFIER)) {
                    try {
                        String[] location = locationID.split("_");
                        String[] weatherInfo = grabWeather(location[0]);
                        locationSubject.updateWeather(locationID, weatherInfo[1],weatherInfo[0], weatherInfo[2],weatherInfo[0]);
                    } catch (Exception ex) {
                        LocationObserver aLocation = (LocationObserver) locationSubject.getObserverHashMap().get(locationID);
                        locationSubject.updateWeather(locationID, aLocation.getTemperature(),aLocation.getTemperatureTimeStamp() + " update Fail!!", aLocation.getRainfall() , aLocation.getRainfallTimeStamp()+ " update Fail!!");//If the update fails, It won't remove the previous data
                    }
                }
            }
            try {
                Thread.sleep(20 * 1000);//Delay 5 mins
            } catch (Exception e) {
                //Try it again
            }
        }
    }

    /**
     * A private method that converts kelvin to celsius
     * @param kelvin A String in kelvin or ""
     * @return A String in Celsius or ""
     */
    private String kelvinToCelsius(String kelvin) {
        final Double convertConstant = 273.15;
        try {
            Double kelvinDouble = Double.parseDouble(kelvin);
            Double celsiusDouble = kelvinDouble - convertConstant;
            DecimalFormat df = new DecimalFormat("#.##");
            return String.valueOf(df.format(celsiusDouble));
        } catch (Exception ex) {
            return "";
        }
    }
    /**
     * A private method that converts cm to mm
     * @param cm A String in cm or ""
     * @return A String in mm or ""
     */
    private String cmToMM(String cm) {
        final Double convertConstant = 100.00;
        try {
            Double cmDouble = Double.parseDouble(cm);
            Double mmDouble = cmDouble * convertConstant;
            DecimalFormat df = new DecimalFormat("#.##");
            return String.valueOf(df.format(mmDouble));
        } catch (Exception ex) {
            return "";
        }
    }
}
