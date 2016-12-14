# pcd2016

Auteurs

	Guillaume GARCIA
	Gauthier ZAMBAUX
	Maud FARIZON
	Charlotte DEVY

Dépendances

	JavaSE-1.6
	Maven
	SWT

Execute

	javac -jar pcd2016.jar

## Roadmap

### Lundi

* Démarrage du cahier des charges

* Mise en place de la base de l'interface graphique

	-> zone de recherche et zone d'affichage des résultats qui se fait en HTML

* Mise en place de la base de données -> MySQL, JDBC pour Eclipse

	-> idée mise de côté pour le moment pour cause d'impossibilité de conserver les vidéos sur l'ordinateur avec l'API

* Choix des technologies : Eclipse, Java : JavaSE-1.6, API YT, Swing, Github, (BD : MySQL ou SQLite, gestionnaire d'accès BD : JDBC)

* Mettre en place la Roadmap

* Organisation du git : branche master, branche dev

* Requêtes avec l'API + barre de recherche, bouton validé et affichage des résultats : OK


### Mardi

* Implémentation des classes vidéo et chaîne/compte dans le modèle du MVC

* Mise en place de la possibilité de se connecter à son compte YT

	-> l'authentification n'a pas été ajoutée à la release car il est plus difficile que prévu de l'implémenter (nous avons choisi d'utiliser le package com.google.gdata qui pose des problème d'installation)

* Mise en place du visionnage de vidéos

	-> le visionnage de vidéos est infructueux avec JavaFX : on peut ouvrir le player Youtube mais on obtient une erreur lorsqu'on essaie de lancer la vidéo.


### Mercredi

* Mise en ligne de vidéos depuis son compte

	-> mise en place réussie du visionnage de vidéos avec SWT (sauf pour certaines vidéos -> trouver la cause du problème)

		=> Problème non trouvé : problèmes d'intégration de SWT dans Swing (et également de Swing dans SWT), avancement non stable : retard et report de la feature.

	-> mise en place de l'authenfication réussie

		=> Le Log In avec ses clés googles est effectif mais il est extrèmement difficile de récupérer un feedback sur la connexion. La mise en ligne d'une vidéo a donc été reportée à plus tard.

* Consultation des commentaires d'une vidéos (éventuellement ajout de commentaires et ajout d'un pouce en l'air ou vers le bas)

	=> Problèmes (voir pour SWT), la feature étant toujours en développement (et instable), la branche comment n'a pas été mergé pour le release de ce jour.

* Amélioration esthétique générale de l'application
	
	=> Il est prévu d'utiliser plusieurs View pour l'application (comme pour les settings, favoris, playlists, etc), donc la classe abstraite est implémentée.

* A ce jour 5 branches coexistent sur le git (master, dev, video, account, comment) et seule la branche dev est à jour avec master après la Release_day_3

### Jeudi

* Résolution des problèmes de mercredi si possible : Possibilité d'afficher au moins une fenêtre de commentaires et éventuellement d'intéragir avec, upload de vidéos,
plusieurs view sur la même fenêtre (settings, playlists, etc en plus de celle affichant le résultat de la recherche)

* Création de playlists (et consultation des playlist existantes)

* Mise en place de suggestions : utilisation des suggestions YT, vidéos du moment


### Vendredi/Version finale

* Accès à l'historique des vidéos vues par l'utilisateur

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
