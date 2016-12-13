package codingweek2016;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.VideoPlayer;

import codingweek2016.model.SearchRequest;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

@SuppressWarnings("serial")
public class View extends JPanel implements Observer {
	
	private static final long NUMBER_OF_VIDEOS_RETURNED = 3;
	
	private SearchRequest request;
	private List<SearchResult> videos = new ArrayList<SearchResult>();
	
	private JButton searchButton = new JButton("Search");
	private JTextField searchField = new JTextField();
	//private JTextArea resultArea = new JTextArea();
	private JEditorPane resultPane = new JEditorPane();
	private final JFXPanel jfxPanel = new JFXPanel();
	private String id = "";
	
	public View(SearchRequest r) {
		
		
		request = r;
		request.addObserver(this);
		
		this.setLayout(new BorderLayout());
		
		searchField.setPreferredSize(new Dimension(300,25));
		
		searchButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	
            	String text = searchField.getText();
            	request.searchKeyWord(text);

            	if (videos != null) {
            		
            		String result = "<html><head></head><body>";
            		if (!videos.iterator().hasNext()) {
            			result += "<p> There aren't any results for your query.</p>";//resultArea.append(" There aren't any results for your query.");
            		} else {
            			result += "<p><br/>=============================================================<br/>";//resultArea.append("\n=============================================================\n");
            			result += "First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + text + "\".<br/>";//resultArea.append("First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + text + "\".\n"); 
            			result += "=============================================================<br/></p>";//resultArea.append("=============================================================\n");
            		}
            		
    		        //while (videos.iterator().hasNext()) {
            		for (int i=0;i<videos.size();i++) {
	    		            SearchResult singleVideo = videos.get(i);//videos.iterator().next();
	    		            ResourceId rId = singleVideo.getId();
	
	    		            if (rId.getKind().equals("youtube#video")) {
	    		                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
	    		                id = rId.getVideoId();
	    		                //result += "<p> Video Id: " + rId.getVideoId()+"<br/>";//resultArea.append(" Video Id" + rId.getVideoId());
	    		                	//lien vidéo : https://www.youtube.com/watch?v= + id
	    		                result += "<p> Title: " + singleVideo.getSnippet().getTitle()+"<br/>";//resultArea.append(" Title: " + singleVideo.getSnippet().getTitle());
	    		                result += "<img src=" + thumbnail.getUrl()+" width=50 height=50></img><br/>";//resultArea.append(" Thumbnail: " + thumbnail.getUrl());
	    		                result += "<br/>-------------------------------------------------------------<br/></p>";//resultArea.append("\n-------------------------------------------------------------\n");
	    		            }
    		        }
            		//result += "<embed width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/"+id+"\"/>";//"<video width=320 height=240 controls>  <source src=\"https://www.youtube.com/watch?v=" + id + "\" type=\"video/mp4\">Your browser does not support the video tag.</video>";
            		result += "</body></html>";
            		resultPane.setContentType("text/html");
            		resultPane.setText(result);
            		Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            initFX(jfxPanel,id);
                        }			
                    });
                }
            }
        });
		
		

	    // jfxPanel.setScene(new Scene(view));
		
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		
		this.add(searchPanel, BorderLayout.NORTH);
		
		this.add(resultPane, BorderLayout.CENTER);
		this.add(jfxPanel, BorderLayout.SOUTH);
		//this.add(resultArea, BorderLayout.CENTER);
	}

	@SuppressWarnings("unchecked")
	public void update(Observable observable, Object videos) {
		if (videos instanceof List<?>) {
			this.videos = (List<SearchResult>) videos;
		}
	}
	
	private static void initFX(JFXPanel jfxPanel,String id) {
		WebView view = new WebView();
		WebEngine webEngine = view.getEngine();
				webEngine.load(
				"http://www.youtube.com/embed/"+id+"?autoplay=0"
	    );
		view.setPrefSize(200, 200);
        jfxPanel.setScene(new Scene(view));
	}

}
