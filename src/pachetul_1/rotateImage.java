package pachetul_1;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class rotateImage extends Image implements rotateImageInterface{//extinde clasa Image si implementeaza Interfata
	//variabile pentru a putea face masurarea timpului
	static long start;
	static long end;
	//functia pe care o chemam in main
	public static void rotateImageFromFile(int factor, String ...files) {
		//iterare prin parametrii cu lungime variabila
		for(String s : files) {
			File inputFile = new File(s);	
			File outputFile = new File(s.substring(0, s.length() - 4) + "_rotated.bmp"); // creare fisier de iesire (numele va depinde de fisierul de intrare)
			//verificare daca fisierul curent exista
			if(!inputFile.exists()) {
	        	System.out.println("A aparut o problema legata de fisierele de input!\n" 
	        						+ "Verificati denumirea fisierului sau daca este in locatia corecta!");
	        }	
		try {
			//citire imagine tinand cont de threaduri (se porneste threadul consumer si producer) si calculul de timp
			readImageFromFile(inputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//functie din clasa Image care ne initializeaza atributele width and height 
		setWidthAndHeight();
		//atributul image din clasa Image este schimbat la forma FINALA
		image = rotateImg(image, factor);
		//functie din clasa Image care ne baga imaginea in fisierul output tinand cont de calculul de timp
		writeImageToFile(outputFile);
		}
	}
	//functie care intoarce imaginea O DATA (la 90 de grade)
	private static BufferedImage rotate(BufferedImage img) {
		BufferedImage newImg = null;
		int width = img.getWidth();
        int height = img.getHeight();
        //cream imaginea noua cu dimensiunile inversate
		newImg = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
		//iteram prin fiecare vector si ne folosim de imaginea veche pentru a calcula indexul
		for(int i=0; i<width; i++) {
        	for(int j=0; j<height; j++) {
        		newImg.setRGB(j, i, img.getRGB(i, height-j-1));
        	}
        }
		return newImg;
	}//functie care apeleaza "rotate" de mai multe ori in functie de numarul rotirilor(90, 180 sau 270)
	public static BufferedImage rotateImg(BufferedImage image, int factor) {
		for(int i=0; i<factor; i++) {
			image = rotate(image);
			setWidthAndHeight();
		}
		return image;
	}
}
