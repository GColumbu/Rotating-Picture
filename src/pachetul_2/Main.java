package pachetul_2;

import java.util.Scanner;

import pachetul_1.rotateImage;

public class Main {
	public static void main(String args[]) {
		String inputPath; 	
		Scanner input = new Scanner(System.in);
		System.out.println("De cate ori vreti sa rotiti imaginea? (90-1, 180-2, 270-3): ");
		int rotateNumber = input.nextInt();
	    Scanner scan = new Scanner(System.in);
	    System.out.println("Insert the path to the image ( the path must respect the next form :  "
	    		+ "C:\\Users\\georg\\OneDrive\\Desktop\\Facultate\\AWJ\\Proiect");
	    inputPath = scan.nextLine();			
		rotateImage.rotateImageFromFile(rotateNumber, inputPath); //functie din clasa care implementeaza 
		//interfata pentru rotire (cu threaduri)
		
		System.out.println("Verifica fisierul output!");
	}
}
