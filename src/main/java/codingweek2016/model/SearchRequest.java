package codingweek2016.model;

import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Properties;

import javax.swing.JPanel;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.samples.youtube.cmdline.Auth;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

public class SearchRequest extends Observable {
	
	private static final String PROPERTIES_FILENAME = "youtube.properties";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
    private static YouTube youtube;
    
    private List<Video> videos = new ArrayList<Video>();
	
	public SearchRequest() {
		super();
	}
	
	public List<SearchResult> searchKeyWord(String userInput) {
        Properties properties = new Properties();
        try {
            InputStream in = SearchRequest.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
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

            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
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
	
	public void loadVideos(List<SearchResult> results) {
		for (int i=0;i<results.size();i++) {
			SearchResult singleVideo = results.get(i);
			videos.add(new Video(singleVideo));    
        }
		this.setChanged();
		this.notifyObservers(videos);
	}
	
	public JPanel display() {
		JPanel panel = new JPanel();
		GridLayout grid = new GridLayout(25,1);
		grid.setVgap(10);
		panel.setLayout(grid);
		
		for (int i=0; i<videos.size(); i++) {
    		panel.add(videos.get(i));
    	}	
		return panel;
	} 

}
