/**
 * Created by Jack on 17/5/17.
 */
import java.lang.Exception;
import java.text.DecimalFormat;

import MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub;
import MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub.*
        ;

public class MelbourneWeatherTimeLapseGrabber extends WeatherGrabber {
    private LocationSubject locationSubject;
    private MelbourneWeatherTimeLapseStub melbourneWeatherTimeLapse;
    public static final String SOURCE_IDENTIFIER = "_MelbourneWeatherTimeLapse";

    public  MelbourneWeatherTimeLapseGrabber(LocationSubject locationSubject) throws Exception{
        this.locationSubject = locationSubject;
        this.melbourneWeatherTimeLapse = new MelbourneWeatherTimeLapseStub();
    }

    public String[] grabWeather(String location) throws Exception {
        GetWeather getWeather = new MelbourneWeatherTimeLapseStub.GetWeather();
        getWeather.setLocation(location);
        GetWeatherResponse weatherResponse = melbourneWeatherTimeLapse.getWeather(getWeather);
        String[] weatherInfo = weatherResponse.get_return();
        weatherInfo[1] = kelvinToCelsius(weatherInfo[1]);
        weatherInfo[2] = cmToMM(weatherInfo[2]);
        return weatherResponse.get_return();
    }

    @Override
    public String[] grabRainFall(String location) throws Exception {
        GetWeather getWeather = new MelbourneWeatherTimeLapseStub.GetWeather();
        getWeather.setLocation(location);
        GetWeatherResponse weatherResponse = melbourneWeatherTimeLapse.getWeather(getWeather);
        String[] weatherArray = weatherResponse.get_return();
        return new String[]{weatherArray[0],weatherArray[2]};
    }

    @Override
    public String[] grabTemperature(String location) throws Exception {
        GetWeather getWeather = new MelbourneWeatherTimeLapseStub.GetWeather();
        getWeather.setLocation(location);
        GetWeatherResponse weatherResponse = melbourneWeatherTimeLapse.getWeather(getWeather);
        String[] weatherArray = weatherResponse.get_return();
        return new String[]{weatherArray[0],weatherArray[1]};
    }

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
                        String[]weatherInfo = grabWeather(location[0]);
                        locationSubject.updateWeather(locationID, weatherInfo[0], weatherInfo[1], weatherInfo[2]);
                    } catch (Exception ex) {
                        LocationObserver aLocation = (LocationObserver) locationSubject.getObserverHashMap().get(locationID);
                        locationSubject.updateWeather(locationID, aLocation.getTimeStamp() + " update Fail!!", aLocation.getTemperature(), aLocation.getRainfall());//If the update fails, It won't remove the previous data
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

    private String kelvinToCelsius(String kelvin){
        final Double convertConstant = 273.15;
        try{
            Double kelvinDouble = Double.parseDouble(kelvin);
            Double celsiusDouble = kelvinDouble - convertConstant;
            DecimalFormat df = new DecimalFormat("#.##");
            return String.valueOf(df.format(celsiusDouble));
        }catch (Exception ex){
            return "";
        }
    }

    private String cmToMM(String cm){
        final Double convertConstant = 100.00;
        try{
            Double cmDouble = Double.parseDouble(cm);
            Double mmDouble = cmDouble * convertConstant;
            DecimalFormat df = new DecimalFormat("#.##");
            return String.valueOf(df.format(mmDouble));
        }catch (Exception ex){
            return "";
        }
    }
}
