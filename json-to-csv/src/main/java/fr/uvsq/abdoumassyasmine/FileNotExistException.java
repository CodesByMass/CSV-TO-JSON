package fr.uvsq.abdoumassyasmine;

public class FileNotExistException extends Exception {
	
	public FileNotExistException () {
		System.err.println("Le fichier en entrée n'existe pas");
		System.exit(1);
	}
}
