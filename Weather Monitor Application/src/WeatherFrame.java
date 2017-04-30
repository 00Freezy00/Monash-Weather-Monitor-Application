import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Jack on 30-Apr-17.
 */
public class WeatherFrame extends JFrame{
    private LocationSubject locationSubject;
    private String location;

    public WeatherFrame(String title, LocationSubject locationSubject, String location){
        super(title);
        this.locationSubject = locationSubject;
        this.location = location;
    }

    public void processWindowEvent(WindowEvent e){
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            try {
                locationSubject.detach(locationSubject.getObserverHashMap().get(location));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            dispose();
        }
    }
}
