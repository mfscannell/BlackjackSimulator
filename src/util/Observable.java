package util;

public interface Observable {
	public abstract void addObserver(Observer obj);
	public abstract void deleteObserver(Observer obj);
	public abstract void notifyObservers();
}
