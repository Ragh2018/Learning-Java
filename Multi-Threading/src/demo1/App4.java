package demo1;

import java.util.Scanner;

class Processor extends Thread{
	private volatile boolean running=true;
	
	public void run() {
		while(running) {
			System.out.println("Hello"+Thread.currentThread());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		running=false;
	}
}
public class App4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Processor p1=new Processor();
		p1.start();
		Processor p2=new Processor();
		p2.start();
		Scanner sc=new Scanner(System.in);
		sc.nextLine();
		p1.shutdown();
		
		
	}

}
