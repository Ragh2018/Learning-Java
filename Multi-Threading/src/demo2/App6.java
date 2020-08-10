package demo2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
	private int id;
	public  Processor(int id) {
		this.id=id;
	}
	@Override
	public void run() {
		System.out.println("starting "+id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("completed "+id);
	}
	
}
public class App6 {

	public static void main(String[] args) {
		
		//it will create the thread pool of size 2
		ExecutorService executorService=Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			//it is putting all the tasks in the thread pool
			executorService.submit(new Processor(i));
		}
		
		//executorService shutdown will stops puttings the tasks in thread pool
		executorService.shutdown();
		System.out.println("All tasks submitted");
		
		try {
			//it will wait for 1 day to tasks gets completed else it will terminate the thread
			executorService.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All tasks completed");
	}

}
