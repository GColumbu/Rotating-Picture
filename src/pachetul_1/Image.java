package pachetul_1;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
public abstract class Image {
	//imaginea cu care o sa lucram
	static BufferedImage image;
	private static int height = 0;
	private static int width = 0;
	// Getteri si setteri pentru height si width
	public static int getHeight() {
		return height;
	}
	public static int getWidth() {
		return width;
	}
	public static void setHeight(int height) {
		Image.height = height;
	}
	public static void setWidth(int width) {
		Image.width = width;
	}
	// Obiect de tip dimension pentru a putea face impartirea in threaduri
	public static Dimension getDimension(File inputFile) throws IOException { 
		BufferedImage img;
		img = ImageIO.read(inputFile); // citesc imaginea ca BufferedImage
		int width = img.getWidth(); 
		int height = img.getHeight();
		return new Dimension(width, height);
	}

		// Pornirea thread-urilor si calcularea timpului pentru citirea imaginii
		public static void readImageFromFile(File inputFile) throws IOException {
			int myArray = {1, 3, 5};
			long startTime = System.currentTimeMillis(); //start
			Dimension dim = getDimension(inputFile);
			Buffer buff = new Buffer((int) (dim.getHeight() * dim.getWidth())); //dimensiunea totala
			
			
			Producer producerThread = new Producer(buff, inputFile, dim); // thread producator
			Consumer consumerThread = new Consumer(buff, dim); // thread consumator

			producerThread.start(); // pornire thread producator
			consumerThread.start(); // pornire thread consumator
			
			try {
				consumerThread.join(); //main thread este blocat pana cand se termina executia thread-ului consumator
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			// transform din vector de bytes in Buffered image
			ByteArrayInputStream byteVector = new ByteArrayInputStream(buff.getVect());
			try {
				image = ImageIO.read(byteVector); //transformam din vector de biti in BufferedImage
			} catch (IOException e) {
				e.printStackTrace();
			}
			long endTime = System.currentTimeMillis();
			System.out.println("\nTimpul pentru citirea fisierului este: " + (endTime - startTime) + " millisecunde"); // calculul timpului necesar pentru citirea imaginii
		}
		
		
		// Scrierea imaginii prelucrate in fisierul de output
		public static void writeImageToFile(File outputFile){
			
			long startTime = System.currentTimeMillis();

			try {
				ImageIO.write(image, "png", outputFile);
			} catch (IOException e) { //tratare exceptii
				System.out.println("Trebuie sa introduci un fisier png");
				e.printStackTrace();
			}

			long endTime = System.currentTimeMillis();
			System.out.println("Timpul pentru scrierea imaginii in fisier este:  " + (endTime - startTime) + " millisecunde\n");

		}

		// seteaza height si width a imaginii
		public static void setWidthAndHeight() {
			setHeight(image.getHeight());
			setWidth(image.getWidth());
		}
}
