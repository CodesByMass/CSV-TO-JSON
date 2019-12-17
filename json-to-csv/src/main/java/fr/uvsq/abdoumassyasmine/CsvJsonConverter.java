package fr.uvsq.abdoumassyasmine;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.csv.*;

/**
 * 
 * 
 * <b>This basically converts a CSV File into a JSON file</b>
 * <p> It contains two functions :  </p>
 * <ul> convertToJson bind, parse the csv file then write the json </ul>
 * <ul> cleanCSV creates a cleanest structure of the csv data, ready to be written into JSON file </ul>
 * 
 * @author Mass' Selmi
 *  
 * @version 1.0
 */
public class CsvJsonConverter {


	/**
	 * Cleans the CSV file, removes extra-spaces, double_quotes, escaping chars...
	 * 
	 * @param parsed_list
	 * It's the parsed list by the CSVMapper
	 * 
	 * @see CSVJsonConverter.ConvertToJson(File CSV_File)
	 * 
	 * @return A cleaned ArrayList
	 *
	 * @throws NullPointerException
	 * 		
	 * 
	 */
	
	public static List<Map<String, String>> cleanCSV(List<Map<String, String>> parsed_list) throws NullPointerException
	{
		// Initialize variables
		List<Map<String, String>> temp_list = new ArrayList<Map<String, String>>() ;
		Map<String, String> temp_map = new LinkedHashMap<String, String>();
		// We clean the list
		for (Map<String, String> map : parsed_list) {
			temp_map = new LinkedHashMap<String, String>();
		    for (Map.Entry<String, String> entry : map.entrySet()) {
		        String key = entry.getKey().trim().replace("\"", "");
		        String value = entry.getValue().trim().replace("\"", "") ;
		        temp_map.put(key, value) ;
		  
		    }
		    temp_list.add(temp_map) ;
		}
		
		return temp_list;
		
	}
	/**
	 * 
	 * Converts the CSV File into a JSON File
	 * 
	 * @param CSV_File
	 * The csv file given by the user
	 * 
	 * @throws IOException
	 * Could occur when we read the file with the MappingIterator
	 */
	public static void convertToJson(File CSV_File) throws IOException
	{

	File csvFile = CSV_File ;
	// Get the name of the file, for the new one in JSON
	String file_name = csvFile.getName().replaceFirst("[.][^.]+$", "");;
	CsvMapper mapper = new CsvMapper();
	CsvSchema schema = CsvSchema.emptySchema().withHeader(); // use first row as header; otherwise defaults are fine
	MappingIterator<Map<String,String>> it = mapper.readerFor(Map.class)
	   .with(schema)
	   .readValues(csvFile);
	
	// We extract a list from the mapping Iterator
	List<Map<String, String>> readObjectsFromCsv = it.readAll() ;
	// Clean the list
	readObjectsFromCsv = cleanCSV(readObjectsFromCsv) ;
	
	// Build the JSON file
	try {
		
		ObjectMapper mapperObject = new ObjectMapper() ;
		
		// Write JSON formated data to output.json file
		mapperObject.writerWithDefaultPrettyPrinter().writeValue(new File(file_name+".json"),readObjectsFromCsv);

		
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	}
