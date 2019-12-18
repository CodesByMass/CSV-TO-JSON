package fr.uvsq.abdoumassyasmine;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App 
{
	/**
	 * La methode qui permet de convertir un fichier 
	 * json en csv
	 */
	public static void jsonTocsv(String file_in, String file_out)throws IOException, NullPointerException{
		ReadJson parser = new  ReadJson();
		WriteCsv writer = new WriteCsv();

    	String chaine="" ;
   	    /*
        * Analyser une chaîne JSON et la convertir en CSV
        */
    	List<Map<String, String>>  string_json = ReadJson.tarit_Json(chaine);
   	 	string_json = ReadJson.parseJson(new File(file_in), "UTF-8");
        Set<String> header =  WriteCsv.ecriture_csv(string_json);
        WriteCsv.writetocsv( string_json, ";", file_out, header);
	}
	
	/**
	 * La methode qui permet de savoir si un fichier existe
	 */
	public static int checkExistsFile(String directory, String file){
		try (Stream<Path> walk = Files.walk(Paths.get(directory))){

			List<String> result = walk.map(x -> x.toString())
					.filter(f -> f.contains(file))
					.collect(Collectors.toList());
			//result.forEach(System.out::println);
			if(result.isEmpty()) {return 0;}
		}catch (IOException e){
			e.printStackTrace();
		}
		return 1;
	}
	
	/**
	 * Cette methode represente l'interface entre l'utilisateur
	 * et l'application
	 * elle gère les entrées et sorties du
	 */
	public static void ihm()throws IOException, NullPointerException,InvalidFileTypeException,FileNotExistException{
		int choix;
		boolean arret = false;
		String file_in = new String();
		String file_out = new String();
		String directory = new String();
		
		Scanner _sc = new Scanner(System.in);
		System.out.println("Entrée le path de l'application:");
		if(_sc.hasNext()){
			directory = _sc.nextLine();
		}
		while(arret == false){
			System.out.println("CONVERTION CSV TO JSON/JSON TO CSV");
			System.out.println("	1. Json to Csv");
			System.out.println("	2. Csv to Json");
			System.out.println("	3. Quitter ");
			
			choix = _sc.nextInt();
			if(choix == 1){
				System.out.println("Veillez Saisir le nom du fichier d'entrée : ");
				file_in = _sc.nextLine();
				/**
				 * Obligation de l'utilisateur à taper quelque chose avant de passer
				 */
				while(file_in.isEmpty()){
					file_in = _sc.nextLine();
				}
				/**
				 * verification du type du fichier 
				 * si c'est un .json
				 */
				if(file_in.endsWith(".json") == true){
					if(checkExistsFile(directory, file_in)==1){
						System.out.println("Veillez Saisir le nom du fichier de sortie : ");
						file_out = _sc.nextLine();
						while(file_out.isEmpty()){
							file_out = _sc.nextLine();
						}
						if(file_out.endsWith(".csv")== true) {
							System.out.println("Souhaitez vous convertir o/n?");
							String c = _sc.nextLine();
							if(c.charAt(0) == 'o'){
								/**
								 * appel de la fonction de conversion jsonTocsv
								 */
								jsonTocsv(file_in,file_out);
								System.out.println("Conversion terminée");
								arret = true;
							}
							else { System.out.println("Conversion annulée"); arret = false;}
						}
						else{
							throw new InvalidFileTypeException();
						}
					}
					else{
						throw new FileNotExistException();
					}
				}
				else { throw new InvalidFileTypeException();}
			}
			else if(choix == 2){
				System.out.println("Veillez Saisir le nom du fichier d'entrée : ");
				file_in = _sc.nextLine();
				while(file_in.isEmpty()){
					file_in = _sc.nextLine();
				}
				/**
				 * verification du type du fichier 
				 * si c'est un .json
				 */
				if(file_in.endsWith(".csv")==true){
					if(checkExistsFile(directory, file_in)==1){	
						System.out.println("Veillez Saisir le nom du fichier de sortie : ");
						file_out = _sc.nextLine();
						while(file_out.isEmpty()){
							file_out = _sc.nextLine();
						}
						if((file_out.endsWith(".json") == true)){
							System.out.println("Souhaitez vous convertir o/n?");
							String c = _sc.nextLine();
							if(c.charAt(0) == 'o'){
								/**
								 * appel de la fonction de conversion
								 */
								CsvJsonConverter.convertToJson(file_in,file_out);
								System.out.println("Conversion terminée");
								arret = true;
							}
							else { System.out.println("Conversion annulée"); arret = false;}					
						}
						else{
							throw new InvalidFileTypeException();
						}
					}
					else{
						throw new FileNotExistException();
					}
				}
				else{
					throw new InvalidFileTypeException();
				}
			}
			else{
				System.out.println("bye bye");
				arret = true;
			}
			_sc.close();		
		}
}

	
    public static void main( String[] args )throws IOException, NullPointerException,InvalidFileTypeException,FileNotExistException{
    	ihm();
    }
 }
