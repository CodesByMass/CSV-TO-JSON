package fr.uvsq.abdoumassyasmine;

public class InvalidFileTypeException extends Exception{
	
	public InvalidFileTypeException() {
		System.err.println("Ce n'est pas le type de fichier attendu");
		System.exit(1);
	}

}
