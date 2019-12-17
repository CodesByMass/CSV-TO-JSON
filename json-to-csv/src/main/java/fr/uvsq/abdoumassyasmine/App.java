package fr.uvsq.abdoumassyasmine;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args ) throws IOException
    {
    	File csvFile = new File("exemple.csv");
    	CsvJsonConverter.convertToJson(csvFile);
    	
    	
    }
}
