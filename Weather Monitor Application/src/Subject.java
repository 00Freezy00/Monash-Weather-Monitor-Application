import java.util.ArrayList;

/**
 * An abstract class for Subject
 * Author: Yi Fei (Freya) Gao, Yun Hao (Jack) Zhang
 */
abstract class Subject {
    abstract void attach(Observer observer);

    abstract void detach(Observer observer);

    abstract void notifyObserver();
}
