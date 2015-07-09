package util;

public interface Observer {
	public abstract void update(final Observable observable, final Object args);
}
