# youtube-copycat

Auteurs

	Guillaume GARCIA
	Gauthier ZAMBAUX
	Maud FARIZON
	Charlotte DEVY

Dépendances

	JavaSE-1.6
	Maven

Execute

	java -jar youtubeCopycat.jar
	
## Vidéo de démonstration
https://www.youtube.com/watch?v=Lw14028Awc0

## Roadmap

### Lundi

* Démarrage du cahier des charges

* Mise en place de la base de l'interface graphique

		=> Zone de recherche et zone d'affichage des résultats qui se fait en HTML

* Mise en place de la base de données -> MySQL, JDBC pour Eclipse

		=> Nous avons décidé de ne finalement pas créer de base de données pour le moment en raison de l'impossibilité de télécharger les vidéos de YouTube avec l'API imposée par Google.

* Choix des technologies 

		=> Nous avons choisi d'utiliser : Eclipse, Java : JavaSE-1.6, API YouTube, Swing, Github, (BD : MySQL ou SQLite, gestionnaire d'accès BD : JDBC)

* Mettre en place la feuille de route

		=> Celle-ci est encore incomplète à ce stade et doit être terminée.
		
* Organisation du git : nous avons choisi de créer un branche dev et une sous-branche pour chaque thématique ; cette stratégie nous permet de travailler en même temps sans empiéter sur les classes manipulées par les autres.

* Les requêtes faites avec l'API fonctionnent : barre de recherche, bouton valider et affichage des résultats implémentés.


### Mardi

* Implémentation des classes vidéo et chaîne/compte dans le modèle du MVC

		=> La classe Vidéo est mise en place.

* Mise en place de la possibilité de se connecter à son compte YT

		=> L'authentification n'a pas été ajoutée à la release (nous avons choisi d'utiliser le package com.google.gdata qui pose des problèmes d'installation).

* Mise en place du visionnage de vidéos

		=> Le visionnage de vidéos est infructueux avec JavaFX : on peut ouvrir le player Youtube mais on obtient une erreur lorsqu'on essaie de lancer la vidéo (Un erreur s'est produite, veuillez réessayer plus tard).


### Mercredi

* Mise en ligne de vidéos depuis son compte

		=> Nous avons réussi à mettre en place le visionnage de vidéos avec SWT (sauf pour certaines vidéos pour lesquelles la politique de YouTube ne nous le permet pas).

		=> Nous avons rencontré des problèmes d'intégration de SWT dans Swing (et également de Swing dans SWT), la fonctionnalité n'est pour le moment pas stable et nous avons décidé de repousser son intégration dans l'application.

		=> L'authentification est à présent possible.

		=> La connexion avec les clés Google fonctionne mais il est extrèmement difficile de récupérer des informations sur cette connexion. La mise en ligne d'une vidéo a donc été reportée à plus tard.

* Consultation des commentaires d'une vidéo (éventuellement ajout de commentaires et ajout d'un pouce en l'air ou vers le bas)

		=> Problèmes (voir pour SWT), la feature étant toujours en développement (et instable), la branche comment n'a pas été mergé pour le release de ce jour.

* Amélioration esthétique générale de l'application
	
		=> Il est prévu d'utiliser plusieurs View pour l'application (comme pour les settings, favoris, playlists, etc), donc la classe abstraite est implémentée.

* À ce jour 5 branches coexistent sur le git (master, dev, video, account, comment) et seule la branche dev est à jour avec master après la Release_day_3

* Nous avons tenté de créer un .jar exécutable de cette release mais il ne se lance pas : il faudrait trouver un moyen d'y lier l'API Google... À voir Jeudi.

### Jeudi

* Résolution des problèmes de mercredi si possible : possibilité d'afficher au moins une fenêtre de commentaires et éventuellement d'intéragir avec et mettre des vidéos en ligne. 

		=> Problèmes résolus.

* Plusieurs vues sur la même fenêtre (settings, playlists, etc en plus de celle affichant le résultat de la recherche)

		=> Implémentation des vues Settings, Upload et Trends avec succès.

* Création de playlists (et consultation des playlist existantes)

		=> Abandon de l'idée de création et consultation de playlists par manque de temps.

* Mise en place de suggestions : utilisation des suggestions YT, vidéos du moment

		=> L'implémentation de la vue affichant les vidéos tendances en France s'est faite avec succès par recherche dans le fichier .json envoyé par Google comme réponse à la requète.


### Vendredi/Version finale

* Accès à l'historique des vidéos vues par l'utilisateur

		=> Par manque de temps et parce que nous souhaitions avoir une version fonctionnelle pour le rendu, nous avons décidé de ne pas implémenter cette fonctionnalité.


L'application doit permettre de se connecter à son compte YT et d'avoir accès à ses données, notamment les informations de ses vidéos.


## Importer le projet avec Maven
Pour faciliter l'initialisation de votre environnement de travail, nous vous proposons d'utiliser Maven. Maven se chargera à votre place de télécharger et d'installer les libraires (de YouTube) au moment de la création du projet sous Éclipse ou Netbeans.

### Importation sous Éclipse :

1. File > import > Maven > Existing Maven Project
2. Selectionnez le dossier contenant le pom.xml

### Importation sous Netbeans :

1. File > Open Project
2. Selectionnez le dossier contenant le pom.xml

Si cette solution ne vous convient pas, vous pouvez bien sûr télécharger les libraires "à la main"...
