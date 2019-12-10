# CSV-TO-JSON

En attendant d'écrire le manuel, voici le sujet : 

# Projet CProg - 2019
Durant ce projet, vous allez développer un outils de conversion entre fichiers [JSON](https://fr.wikipedia.org/wiki/JavaScript_Object_Notation) et [CSV](https://fr.wikipedia.org/wiki/Comma-separated_values).

Vous devrez respecter les contraintes ci-dessous.

## Description
* L'application se présentera sous la forme d'un programme en ligne de commande.
* L'application permettra de transformer des fichiers [JSON](https://fr.wikipedia.org/wiki/JavaScript_Object_Notation) en [CSV](https://fr.wikipedia.org/wiki/Comma-separated_values) et vice-versa.
* Les règles de correspondance devront être configurables grâce à un fichier de configuration (format à définir).
  * un seul fichier sera nécessaire quel que soit le sens de conversion
  * le nom des attributs cibles devra pouvoir être choisi
  * un attribut cible pourra être généré à partir d'un attribut source ou calculé à partir d'un ou plusieurs attributs sources. Par exemple, une règle pourrait être `attr_cible <- attr_src1 + 2 x attr_src2`
* L'application générera un fichier de configuration par défaut.
* Les rejets (enregistrements non convertis) devront être clairement signalés

## Contraintes techniques
* Le projet est à réaliser en Java 8 ou ultérieur par **groupe de 2 ou 3 étudiants**.
* Le projet sera à présenter le **jeudi 19 décembre** durant les TDs.
* Il utilisera une bibliothèque pour le [traitement des fichiers JSON](https://stackoverflow.com/questions/2591098/how-to-parse-json-in-java) et une pour le [traitement des fichiers CSV](https://stackoverflow.com/questions/101100/csv-api-for-java).
* Le suivi des versions est à réaliser avec `git`/`bitbucket` ou `git`/`github`.
  * les contributions de chaque membre du groupe devront être clairement visibles dans les *commits*.
* Il comportera des tests unitaires (*JUnit 4* ou *JUnit 5*) pour une partie significative du projet.
* Il sera compilable avec ``maven``.
  * le projet utilisera [maven wrapper](https://github.com/takari/maven-wrapper)
  * le projet intégrera javadoc, [checkstyle](https://checkstyle.sourceforge.io/) et [spotbugs](https://spotbugs.github.io/)/[findbugs](http://findbugs.sourceforge.net/) correctement configurés
  * le projet générera également un jar incluant les dépendances
* Il devra être exécutable en tapant ``java -jar mon_projet.jar``.
* Il comportera une documentation sous la forme d'un fichier ``README.md``.
  * la documentation décrira l'usage de l'application (*manuel utilisateur*) ainsi que la conception du jeu (*manuel technique*)
  * en particulier, la documentation précisera les fonctionnalités de l'application, i.e. la limite de ce qui est convertible avec le programme
