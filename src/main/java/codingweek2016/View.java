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
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import codingweek2016.model.SearchRequest;
import codingweek2016.model.Video;

@SuppressWarnings("serial")
public class View extends JPanel implements Observer {
	
	private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
	
	private SearchRequest request;
	private List<Video> videos = new ArrayList<Video>();
	
	private JButton searchButton = new JButton("Search");
	private JTextField searchField = new JTextField();

	private JEditorPane resultHead = new JEditorPane();
	private JPanel resultGrid = new JPanel();
	
	public View(SearchRequest r) {
		
		request = r;
		request.addObserver(this);
		
		this.setLayout(new BorderLayout());
		
		searchField.setPreferredSize(new Dimension(300,25));
		
		searchButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	
            	String text = searchField.getText();
            	request.loadVideos(request.searchKeyWord(text));

            	if (videos != null) {
            		
            		String head = "<html><body>";
            		
            		if (!videos.iterator().hasNext()) {
            			head += "<p><center> There aren't any results for your query.</center></p>";
            		} else {
            			head += "<p><center><br/>=============================================================<br/>";
            			head += "First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + text + "\".<br/>";
            			head += "=============================================================<br/><br/><br/><br/></center></p>";
            		}
            		
            		head += "</body></html>";
            		
            		resultHead.setOpaque(false);
            		resultHead.setContentType("text/html");
            		resultHead.setText(head);
            		
            		BorderLayout grid = new BorderLayout();
            		resultGrid.removeAll();
            		resultGrid.setLayout(grid);
            		
            		JScrollPane scrollBar = new JScrollPane(request.display());
            		
            		resultGrid.add(resultHead, BorderLayout.NORTH);
            		resultGrid.add(scrollBar, BorderLayout.CENTER);
            		resultGrid.revalidate();
            		resultGrid.repaint();
                }
            }
        });		
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		
		this.add(searchPanel, BorderLayout.NORTH);
		this.add(resultGrid, BorderLayout.CENTER);
	}

	@SuppressWarnings("unchecked")
	public void update(Observable observable, Object videos) {
		if (videos instanceof List<?>) {
			this.videos = (List<Video>) videos;
		}
	}

}
