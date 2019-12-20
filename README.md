# Projet JAVA - 2019

## CSV->JSON | JSON->CSV converter

### Project members
Mohamed-Amokrane Selmi <br/>
Yasmine Rabahallah <br/>
Abdoulaye Baldé <br/>

# Guide d'utilisation
Au lancement de l'application, un menu est proposé à l'utilisateur avec 3 choix : <br/>
1 -Convertir un fichier JSON en CSV <br/>
2 -Convertir un fichier CSV en JSON <br/>
3 - Quitter<br/>

L'utilisateur doit taper son choix (1|2|3), il sera redirigé sur le menu tant qu'il n'aura pas saisi une de ces valeurs. <br/>

### 1-Transformation des fichiers JSON en CSV
1- Ecrire le fichier JSON à convertir, qui doit être existant. <br/>
2- Choisir le nom de son fichier CSV de sortie. <br/>
3- Confirmer son choix <br/>
4- La conversion est effectuée. <br/>
5- L'utilisateur est alors notifié du succés ou de l'echec de la conversion. <br/>

### 2-Transformation des fichiers CSV en JSON
1- Ecrire le fichier CSV à convertir, qui doit être existant. <br/>
2- Choisir le nom de son fichier JSON de sortie.<br/>
3- Confirmer son choix <br/>
4- La conversion est effectuée. <br/>
5- L'utilisateur est alors notifié du succés ou de l'echec de la conversion. <br/>


# Manuel Technique

## Classe App
La classe App contient la fonction ihm() qui gère les erreurs d'entrées de l'utilisateur et invoque les fonctions des classes ParseJson, WriteCsv et CsvJsonConverter pour réaliser la conversion demandée par l'utilisateur.

## Transformation des fichiers JSON en CSV 
### Bibilothèque utilisée : org.json
#### les etapes de transformation:
1. lire l'exemple de document json dans arborescence d'objects. </br>
2. répresenter une varieté de structure d'objects ,y compris celles qui contiennent des tableaux et des objets imbriqués <br/>
3. si notre document JSON a un tableau d'objets, nous pouvons reformater chaque objet (transformation d'un tableau json) <br/>
4. traiter les objects de tel sorte de verifie les objects imbriqués et les mettre dans des flux json  <br/>
5. stocker a chaque fois les différents flux json de sortie des transformations dans une liste qui contient un collection (map) de clés_valeurs  <br/>
6. créons csv schéma pour déterminer les en-tetes de colonnes dans fichier csv  <br/>
7. écriture  dans le fichier csv .  <br/>

## Transformation des fichiers CSV en JSON

### Bibilothèque utilisée : Jackson
1. La fonction ConvertToJson prend en paramètre les fichiers d'entrée et de sortie. <br/>
2. La méthode ParseCSV prend le fichier d'entrée et lit les données CSV et les transforme en liste contenant des map. <br/>
3. La méthode CleanCSV prend la liste retournée par ParseCSV et enlève les espaces facultatifs, et renvoie une liste épuré à la fonction ConvertToJson. <br/>
4. la fonction ConvertToJson convertit ensuite la liste retournée par ParseCSV en fichier JSON avec l'oBjectMapper de la bibliothèque Jackon. <br/>

## Limites de l'application 
- Le fichier de configuration n'est pas integré.
- Conversion CSV en JSON avec des objets JSON imbriquées.
- L'utilisateur n'a pas la possibilité de choisir le répertoire de sauvegarde des fichiers. 
