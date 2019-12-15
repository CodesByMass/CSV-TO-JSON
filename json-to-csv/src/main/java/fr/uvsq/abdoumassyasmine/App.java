package fr.uvsq.abdoumassyasmine;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.BasicConfigurator;

public class App 
{
    public static void main( String[] args )
    {
    	BasicConfigurator.configure();
    	String chaine="" ;
   	    /*
        * Analyser une chaîne JSON et la convertir en CSV
        */
   	 List<Map<String, String>>  fluxJson = ReadJson.tarit_Json(chaine);
        
   	 fluxJson = ReadJson.parseJson(new File("fichierjson.json"), "UTF-8");
        Set<String> header =  WriteCsv.collectOrdered( fluxJson);
        WriteCsv.writeLargeFile( fluxJson, ";", "fichier_csv.csv", header);  
    }
    }

