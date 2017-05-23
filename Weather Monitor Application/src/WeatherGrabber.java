/**
 * An abstract class for Subject
 * Author: Yifei (Freya) Gao, Yun Hao (Jack) Zhang
 */
abstract class WeatherGrabber implements Runnable {
    public abstract String[] grabRainFall(String location) throws Exception;

    public abstract String[] grabTemperature(String location) throws Exception;

    public abstract String[] grabLocations() throws Exception;
}
