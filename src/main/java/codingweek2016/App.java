package codingweek2016;

import java.io.File;

import codingweek2016.MainWindow;

public class App {
	
	public static long NUMBER_OF_VIDEOS_RETURNED = 25;
	
	public static void main(String[] args) {
		//Vérifie le nom/existence du jar !
		if (!new File("youtubeCopycat.jar").exists()) {
		    throw new RuntimeException("L'exécutable doit se nommer \"youtubeCopycat.jar\" !");
		}
		//Lance l'appli à proprement parler
		new MainWindow();
	}
}
