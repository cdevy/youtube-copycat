# pcd2016

Auteurs

	Guillaume GARCIA
	Gauthier ZAMBAUX
	Maud FARIZON
	Charlotte DEVY

Dépendances

	JavaSE-1.6
	Maven
	JavaFX

Execute

	javac -jar pcd2016.jar

## Roadmap

**** LUNDI ****

Démarrage du cahier des charges
Mise en place de la base de l'interface graphique
	-> zone de recherche et zone d'affichage des résultats qui se fait en HTML
Mise en place de la base de données -> MySQL, JDBC pour Eclipse
	-> idée mise de côté pour le moment pour cause d'impossibilité de conserver les vidéos sur l'ordinateur avec l'API
Choix des technologies : Eclipse, Java : JavaSE-1.6, API YT, Swing, Github, (BD : MySQL ou SQLite, gestionnaire d'accès BD : JDBC)

Mettre en place la Roadmap
Organisation du git : branche master, branche dev
Requêtes avec l'API + barre de recherche, bouton validé et affichage des résultats : OK


**** MARDI ****

Implémentation des classes vidéo et chaîne/compte dans le modèle du MVC
Mise en place de la possibilité de se connecter à son compte YT
	-> l'authentification n'a pas été ajoutée à la release car il est plus difficile que prévu de l'implémenter (nous avons choisi d'utiliser le package com.google.gdata qui pose des problème d'installation)
Mise en place du visionnage de vidéos


**** MERCREDI ****

Mise en ligne de vidéos depuis son compte
Consultation des commentaires d'une vidéos (éventuellement ajout de commentaires et ajout d'un pouce en l'air ou vers le bas)


**** JEUDI ****

Création de playlists (et consultation des playlist existantes)
Mise en place de suggestions : utilisation des suggestions YT, vidéos du moment


**** VENDREDI/FINAL ****

Accès à l'historique des vidéos vues par l'utilisateur

L'application doit permettre de se connecter à son compte YT et d'avoir accès à ses données, notamment les informations de ses vidéos.
Elle doit aussi permettre de faire des requètes et de regarder des vidéos, ces fonctionnalités étant disponnibles dans connextion.


## Importer le projet avec Maven
Pour faciliter l'initialisation de votre environnement de travail, nous vous proposons d'utiliser Maven. Maven se chargera à votre place de télécharger et d'installer les libraires (de YouTube) au moment de la création du projet sous Éclipse ou Netbeans.

### Importation sous Éclipse :

1. File > import > Maven > Existing Maven Project
2. Selectionnez le dossier contenant le pom.xml

### Importation sous Netbeans :

1. File > Open Project
2. Selectionnez le dossier contenant le pom.xml

Si cette solution ne vous convient pas, vous pouvez bien sûr télécharger les libraires "à la main"...
