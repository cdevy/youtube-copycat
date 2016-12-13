package codingweek2016.model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.google.api.services.youtube.model.SearchResult;

public class Video extends JPanel {
	
	private JButton thumbnail = new JButton();
	private JButton title = new JButton();
	private String url = "https://www.youtube.com/watch?v=";
	
	public Video(SearchResult result) {
		super();
		this.setLayout(new BorderLayout());
		
		url += result.getId().getVideoId();
		
		try {
			thumbnail.setIcon(new ImageIcon(new URL(result.getSnippet().getThumbnails().getDefault().getUrl())));
		} catch (Exception e) {
		    System.out.println(e);
		}
		thumbnail.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Read video");
            }
        });
		thumbnail.setOpaque(false);
		thumbnail.setContentAreaFilled(false);
		thumbnail.setBorderPainted(false);
		thumbnail.setPreferredSize(new Dimension(200,0));
		this.add(thumbnail, BorderLayout.WEST);
		
		title.setText(result.getSnippet().getTitle());
		title.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Read video");
            }
        });
		title.setOpaque(false);
		title.setContentAreaFilled(false);
		title.setBorderPainted(false);
		this.add(title);
	}
	
	public void display() {
		
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("");
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(900,700));
		
		JPanel panel = new JPanel();
		GridLayout grid = new GridLayout(10,1);
		grid.setVgap(10);
		panel.setLayout(grid);
		List<SearchResult> results = new ArrayList<SearchResult>();
		JTextArea resultPane = new JTextArea();
		
		SearchRequest r = new SearchRequest();
		
    	results = r.searchKeyWord("lol");
    	System.out.println(results);
		    	
    	List<Video> videos = new ArrayList<Video>(); 
    	videos = r.loadVideos(results);
    	System.out.println(videos);
    	
    	resultPane.setOpaque(false);
	    
		if (videos != null) {
			if (!videos.iterator().hasNext()) {
				resultPane.append("There aren't any results for your query.\n");
			} else {
		    	resultPane.append("=============================================================\n");
		    	resultPane.append("First " + 3 + " videos for search on \"" + "lol" + "\".\n");
		    	resultPane.append("=============================================================\n");
		   	}
		}
		
		for (int i=0; i<videos.size(); i++) {
    		panel.add(videos.get(i));
    	}
		
		frame.add(resultPane, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
