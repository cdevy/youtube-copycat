package codingweek2016.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/*
import com.google.api.services.youtube.YouTube;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.UserProfileEntry;
<<<<<<< HEAD:src/main/java/codingweek2016/model/Account.java
import com.google.gdata.util.ServiceException;*/
=======
import com.google.gdata.util.ServiceException; 

>>>>>>> 70618ca5689d35a559d9f5c661147011d0873292:src/main/java/codingweek2016/Account.java
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.common.collect.Lists;

import codingweek2016.model.Authentification;

public class Account {
	
	//UserProfileEntry user ;
	
	private Credential credential;
	
	public Account() throws IOException {
		List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");
		
		try {
			// Authorize the request.
			credential = Authentification.authorize(scopes, "addsubscription");
		} catch (GoogleJsonResponseException e) {
            System.err.println("GoogleJsonResponseException code: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
	}
	
	public Credential getCredential() {
		return credential;
	}

	public static void main(String[] args) { //throws MalformedURLException, IOException, ServiceException {
		
		try {
            // This object is used to make YouTube Data API requests.
            /*youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential).setApplicationName(
                    "youtube-cmdline-addsubscription-sample").build();*/

        
        } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
        }
		
		/*YouTubeService service = new YouTubeService("166631906314-lvle0u9f7i47honorsmecm0onpe79m67.apps.googleusercontent.com", "44334");
		//YouTubeService service = new YouTubeService("");
		String profileUrl = "http://gdata.youtube.com/feeds/api/users/YTdebates";
		UserProfileEntry profileEntry = service.getEntry(new URL(profileUrl), UserProfileEntry.class);
		System.out.println( profileEntry.getFirstName());*/
		
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
