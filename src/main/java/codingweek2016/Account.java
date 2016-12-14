package codingweek2016;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import com.google.api.services.youtube.YouTube;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.UserProfileEntry;
import com.google.gdata.util.ServiceException; 

public class Account {
	
	
	UserProfileEntry user ;
	
	//
	public static void main(String[] args) throws MalformedURLException, IOException, ServiceException {
		YouTubeService service = new YouTubeService("166631906314-lvle0u9f7i47honorsmecm0onpe79m67.apps.googleusercontent.com", "44334");
		//YouTubeService service = new YouTubeService("");
		String profileUrl = "http://gdata.youtube.com/feeds/api/users/YTdebates";
		UserProfileEntry profileEntry = service.getEntry(new URL(profileUrl), UserProfileEntry.class);
		System.out.println( profileEntry.getFirstName());
		
		/**
		 * Apparement il y a un conflit de library : il semble que c'est guava.jar qui fout la merde !
		 * Askip il faut passer à une version inférieur (voir 2e lien) pour résoudre le pb mais je n'arrive pas à update Maven
		 * 
		 * Réponses au pb :
		 * http://stackoverflow.com/questions/9222665/nosuchmethod-error-getting-a-gdata-service
		 * 
		 * Lien de guava.jar dernière version :
		 * https://github.com/google/guava/wiki/Release10
		 *
		 * En faisaint celà ça génère une autre erreur... Il faudra bien faire le changement de version de guava ! 
		 * A tester donc.
		 */
		
	}
}
