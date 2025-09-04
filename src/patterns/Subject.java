package patterns;

public interface Subject {
    void registerObserver(Observer o);

    void notifyObservers();
}
