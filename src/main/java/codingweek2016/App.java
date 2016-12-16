package codingweek2016;

import java.io.File;

import codingweek2016.MainWindow;

public class App {
	
	public static long NUMBER_OF_VIDEOS_RETURNED = 15;
	
	public static void main(String[] args) {
		// Verify that the jar file exists and has the correct name
		if (!new File("youtubeCopycat.jar").exists()) {
		    throw new RuntimeException("Executable file must be named \"youtubeCopycat.jar\" !");
		}
		//Launch the app
		new MainWindow();
	}
}
