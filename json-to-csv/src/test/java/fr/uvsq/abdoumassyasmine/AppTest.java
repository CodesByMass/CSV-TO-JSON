package fr.uvsq.abdoumassyasmine;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * <b> This tests the Application main class. </b>
 * 
 * @author Mass' Selmi
 * 
 * @version 1.0
 *
 */
public class AppTest 
{
   

@Rule
public TemporaryFolder folder= new TemporaryFolder();

private File mock_file = null ;
File exemple = new File("exemple.json");

@Test
public void testJsonTocsv() throws IOException{
	File mock_file = new File("mock_file.json");
	FileUtils.copyFile(exemple, mock_file);
	App.jsonTocsv(mock_file.getName(), "mock_file.csv");
	assertTrue(FileUtils.contentEquals(mock_file, exemple));
}

}
