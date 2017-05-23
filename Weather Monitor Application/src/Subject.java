/**
 * An abstract class for Subject
 * Author: Yifei (Freya) Gao, Yun Hao (Jack) Zhang
 */
abstract class Subject {
    /**
     * Remove a observer from the notify list
     *
     * @param observer a observer it could be locationObserver
     */
    abstract void attach(Observer observer);

    /**
     * Add a observer to the notify list
     *
     * @param observer a observer it could be locationObserver
     */
    abstract void detach(Observer observer);

    /**
     * Notify the target observer by invoke the update function on the observer
     */
    abstract void notifyObserver();
}
