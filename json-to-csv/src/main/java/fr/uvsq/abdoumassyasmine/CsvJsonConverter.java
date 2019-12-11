package fr.uvsq.abdoumassyasmine;

import java.io.*;
import java.util.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;

/**
 * 
 * @author Mass'
 * @version 0.1
 * 
 * <b>Cette classe convertit les fichiers CSV en fichier JSON</b>
 */
public class CsvJsonConverter {
	
	
	/**
	 * Lis un fichier CSV et le transforme en JSON
	 * 
	 * @param CsvFile
	 * 		Le fichier entré par l'utilisateur 
	 * 
	 * @param JsonFile
	 * 		Le fichier convertit par le programme
	 * 
	 * @throws FileNotFoundException
	 * 		Si on ne trouve pas le fichier
	 * @throws IOException
	 * 		
	 */
	public static void convertToJSON (File CsvFile, File JsonFile) throws Exception {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		
}
}
