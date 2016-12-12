package codingweek2016;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.api.services.youtube.model.SearchResult;

import codingweek2016.features.Model;

@SuppressWarnings("serial")
public class View extends JPanel implements Observer {
	
	private static final long NUMBER_OF_VIDEOS_RETURNED = 3;
	
	private Model model;
	private List<SearchResult> videos = new ArrayList<SearchResult>();
	
	private JButton searchButton = new JButton("Search");
	private JTextField searchField = new JTextField();
	private JTextArea resultArea = new JTextArea();
	
	public View(Model m) {
		
		model = m;
		model.addObserver(this);
		
		this.setLayout(new BorderLayout());
		
		searchField.setPreferredSize(new Dimension(300,25));
		
		searchButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	String text = searchField.getText();
            	model.searchKeyWord(text);
            	
            	if (videos != null) {
            		if (!videos.iterator().hasNext()) {
            		    resultArea.append(" There aren't any results for your query.");
            		} else {
            			resultArea.append("\n=============================================================\n");
                		resultArea.append("First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + text + "\".\n"); 
                		resultArea.append("=============================================================\n");
            		}
            		
            		/**
            		 * @TODO : correct the display error !
    		        while (videos.iterator().hasNext()) {

    		            SearchResult singleVideo = videos.iterator().next();
    		            ResourceId rId = singleVideo.getId();
    		            System.out.println("1");

    		            if (rId.getKind().equals("youtube#video")) {
    		                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

    		                resultArea.append(" Video Id" + rId.getVideoId());
    		                resultArea.append(" Title: " + singleVideo.getSnippet().getTitle());
    		                resultArea.append(" Thumbnail: " + thumbnail.getUrl());
    		                resultArea.append("\n-------------------------------------------------------------\n");
    		            }
    		        }*/
                }
            }
        });
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		
		this.add(searchPanel, BorderLayout.NORTH);
		
		this.add(resultArea, BorderLayout.CENTER);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable observable, Object videos) {
		if (videos instanceof List<?>) {
			this.videos = (List<SearchResult>) videos;
		}
	}

}
