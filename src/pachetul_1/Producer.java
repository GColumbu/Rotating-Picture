package pachetul_1;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Producer extends abstractWorkerThread{
	File inputFile;
	Dimension d = new Dimension();
	InputStream is = null;
	
    public Producer(Buffer buffer, File inputFile, Dimension d) { //constructor
        super();
        this.buffer = buffer;
        this.d = d;
        this.inputFile = inputFile;
    }
    
    int getDimension(){ // returneaza un obiect de tip Dimension cu dimensiunile imaginii
    	return (int) ((d.getHeight()*d.getWidth())/4);
    }

    // Producatorul citeste cate un sfert din imagine de la consumator
	@Override
    public void doWork() {

		int dim = getDimension();		
        try {
			is = new FileInputStream(inputFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		     
        for (int i = 1; i < 5; i++) {
        	byte[] vect1 = new byte[dim*2]; // declar vectorul de byte
        	try {
				is.read(vect1, (i-1)*dim/4, dim/4+dim); //selecteaza sfertul de imagine si il pune in vectorul de byte
			} catch (IOException e) {
				e.printStackTrace();
			}
            
            try {
                this.buffer.put(vect1, i - 1); // Producatorul trimite sfertul de imagine catre consumator
                System.out.println("Thread producator: " + i + "/4 din imagine");
                Thread.sleep(1000); //Producator asteapta o secunda
            } catch (InterruptedException e) { 
                e.printStackTrace();
            }
        }
    }
}
