import java.util.ArrayList;

/**
 * Created by Jack on 28-Apr-17.
 */
abstract class Subject {
    abstract void attach(Observer observer);

    abstract void detach(Observer observer);

    abstract void notifyObserver();
}
