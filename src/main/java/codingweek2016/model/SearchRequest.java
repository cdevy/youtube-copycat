package codingweek2016.model;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import codingweek2016.App;

public class SearchRequest extends Request {
    
    private List<Video> videos = new ArrayList<Video>();
    private static HttpRequestInitializer httpRequest = new HttpRequestInitializer() {public void initialize(HttpRequest request) throws IOException {}};
	private static String appName = "youtube-search";
    
	public SearchRequest() {
		super(httpRequest,appName);
	}
	
	public List<SearchResult> searchKeyWord(String userInput) throws IOException {
		videos = new ArrayList<Video>();

        String queryTerm = userInput;
        
        YouTube.Search.List search = youtube.search().list("id,snippet");
        
        search.setKey(apiKey);
        search.setQ(queryTerm);

        search.setType("video");

        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
        search.setMaxResults(App.NUMBER_OF_VIDEOS_RETURNED);
        SearchListResponse searchResponse = search.execute();
        
        return(searchResponse.getItems());
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
		GridLayout grid = new GridLayout((int) App.NUMBER_OF_VIDEOS_RETURNED,1);
		grid.setVgap(10);
		panel.setLayout(grid);
		
		for (int i=0; i<videos.size(); i++) {
    		panel.add(videos.get(i));
    	}	
		return panel;
	} 

}
