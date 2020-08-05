package demo1;

public class App5 {

	private int count=0;
	public static void main(String[] args) {
		App5 app=new App5();
		app.doWork();
	}
	public synchronized void increment() {
		count++;
	}
	public void doWork() {
		
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10000;i++)
				{
					//count++;
					increment();
				}
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<10000;i++)
				{
					//count++;
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		//here thread is still running so process is not completed before that we are printing
		//that's why it's not show 20000
		System.out.println(count);
		
		//join is used to hold the below code once thread will stop working then it will print
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//still you will not get 20000 because it can be possible that both thread accessing same value 
		//at a time and assign it to count suppose thread 1 access 100 and thread 2 also access 100 so after
		//that count from both the thread will be same(count=count+1(here 3 steps are involve which
		// will take time to fetch add assign value of count))
		
		System.out.println(count);
		
	}

}
