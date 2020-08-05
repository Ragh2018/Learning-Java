package demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

	private Random random = new Random();

	// Every object have intrinsic lock if we use worker class object our program
	// will took more
	// time to process the two method so if we provide the two lock in synchronized
	// block if one thread
	// acquire that method then that thread can work on the other method. Because
	// both will have different thread

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	// here on method level we can use this method as a synchronized but we didn't
	// use if we used then
	// it will hold the lock of worker class . But for now this method can be used
	// by both the thread
	// but in synchronized block if one thread is working on it then other thread
	// have to wait until
	// that thread release the lock
	
	//public synchronized void stageOne()  (this will take double time if we add lock on method)
	public void stageOne() {

		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	//public synchronized void stageTwo()
	public void stageTwo() {

		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public static void main(String[] args) {

		System.out.println("Starting......");

		Worker worker = new Worker();

		long start = System.currentTimeMillis();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				worker.process();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				worker.process();
			}
		});
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("Time " + (end - start));
		System.out.println(worker.list1.size() + " " + worker.list2.size());
	}

}
