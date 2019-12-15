package fr.uvsq.abdoumassyasmine;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import java.lang.Object ;
import org.apache.log4j.Category ;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadJson {
	 /**
     * Le type JSONObject
     **/
    private static final Class JSON_OBJECT = JSONObject.class;
    /**
    * Le type JSONArray
    **/
    private static final Class JSON_ARRAY = JSONArray.class;
    /**
    * L'enregistreur de classe
    **/
    private static final Logger LOGGER = Logger.getLogger(ReadJson.class);
   
    

    
    
    
     /**
     * Analyser le fichier JSON en utilisant l'encodage de caractères spécifié
     *
     * @param file
     * 
     * @param encodage 
     * 
     * l'encodage de caractères spécifié
     * 
     * @throws  IOException
     **/
    public static List<Map<String, String>> parseJson(File file, String encodage) {
        List<Map<String, String>> fluxJson = null;
        String json = "";

      
            try {
				json = FileUtils.readFileToString(file, encodage);
			} catch (IOException e) {
				e.printStackTrace();
			}
            fluxJson = tarit_Json(json);
         return fluxJson;
    }
     /**
     * Analyser la chaîne JSON
     * 
     * @param json
     * 
     * @throws  JSONException
     
     */
    public static List<Map<String, String>> tarit_Json(String json) {
        List<Map<String, String>> fluxJson = null;
       try {
            JSONObject jsonObject = new JSONObject(json);
            fluxJson = new ArrayList<Map<String, String>>();
            fluxJson.add(parse_JSONObject(jsonObject));
        } catch (JSONException js) {
        	
        	fluxJson = handleAsArray(json);
        }

        return fluxJson;
       }

     /**
      * Analyser un objet JSON 
     * @param JSONObject 
     * 
     * @throws  JSONException
     **/
    public static Map<String, String> parse_JSONObject(JSONObject jsonObject) {
        Map<String, String> fluxJson = new LinkedHashMap<String, String>();
        flatten_JSON_OBJECT(jsonObject, fluxJson, "");

        return fluxJson;
    }

    /**
     * Analyser un tableau JSON
     * @param JSONArray  
     * 
     * @throws  JSONException
     */
    public static List<Map<String, String>> parse_JSONArray(JSONArray jsonArray) {
        JSONObject jsonObject = null;
        List<Map<String, String>> fluxJson = new ArrayList<Map<String, String>>();
        int length = jsonArray.length();

        for (int i = 0; i < length; i++) {
            try {
				jsonObject = jsonArray.getJSONObject(i);
			} catch (JSONException e) {
	
				e.printStackTrace();
			}
            Map<String, String> stringMap = parse_JSONObject(jsonObject);
            fluxJson.add(stringMap);
        }

        return fluxJson;
    }

    /**
     * Gérer la chaîne JSON comme un tableau
     * @param  json 
     * 
     * @throws  JSONException
     */
    private static List<Map<String, String>> handleAsArray(String json) {
        List<Map<String, String>> fluxJson = null;

        try {
            JSONArray jsonArray = new JSONArray(json);
            fluxJson = parse_JSONArray(jsonArray);
        } catch (JSONException e) {
           
        }

        return fluxJson;
    }

    /**
    * Aplatir l'objet JSON donné
    * @param obj
     * @param fluxJson
     * @param prefix
     * @throws  JSONException
    */
    private static void flatten_JSON_OBJECT(JSONObject obj, Map<String, String> fluxJson, String prefix) {
        Iterator Conteneur = obj.keys();
        String _prefix = prefix != "" ? prefix + "." : "";

        while (Conteneur.hasNext()) {
            String key = Conteneur.next().toString();

            try {
				if (obj.get(key).getClass() == JSON_OBJECT) {
				    JSONObject jsonObject = (JSONObject) obj.get(key);
				    flatten_JSON_OBJECT(jsonObject, fluxJson, _prefix + key);
				} else if (obj.get(key).getClass() == JSON_ARRAY) {
				    JSONArray jsonArray = (JSONArray) obj.get(key);

				    if (jsonArray.length() < 1) {
				        continue;
				    }

				    flatten_JSON_ARRAY(jsonArray, fluxJson, _prefix + key);
				} else {
				    String value = obj.get(key).toString();

				    if (value != null && !value.equals("null")) {
				    	fluxJson.put(_prefix + key, value);
				    }
				}
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
        }

    }

     /**
     * Aplatir le tableau JSON donné
     * @param obj
     * @param fluxJson
     * @param prefix
     * @throws  JSONException
     */
    private static void flatten_JSON_ARRAY(JSONArray obj, Map<String, String> fluxJson, String prefix) {
        int length = obj.length();

        for (int i = 0; i < length; i++) {
            try {
				if (obj.get(i).getClass() == JSON_ARRAY) {
				    JSONArray jsonArray = (JSONArray) obj.get(i);

				    if (jsonArray.length() < 1) {
				        continue;
				    }

				    flatten_JSON_ARRAY(jsonArray, fluxJson, prefix + "[" + i + "]");
				} else if (obj.get(i).getClass() == JSON_OBJECT) {
				    JSONObject jsonObject = (JSONObject) obj.get(i);
				    flatten_JSON_OBJECT(jsonObject, fluxJson, prefix + "[" + (i + 1) + "]");
				} else {
				    String value = obj.get(i).toString();

				    if (value != null) {
				    	fluxJson.put(prefix + "[" + (i + 1) + "]", value);
				    }
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
    }
}