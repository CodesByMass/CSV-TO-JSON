package fr.uvsq.abdoumassyasmine;

import org.apache.logging.log4j.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.io.IOException;
import java.util.Scanner;

public class App 
{
	public static void jsonTocsv(String file_in, String file_out)throws IOException, NullPointerException
	{
		/*BasicConfigurator.configure();*/
    	String chaine="" ;
   	    /*
        * Analyser une chaîne JSON et la convertir en CSV
        */
    	List<Map<String, String>>  fluxJson = ReadJson.tarit_Json(chaine);
        
   	 	fluxJson = ReadJson.parseJson(new File(file_in), "UTF-8");
        Set<String> header =  WriteCsv.collectOrdered( fluxJson);
        WriteCsv.writeLargeFile( fluxJson, ";", file_out, header);
	}
	
	public static void ihm()throws IOException, NullPointerException
	{
		int choix;
		boolean arret = false;
		String file_in = new String();
		String file_out = new String();
		
		Scanner _sc = new Scanner(System.in);
		while(arret == false){
			
			System.out.println("CONVERTION CSV TO JSON/JSON TO CSV");
			System.out.println("	1. Json to Csv");
			System.out.println("	2. Csv to Json");
			System.out.println("	3. Quitter ");
			
			choix = _sc.nextInt();
			if(choix == 1)
			{
				
				System.out.println("Veillez Saisir le nom du fichier d'entrée : ");
				file_in = _sc.nextLine();
				
				while(file_in.isEmpty())
				{
					file_in = _sc.nextLine();
				}
				
				System.out.println("Veillez Saisir le nom du fichier de sortie : ");
				file_out = _sc.nextLine();
				while(file_out.isEmpty())
				{
					file_out = _sc.nextLine();
				}
				
				System.out.println("Souhaitez vous convertir o/n?");
				String c = _sc.nextLine();
				if(c.charAt(0) == 'o')
				{
					/**
					 * appel de la fonction de conversion
					 */
					jsonTocsv(file_in,file_out);
					System.out.println("Conversion terminée");
					arret = true;
				}
				else
				{
					System.out.println("Bye Bye");
					arret = false;
				}
				
			}
			else if(choix == 2)
			{
				
				System.out.println("Veillez Saisir le nom du fichier d'entrée : ");
				file_in = _sc.nextLine();
				
				while(file_in.isEmpty())
				{
					file_in = _sc.nextLine();
				}
				
				System.out.println("Veillez Saisir le nom du fichier de sortie : ");
				file_out = _sc.nextLine();
				if(file_out.isEmpty()) file_out = file_in;
				
				System.out.println("Souhaitez vous convertir o/n?");
				String c = _sc.nextLine();
				if(c.charAt(0) == 'o')
				{
					/**
					 * appel de la fonction de conversion
					 */
					CsvJsonConverter.convertToJson(file_in,file_out);
					System.out.println("Conversion terminée");
					arret = true;
				}
				else
				{
					System.out.println("Conversion Annuler");
					arret = false;
				}
			}
			else
			{
				System.out.println("bye bye");
				arret = true;
			}
		}
		_sc.close();
	}

	
    public static void main( String[] args )throws IOException, NullPointerException 
    {
    	ihm();
    }
 }
