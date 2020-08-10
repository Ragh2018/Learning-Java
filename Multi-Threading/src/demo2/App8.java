package demo2;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App8 {

	public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

	public static void main(String[] args) throws InterruptedException  {
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					consumer(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t3=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					consumer(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();t2.start();t3.start();
		t1.join();t2.join();t3.join();
		
	}

	private static void producer() throws InterruptedException {
		Random random = new Random();
		while (true) {
			Thread.sleep(300);
			Integer k=random.nextInt(100);
			//put operation can not put more then 10 data in queue as it is mentioned while we made
			// a queue object. And there is no need of putting synchronized keyword as this is thread
			//safe
			queue.put(k);
			System.out.println("Putting value "+k+" in queue");
		}
	}

	private static void consumer(int k) throws InterruptedException {
		Random random = new Random();
		while (true) {
			Thread.sleep(100);
			if (random.nextInt(10) == 0) {
				//Same will happen to consumer it there is nothing in queue it will wait for data to
				//come in consumer.
				Integer value = queue.take();
				System.out.println("Consumer "+k+" Tooks Value " + value + "; Queue size is " + queue.size());
			}
		}
	}
}
