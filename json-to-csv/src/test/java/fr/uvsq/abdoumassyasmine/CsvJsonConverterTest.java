package fr.uvsq.abdoumassyasmine;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.fasterxml.jackson.dataformat.csv.CsvMappingException;

/**
 * <b> This tests all the functions of the CsvJsonConverter class
 * 
 * @author Mass' Selmi
 * 
 * @version 1.0
 *
 */

public class CsvJsonConverterTest {
	
	/**
	 * Uses the temporary folder of Junit to mock files
	 */
	@Rule
	public TemporaryFolder folder= new TemporaryFolder();

	String wrong_string = "\r\n" + 
			"2\r\n" + 
			"3\r\n" + 
			"4\r\n" + 
			"5	0 ";

	File mockFile_empty = null ;
	File mockFile_good = null ;
	File mockFile_wrong = null ;
	File exemple = null ;
	
	List<Map<String, String>> parsed = new ArrayList<Map<String, String>>() ;



	@Before
	public void setup()
			throws IOException
	{
		mockFile_empty = folder.newFile("mockFile_empty.csv");
		mockFile_good = folder.newFile("mockFile_good.csv");
		mockFile_wrong = folder.newFile("mockFile_wrong.csv");
		FileUtils.write(mockFile_wrong, wrong_string, "UTF-8");        
		File exemple = new File("exemple.csv");
		FileUtils.copyFile(exemple, mockFile_good);
	}

	/**
	 * Tests if the Parser ignores empty files
	 * Should find that the Mapper doesn't find anything
	 * 
	 * @throws IOException
	 */
	@Test(expected = NullPointerException.class)
	public void testParseCSV_emptyfile() throws IOException {
		assertNull(CsvJsonConverter.parseCSV(mockFile_empty));
	}

	/**
	 * Tests a normal CSV file
	 * 
	 * @throws IOException
	 */
	@Test
	public void testParseCSV_normalFile() throws IOException {
		assertNotNull(CsvJsonConverter.parseCSV(mockFile_good));
	}

	/**
	 * Tests a corrupted CSV File
	 * 
	 * @throws IOException
	 */
	@Test(expected = NullPointerException.class)
	public void testParseCSV_wrongFile() throws IOException {
		assertNull(CsvJsonConverter.parseCSV(mockFile_wrong));
	}
	
	/**
	 * Tests an empty CSV File
	 * 
	 * @throws IOException
	 */
	@Test(expected = NullPointerException.class)
	public void testParseCSV_Exception() throws IOException {
		CsvJsonConverter.parseCSV(mockFile_empty);
	}

	/**
	 * Tests if the function actually does the job done
	 * 
	 * @throws IOException
	 */
	@Test 
	public void testParseCSV_successfull() throws IOException {
		 Map<String, String> data = new LinkedHashMap<String, String>();
         data.put("Nom", "Lopes");
         data.put("Prenom", "Stephane");
         parsed.add(data);
         FileUtils.write(mockFile_good, "Nom;Prenom \n"
         		+ "Lopes;Stephane", "UTF-8"); 
         assertEquals(parsed,CsvJsonConverter.parseCSV(mockFile_good));
	}
	
	/**
	 * Tests if the function throws a NullPointerException if the param is null
	 */
	@Test(expected = NullPointerException.class)
	public void testCleanCSV_null() {
		parsed = null ;
		CsvJsonConverter.cleanCSV(parsed) ;
	}
	
	/**
	 * Tests if the function actually does clean the list given in param
	 */
	@Test
	public void testCleanCSV() {
		List<Map<String, String>> correct_list = parsed ;
		Map<String, String> data = new LinkedHashMap<String, String>();
        data.put("Nom\"", "Lopes   ");
        data.put("Prenom", "Stephane   ");
        parsed.add(data);
        data.clear();
        data.put("Nom", "Lopes");
        data.put("Prenom", "Stephane");
        correct_list.add(data) ;
        assertEquals(correct_list,CsvJsonConverter.cleanCSV(parsed)) ;
	}
	
	/**
	 * Detects if the file entered is not found
	 * 
	 * @see fr.uvsq.abdoumassyasmine.CsvJsonConverter
	 * 
	 * @throws IOException
	 */
	@Test(expected=FileNotFoundException.class)
	public void testConvertToJson_notFound() throws IOException
	{
		File exemple = new File("exemple.json");
		String file_name = mockFile_good.getName().replaceFirst("[.][^.]+$", "");
		CsvJsonConverter.convertToJson(file_name, file_name);
		assertEquals(exemple,mockFile_good);
	}
	
	/**
	 * Tests if the converter converts CSV to JSON without any errors
	 * 
	 * @throws IOException
	 */
//	@Test
//	public void testConvertToJson() throws IOException
//	{
//		File json = new File("exemple.json");
//		String file_name = mockFile_good.getName().replaceFirst("[.][^.]+$", ""); ;
//		CsvJsonConverter.convertToJson("exemple.csv", file_name+".json");
//		assertTrue(FileUtils.contentEquals(json, new File(file_name+".json")));
//	}
//	
	
}
