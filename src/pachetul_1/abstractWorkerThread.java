package pachetul_1;


public abstract class abstractWorkerThread extends Thread {// interfata care va fi implementata de thread-urile producator si consumator
	public Buffer buffer;
	public abstract void doWork() throws InterruptedException; //functie care trimite sau primeste sfertul de imagine 
	protected abstractWorkerThread() {// constructor
        super();
    }
	public void run() { //implementarea functiei run pentru producator si consumator (aceeasi), desi functia doWork este scrisa pentru fiecare
        try {
            this.doWork();
        }catch (InterruptedException e) { //tratare exceptii
        		System.out.println("A aparut o problema interfata thread-urilor");
        		e.printStackTrace();
        }
    }
}
