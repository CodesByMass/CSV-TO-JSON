package fr.uvsq.abdoumassyasmine;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import java.lang.Object ;
import org.apache.log4j.Category ;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import fr.uvsq.abdoumassyasmine.*;
public class ReadJson {
	/**
	 * Analyser la chaîne JSON
	 * 
	 * @param json
	 * chaine json 
	 * 
	 * @return chaine_json
	 */
    public static List<Map<String, String>> tarit_Json(String json) {
        List<Map<String, String>> string_Json = null;
       try {
            JSONObject jsonObject = new JSONObject(json);
            string_Json = new ArrayList<Map<String, String>>();
            string_Json.add(parse_JSONObject(jsonObject));
        } catch (JSONException js) {
        	
        	string_Json = manipuler_AsArray(json);
        }

        return string_Json;
       }
   /**
    * Analyser le fichier JSON en utilisant l'encodage de caractères spécifié
     * l'encodage de caractères spécifié
     * 
    * @param file
    * fichier json 
    * 
    * @param encodage
    * l'encodage de caractères spécifié
    * 
    * @return string_json
    */
    public static List<Map<String, String>> parseJson(File file, String encodage) {
        List<Map<String, String>> string_Json = null;
        String json = "";

      
            try {
				json = FileUtils.readFileToString(file, encodage);
			} catch (IOException e) {
				e.printStackTrace();
			}
            string_Json = tarit_Json(json);
         return string_Json;
    }
    /**
     *  Analyser un objet JSON 
     *  
     * @param jsonObject
     * object json 
     * 
     * @return string json
     */
    public static Map<String, String> parse_JSONObject(JSONObject jsonObject) {
        Map<String, String> string_Json = new LinkedHashMap<String, String>();
        trans_JSON_OBJECT(jsonObject, string_Json, "");

        return string_Json;
    }
/**
 * Analyser un tableau JSON
 * 
 * @param jsonArray
 * tableau json 
 * 
 * @return chaine
 */
    public static List<Map<String, String>> parse_JSONArray(JSONArray jsonArray) {
        JSONObject jsonObject = null;
        List<Map<String, String>> string_Json = new ArrayList<Map<String, String>>();
        int length = jsonArray.length();

        for (int i = 0; i < length; i++) {
            try {
				jsonObject = jsonArray.getJSONObject(i);
			} catch (JSONException e) {
	
				e.printStackTrace();
			}
            Map<String, String> stringMap = parse_JSONObject(jsonObject);
            string_Json.add(stringMap);
        }

        return string_Json;
    }

   /**
    *  * Gérer la chaîne JSON comme un tableau
    *  
    * @param json
    * chaine json 
    * 
    * @return string json
    */
    private static List<Map<String, String>> manipuler_AsArray(String json) {
        List<Map<String, String>> string_Json = null;

        try {
            JSONArray jsonArray = new JSONArray(json);
            string_Json = parse_JSONArray(jsonArray);
        } catch (JSONException e) {
           
        }

        return string_Json;
    }

    /**
     *  Aplatir l'objet JSON donné
     *  
     * @param obj
     * json object 
     * 
     * @param string_Json
     * chaine json 
     * 
     * @param prefix
     * prefix
     */
    private static void trans_JSON_OBJECT(JSONObject obj, Map<String, String> string_Json, String prefix) {
        Iterator Conteneur = obj.keys();
        String _prefix = prefix != "" ? prefix + "." : "";

        while (Conteneur.hasNext()) {
            String key = Conteneur.next().toString();

            try {
				if (obj.get(key) instanceof JSONObject) {
				    JSONObject jsonObject = (JSONObject) obj.get(key);
				    trans_JSON_OBJECT(jsonObject, string_Json, _prefix + key);
				} else if (obj.get(key) instanceof JSONArray) {
				    JSONArray jsonArray = (JSONArray) obj.get(key);

				    if (jsonArray.length() < 1) {
				        continue;
				    }

				    trans_JSON_ARRAY(jsonArray,string_Json, _prefix + key);
				} else {
				    String value = obj.get(key).toString();

				    if (value != null && !value.equals("null")) {
				    	string_Json.put(_prefix + key, value);
				    }
				}
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
        }

    }

  /**
   * Aplatir le tableau JSON donné
   * 
   * @param obj
   * json array 
   * 
   * @param string_Json
   * chaine json 
   * 
   * @param prefix
   * prefix
   */
    private static void trans_JSON_ARRAY(JSONArray obj, Map<String, String> string_Json, String prefix) {
        int length = obj.length();

        for (int i = 0; i < length; i++) {
            try {
				if (obj.get(i) instanceof JSONArray) {
				    JSONArray jsonArray = (JSONArray) obj.get(i);

				    if (jsonArray.length() < 1) {
				        continue;
				    }

				    trans_JSON_ARRAY(jsonArray, string_Json, prefix + "[" + i + "]");
				} else if (obj.get(i) instanceof JSONObject) {
				    JSONObject jsonObject = (JSONObject) obj.get(i);
				    trans_JSON_OBJECT(jsonObject, string_Json, prefix + "[" + (i + 1) + "]");
				} else {
				    String value = obj.get(i).toString();

				    if (value != null) {
				    	string_Json.put(prefix + "[" + (i + 1) + "]", value);
				    }
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
    }
}