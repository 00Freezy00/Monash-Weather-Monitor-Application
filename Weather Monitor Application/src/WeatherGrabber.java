
/**
 * An abstract class for Subject
 * Author: Yi Fei (Freya) Gao, Yun Hao (Jack) Zhang
 */
abstract class WeatherGrabber implements Runnable{
    public abstract Observer newLocationObserver(String location)throws Exception;
    public abstract String[] grabRainFall(String location) throws Exception;
    public abstract String[] grabTemperature(String location) throws Exception;
    public abstract String[] grabLocations() throws Exception;
}
