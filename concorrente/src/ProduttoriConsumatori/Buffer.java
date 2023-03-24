package ProduttoriConsumatori;

import java.util.ArrayList;

public class Buffer<T> {
	private static int size = 1;
	private int currentCapacity = 0;
	ArrayList<T> buf;

	public Buffer(int s) {
		size = s;
		buf = new ArrayList<T>(s);

	}

	public synchronized void insert(T data) throws InterruptedException {
		while (currentCapacity>=size)
			wait();
		buf.add(currentCapacity++, data);
		notifyAll();
	}

	public synchronized T get() throws InterruptedException {
		T value = null;

		while(currentCapacity <= 0)
			wait();

		value = buf.get(--currentCapacity);
		notifyAll();

		return value;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

}
