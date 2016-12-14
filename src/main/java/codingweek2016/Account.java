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
		YouTubeService service = new YouTubeService("166631906314-lvle0u9f7i47honorsmecm0onpe79m67.apps.googleusercontent.com");
		//YouTubeService service = new YouTubeService("");
		//String profileUrl = "http://gdata.youtube.com/feeds/api/users/YTdebates";
		//UserProfileEntry profileEntry = service.getEntry(new URL(profileUrl), UserProfileEntry.class);
		//System.out.println( profileEntry.getFirstName());
		
	}
}
