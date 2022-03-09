package pachetul_1;

public class Buffer { //clasa ce se ocupa mai mult de sincronizare
	private int dimensiune;
	private final byte[] vect;
	
	private boolean available = false; //variabila pentru a tine cont daca thread-ul este ocupat sau nu
	
	public Buffer(int dim) { // constructor
		dimensiune = dim;
		vect = new byte[dimensiune];
	}
	//getter pentru vector
	public byte[] getVect() { 
		return vect;
	}
	
	public synchronized byte[] get() { // Functie apelata de Consumator
		if (!available) {
			try {
				wait(); // In caz ca threadul producator inca lucreaza, il asteapta pe acesta sa puna o valoare
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		available = false; // updatarea variabilei de disponibilitate

		notifyAll(); // se anunta terminarea functiei
		return vect;
	}

	public synchronized void put(byte [] vect1, int offset) { // Functie apelata de Producator
		if (available) {
			try {
				wait();
				// In caz ca threadul producator inca lucreaza, il asteapta pe acesta sa preia valoarea
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
		// Thread-ul Producator pune un sfert din imagine in vect pentru a putea fi preluata de Consumer
		for(int i = offset * dimensiune / 4; i < (offset + 1)*dimensiune/4; i++) {
			vect[i] = vect1[i - offset * dimensiune / 4];
		}
		// avem nevoie de offset in interiorul for-ului pune a putea stii la ce sfert din imagine ne referim
		available = true; // updatarea variabilei de disponibilitate
		notifyAll(); // se anunta terminarea functiei
	}
}
