import java.util.ArrayList;

/**
 * Created by Jack on 28-Apr-17.
 */
public interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

     void notifyObserver();
}
