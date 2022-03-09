package pachetul_1;

import java.awt.Dimension;

public class Consumer extends abstractWorkerThread{
	Dimension d = new Dimension(); // obiect de tip Dimension (pentru dimensiunile imaginii)
	
    public Consumer(Buffer buffer, Dimension dim) { //constructor
        super();
        this.buffer = buffer;
        this.d = d;
    }
    
    int getDimension(){ // returneaza un obiect de tip Dimension cu dimensiunile imaginii (folosit in doWork)
    	return (int) ((d.getHeight()*d.getWidth())/4);
    }
    
    //Consumaorul preia cate un sfert din imagine de la producator
    @Override
    public void doWork() throws InterruptedException {
		int dim = getDimension();
        for (int i = 1; i < 5; i++) {
            byte[] vect1 = this.buffer.get(); // Consumatorul primeste sfertul de imagine de la Producator
            System.out.println("Thread consumer: " + i + "/4 din imagine");
            Thread.sleep(1000);    // Threadul consumator asteapta o secunda   
        }
    }

	public byte[] getFullImage() {
		return this.buffer.getVect();
	}
}
