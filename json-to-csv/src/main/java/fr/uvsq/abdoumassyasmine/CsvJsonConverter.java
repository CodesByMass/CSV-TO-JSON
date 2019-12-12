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
		// On a pas de schéma prédifini avec une POJO, les colonnes sont ajoutées via le terminal.
		CsvSchema schema = CsvSchema.builder().setUseHeader(true)
				.addColumn("firstName", CsvSchema.ColumnType.STRING)
				.addColumn("lastName", CsvSchema.ColumnType.STRING)
				.addColumn("age", CsvSchema.ColumnType.NUMBER)
				.build();

		// Itérateur qui va récupérer les clés / valeur
		MappingIterator<Map<String, Object>> mappingIterator = csvMapper
				.readerFor(Map.class)
				.with(schema)
				.readValues(CsvFile);
		
         // L'itération
		while (mappingIterator.hasNext()) {
			Map<String, Object> entryMap = mappingIterator.next();
			Number age = (Number) entryMap.get("age");

		}
	}}
