package codingweek2016.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.jayway.jsonpath.JsonPath;

import codingweek2016.MainMenu;
import codingweek2016.MainWindow;
import codingweek2016.model.Authentification;
import codingweek2016.model.SearchRequest;
import codingweek2016.model.Video;
import extraction.GetJarResources;

@SuppressWarnings("serial")
public class TrendingView extends AbstractView {
	
	private static final String PROPERTIES_FILENAME = "youtube.properties";
    private static HttpRequestInitializer httpRequest = new HttpRequestInitializer() {public void initialize(HttpRequest request) throws IOException {}};
	
	private JLabel yTImg;
	
	private GetJarResources jar = new GetJarResources("youtubeCopycat.jar");
	
	private static YouTube youtube;
	
	private JEditorPane resultHead = new JEditorPane();
	private JPanel resultGrid = new JPanel();
	
	public TrendingView(MainWindow mW) throws IOException {
		mainWindow = mW;
		mainMenu = new MainMenu(mainWindow);
		
		this.setLayout(new BorderLayout());
		    	
		List<SearchResult> request = getPopular();
  		
    	loadVideos(request);

    	if (videos != null) {
    		
    		String head = "<html><body>";
    		
    		if (!videos.iterator().hasNext()) {
    			head += "<p><center><b> Impossible to access trending videos.<b/></center></p>";
    		} else {
    			head += "<p><center><b>First " + NUMBER_OF_VIDEOS_RETURNED + " trending videos.<b/><br/><br/><br/><br/>";
    		}
    		
    		head += "</body></html>";
    		
    		resultHead.setOpaque(false);
    		resultHead.setContentType("text/html");
    		resultHead.setText(head);
    		
    		BorderLayout grid = new BorderLayout();
    		//resultGrid.removeAll();
    		resultGrid.setLayout(grid);
    		
    		final JScrollPane scrollBar = new JScrollPane(display());
    		scrollBar.setBorder(null);
    		javax.swing.SwingUtilities.invokeLater(new Runnable() {
     		   public void run() { 
     			   scrollBar.getVerticalScrollBar().setValue(0);
     			  scrollBar.getHorizontalScrollBar().setValue(0);
     		   }
     		});
    		
    		resultGrid.add(resultHead, BorderLayout.NORTH);
    		resultGrid.add(scrollBar, BorderLayout.CENTER);
    		/*resultGrid.revalidate();
    		resultGrid.repaint();*/
        }
    	
		
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		
		this.add(mainMenu,BorderLayout.WEST);
		this.add(resultGrid,BorderLayout.CENTER);
		//ImageIcon img = new ImageIcon(ImageIO.read(new File("src/main/resources/youtube.png")));
		Image img = Toolkit.getDefaultToolkit().createImage(jar.getResource("youtube.png"));
		ImageIcon icon = new ImageIcon(img);
		yTImg = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH)));
		north.add(yTImg);
		this.add(north,BorderLayout.NORTH);
	}
	
	public List<SearchResult> getPopular() throws IOException {
		Properties properties = new Properties();
		String apiKey = null;
        try {
            InputStream in = SearchRequest.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }
        try {
            youtube = new YouTube.Builder(Authentification.HTTP_TRANSPORT, Authentification.JSON_FACTORY, httpRequest).setApplicationName("youtube-trending").build();
            apiKey = properties.getProperty("youtube.apikey");
        } catch (Throwable t) {
            t.printStackTrace();
        }
		
		URL webSite = new URL("https://www.googleapis.com/youtube/v3/videos?part=contentDetails&chart=mostPopular&regionCode=FR&maxResults="+NUMBER_OF_VIDEOS_RETURNED+"&key="+apiKey);
		
        URLConnection connection = null;
		connection = webSite.openConnection();
		
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String str = "";
        String line = "";
        while((line = in.readLine()) != null){
        	str += line;
        } 

        List<String> id = JsonPath.read(str,"$.items..id");

        List<SearchResult> searchResults = new ArrayList<SearchResult>();
        videos = new ArrayList<Video>();
        for (String s:id) {
        	String queryTerm = s;
            
            YouTube.Search.List search = youtube.search().list("id,snippet");
            
            search.setKey(apiKey);
            search.setQ(queryTerm);

            search.setType("video");

            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");
            search.setMaxResults((long) 1);
            
            List<SearchResult> items = search.execute().getItems();
            if (items.size() > 0)
            	searchResults.add(search.execute().getItems().get(0));
        }
        
        return searchResults;
	}
	
	public void loadVideos(List<SearchResult> results) {
		for (int i=0;i<results.size();i++) {
			SearchResult singleVideo = results.get(i);
			videos.add(new Video(singleVideo));    
        }
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
