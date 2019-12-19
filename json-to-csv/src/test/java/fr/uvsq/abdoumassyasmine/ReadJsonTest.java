package fr.uvsq.abdoumassyasmine;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * <b> This tests all the methods of the ReadJson class. </b>
 * 
 * @author Mass' Selmi
 * 
 * @version 1.0
 *
 */
public class ReadJsonTest {
	
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
	 * Tests a corrupted JSON.
	 */
	@Test
	public void testTarit_Json_null() {
		assertNull(ReadJson.tarit_Json("s")) ;
	}
	
	/**
	 * Tests if the method does not return Null.
	 */
	@Test
	public void testTarit_Json() {
		assertNotNull(ReadJson.tarit_Json("{ \"name\":\"John\" }")) ;
	}
	
	/**
	 * Tests if the method does the job.
	 */
	@Test
	public void testTarit_Json_success() {
		data.put("name", "John");
		list_json.add(data);
		assertEquals(list_json,ReadJson.tarit_Json("{ \"name\":\"John\" }")) ;
	}
	
	/**
	 * Parse a no null JSONObject
	 */
	@Test
	public void testParse_JSONObject_NotNull() {
		JSONObject person = new JSONObject();
		  person.put("name", "John");
		  assertNotNull(ReadJson.parse_JSONObject(person));
	}
	
	/**
	 * Check the reliability of the method
	 */
	@Test
	public void testParse_JSONObject() {
		data.put("name", "John");
		JSONObject person = new JSONObject();
		  person.put("name", "John");
		  assertEquals(data,ReadJson.parse_JSONObject(person));
	}
	
	@Test
	public void testParseJson() throws IOException
	{
		mock_file = folder.newFile("mock_file.json");
		data.put("name", "John");
		list_json.add(data);
		FileUtils.write(mock_file, json_string, "UTF-8");
		assertEquals(list_json,ReadJson.parseJson(mock_file,"UTF-8"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testParseJson_null() throws IOException
	{
		mock_file = null ;
		ReadJson.parseJson(mock_file,"UTF-8");
	}
	
	@Test
	public void testParse_JSONArray()
	{
		JSONArray array = new JSONArray() ;
		JSONObject person = new JSONObject();
		  person.put("name", "John");
		data.put("name", "John");
		list_json.add(data);
		array.put(person);
		assertEquals(list_json,ReadJson.parse_JSONArray(array));
	}
	
	@Test(expected=NullPointerException.class)
	public void testParse_JSONArray_null()
	{
		JSONArray array = new JSONArray() ;
		array = null ;
		ReadJson.parse_JSONArray(array);
	}
	
	@Test
	public void testManipuer_AsArray() {
		data.put("name", "John");
		list_json.add(data);
		JSONObject person = new JSONObject();
		  person.put("name", "John");
		assertNotEquals(list_json,ReadJson.manipuler_AsArray(person.toString()));
	}
	
	

}
