package fr.uvsq.abdoumassyasmine;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMappingException;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * <b>This basically converts a CSV File into a JSON file.</b>
 * <p>
 * It contains 3 methods :
 * </p>
 * 
 * <ul> 
 * <li> parseCSV binds and parses a CSV file into a list of maps 
 * <li> cleanCSV creates a cleanest structure of the csv data, ready to be written into JSON file
 * <li> convertToJson calls the two above methods then generates the JSON file 
 * </ul>
 * 
 * @author Mass' Selmi
 * 
 * @version 2.0
 */
public class CsvJsonConverter {

	/**
	 * Parse the CSV file in a list.
	 * 
	 * @param csvFile The input csv file
	 * 
	 * @see #convertToJson(String CSV_File, String JSON_File)
	 * 
	 * @return an ArrayList with the CSV data parsed.
	 *
	 * @throws IOException Occurs with the file is empty, or unreadable
	 * 		
	 */
	public static List<Map<String, String>> parseCSV(File csvFile) throws IOException {
   List<Map<String, String>> temp_list = new ArrayList<Map<String, String>>();
   // Build the CSVmapper
   CsvMapper mapper = new CsvMapper();
   mapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
   // mapper.enable(CsvParser.Feature.SKIP_EMPTY_LINES);
   // Build the CSVschema
   CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';'); // use first row as header;
   // otherwise defaults are
   // fine
   MappingIterator<Map<String, String>> it = null;
   try {
	 it = mapper.readerFor(Map.class).with(schema).readValues(csvFile);
   } catch (CsvMappingException e) {
	 System.out.println(" Le fichier CSV est incorrect");
   }

	// Return a list from the MappingIterator
   if (it == null) {
	 throw new NullPointerException("Le fichier n'a pas pu être convertit");
   } else {
     temp_list = it.readAll();
   }
   return temp_list;
 }

	/**
	 * Cleans the CSV file, removes extra-spaces, double_quotes, escaping chars...
	 * 
	 *@param parsed_list It's the parsed list by the CSVMapper
	 * 
	 *@see #convertToJson(String CSV_File, String JSON_File)
	 * 
	 *@return A cleaned ArrayList
	 *
	 *@throws NullPointerException When the param is null, hence there is no data
	 * 
	 * 
	 */

	public static List<Map<String, String>> cleanCSV(List<Map<String, String>> parsed_list)
			throws NullPointerException {
		if (parsed_list == null) {
			throw new NullPointerException(" La conversion a échoué");
		} else {
			// Initialize variables
			List<Map<String, String>> temp_list = new ArrayList<Map<String, String>>();
			Map<String, String> temp_map = new LinkedHashMap<String, String>();
			// We clean the list
			for (Map<String, String> map : parsed_list) {
				temp_map = new LinkedHashMap<String, String>();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					String key = entry.getKey().trim().replace("\"", "");
					String value = entry.getValue().trim().replace("\"", "");
					temp_map.put(key, value);

				}
				temp_list.add(temp_map);
			}

			return temp_list;
		}

	}

	/**
	 * 
	 * Converts the CSV File into a JSON File
	 * 
	 * @param CSV_File  The name of csv file given by the user
	 *
	 * @param JSON_File The name of the Output file wanted by the user
	 * 
	 * @throws IOException Could occur when we read the file with the
	 *                     MappingIterator
	 */
	public static void convertToJson(String CSV_File, String JSON_File) throws IOException {
		// ! The string contains the full name, with the extension
		File csvFile = new File(CSV_File);
		// Check if the file exists
		if (!csvFile.exists()) {
			throw new FileNotFoundException(" Le fichier spécifié est introuvable");
		} else {
			/*
			 * Get the name of the file, for the new one in JSON / Deprecated since the
			 * chief Abdoulaye wanted two strings in the params / String file_name =
			 * csvFile.getName().replaceFirst("[.][^.]+$", "");
			 */

			// We extract a list from the mapping Iterator
			List<Map<String, String>> readObjectsFromCsv = parseCSV(csvFile);
			// Clean the list
			readObjectsFromCsv = cleanCSV(readObjectsFromCsv);

			// Build the JSON file
			try {

				ObjectMapper mapperObject = new ObjectMapper();

				// Write JSON formated data to output.json file
				mapperObject.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_File), readObjectsFromCsv);

			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
