package fr.uvsq.abdoumassyasmine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import fr.uvsq.abdoumassyasmine.*;

public class WriteCsv {
	
    /**
     * Écrivez le CSV donné d'un json plat dans le fichier donné.
     * 
     * @param string_Json
     * chaine json  
     * 
     * @param separator
     * separateur des colonne csv 
     * 
     * @param fileName
     * fichier csv 
     * 
     * 
     * @param headers
     * les en-tetes des colonnes csv 
     */
    public static void writetocsv(List<Map<String, String>> string_Json, String separator, String fileName, Set<String> headers){
    	String csvString;
        csvString = StringUtils.join(headers.toArray(), separator) + "\n";
        File file = new File(fileName);
        
        try {
            
            FileUtils.write(file, csvString, "ISO8859_1");
            
            for (Map<String, String> map : string_Json) {
            	csvString = "";
            	csvString = entete_sep(headers, map, separator) + "\n";
            	Files.write(Paths.get(fileName), csvString.getBytes("ISO8859_1"), StandardOpenOption.APPEND);
            }            
        } catch (IOException e) {
           
        }
    }    

    /**
     * Obtenez des comlumns séparés en utilisant un séparateur (virgule, demi-colonne, tabulation)
     * 
     * @param headers
     * en-tetes csv 
     * 
     * @param map
     * map qui contient de clés valeurs
     * 
     * @param separator
     * le séparateur utiliser pour séparer les colonne csv
     * 
     * @return une chaine
     * 
     */
    private static String entete_sep(Set<String> headers, Map<String, String> map, String separator) {
        List<String> art= new ArrayList<String>();
        for (String header : headers) {
            String value = map.get(header) == null ? "" : map.get(header).replaceAll("[\\,\\;\\r\\n\\t\\s]+", " "); 
            art.add(value);
        }

        return StringUtils.join(art.toArray(), separator);
    }

  

   /**
    * Obtenez l'en-tête ordonné CSV
    * 
    * @param string_Json 
    * chaine json 
    * 
    * @return liste 
    */
    public static Set<String> ecriture_csv(List<Map<String, String>> string_Json) {
        Set<String> ent = new TreeSet<String>();
        for (Map<String, String> map : string_Json) {
        	ent.addAll(map.keySet());
        }
        return ent;
    }       
}
