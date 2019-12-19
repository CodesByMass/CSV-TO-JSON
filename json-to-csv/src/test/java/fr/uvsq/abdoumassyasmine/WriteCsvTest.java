package fr.uvsq.abdoumassyasmine;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * <b> This tests all the methods of the WriteCSV class. </b>
 * 
 * @author Mass' Selmi
 * 
 * @version 1.0
 *
 */
public class WriteCsvTest {
	
	/**
	 * Uses the temporary folder of Junit to mock files.
	 */
	@Rule
	public TemporaryFolder folder= new TemporaryFolder();
	
	private File mock_file = null ;

	//Variable initialisations
	private String json_string = "{\r\n" + 
			"    \"name\": \"John\",}" ;
	private List<Map<String, String>> list_json = new ArrayList<Map<String, String>>() ;
	private Map<String, String> data = new LinkedHashMap<String, String>() ;

	/**
	 * Check if the function creates the csv file
	 * 
	 * @throws IOException
	 */
	@Test
	public void testWritetocsv() throws IOException {
		mock_file = folder.newFile("mock_file.json");
		data.put("name", "John");
		list_json.add(data);
		Set<String> headers = new TreeSet<String>();
		headers.add("name");
		FileUtils.write(mock_file, json_string, "UTF-8");
		WriteCsv.writetocsv(list_json, ";", "mock_file.csv", headers) ;
		assertTrue(new File("mock_file.csv").exists());
	}

	/**
	 * Check the equality between the JSON keys and CSV headers
	 */
	@Test
	public void testEcriture_csv()
	{
		data.put("name", "John");
		list_json.add(data);
		Set<String> headers = new TreeSet<String>();
		headers.add("name");
		assertEquals(headers,WriteCsv.ecriture_csv(list_json));
	}
}
