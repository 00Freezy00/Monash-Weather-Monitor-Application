import MelbourneWeatherTimeLapse.MelbourneWeatherTimeLapseStub;

/**
 * Created by Jack on 12-May-17.
 */
public class Driver {

    public static void main(String[] args) throws Exception {
        final MelbourneWeatherTimeLapseStub MelbourneWeatherTimeLapseService = new MelbourneWeatherTimeLapseStub();
        MelbourneWeatherTimeLapseStub.GetWeather getWeather = new MelbourneWeatherTimeLapseStub.GetWeather();
        MelbourneWeatherTimeLapseStub.GetWeatherResponse getWeatherResponse = MelbourneWeatherTimeLapseService.getWeather(getWeather);
        String[] testArray = getWeatherResponse.get_return();

    }
}
