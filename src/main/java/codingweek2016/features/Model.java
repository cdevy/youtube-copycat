package codingweek2016.features;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Properties;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.samples.youtube.cmdline.data.Search;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

public class Model extends Observable {
	
	private static final String PROPERTIES_FILENAME = "youtube.properties";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 3;
    private static YouTube youtube;
    
    private List<SearchResult> videos = new ArrayList<SearchResult>();
	
	public Model() {
		super();
	}
	
	public List<Video> loadVideos(List<SearchResult> results) {
		List<Video> videos2 = new ArrayList<Video>();
		for (int i=0;i<results.size();i++) {
			SearchResult singleVideo = results.get(i);
			videos2.add(new Video(singleVideo));    
        }
		return videos2;
	}
	/*
	public void searchKeyWord(String userInput) {
        Properties properties = new Properties();
        try {
            InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }
        
        try {

            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
            	
                public void initialize(HttpRequest request) throws IOException {
                	
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            String queryTerm = userInput;
            
            YouTube.Search.List search = youtube.search().list("id,snippet");

            String apiKey = properties.getProperty("youtube.apikey");
            search.setKey(apiKey);
            search.setQ(queryTerm);
            System.out.println("");
            search.setType("video");

            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();
            videos = searchResponse.getItems();
            System.out.println("");
            /* Notify observer of changes 
            setChanged();
            notifyObservers(videos);
            
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}*/
	
	public List<SearchResult> searchKeyWord(String userInput) {
        Properties properties = new Properties();
        try {
            InputStream in = Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }
        
        try {

            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
            	
                public void initialize(HttpRequest request) throws IOException {
                	
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            String queryTerm = userInput;
            
            YouTube.Search.List search = youtube.search().list("id,snippet");

            String apiKey = properties.getProperty("youtube.apikey");
            search.setKey(apiKey);
            search.setQ(queryTerm);
            System.out.println("");
            search.setType("video");

            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();
            return(searchResponse.getItems());
            
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
		return null;
	}
	
	public List<SearchResult> getVideos() {
		return videos;
	}

}
