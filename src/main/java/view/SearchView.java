package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


import codingweek2016.MainMenu;
import codingweek2016.model.SearchRequest;
import codingweek2016.model.Video;

@SuppressWarnings("serial")
public class SearchView extends AbstractView {
	
	private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
	
	private JLabel logo;
	
	private SearchRequest request;
	private List<Video> videos = new ArrayList<Video>();
	
	private JButton searchButton = new JButton("Search");
	private JTextField searchField = new JTextField();

	private JEditorPane resultHead = new JEditorPane();
	private JPanel resultGrid = new JPanel();
	private JPanel mainMenu = new MainMenu();
	
	public SearchView(SearchRequest r) {
		
		request = r;
		request.addObserver(this);
		
		this.setLayout(new BorderLayout());
		
		searchField.setPreferredSize(new Dimension(300,25));
		
		searchButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent arg0) {
				searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
			}
        });
		
		searchButton.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	
            	String text = searchField.getText();
            	
            	if (text.length()>0) {
            		
	            	request.loadVideos(request.searchKeyWord(text));
	
	            	if (videos != null) {
	            		
	            		String head = "<html><body>";
	            		
	            		if (!videos.iterator().hasNext()) {
	            			head += "<p><center><b> There aren't any results for your query.<b/></center></p>";
	            		} else {
	            			head += "<p><center><b>First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + text + "\".<b/><br/><br/><br/><br/>";
	            		}
	            		
	            		head += "</body></html>";
	            		
	            		resultHead.setOpaque(false);
	            		resultHead.setContentType("text/html");
	            		resultHead.setText(head);
	            		
	            		BorderLayout grid = new BorderLayout();
	            		resultGrid.removeAll();
	            		resultGrid.setLayout(grid);
	            		
	            		JScrollPane scrollBar = new JScrollPane(request.display());
	            		scrollBar.setBorder(null);
	            		
	            		resultGrid.add(resultHead, BorderLayout.NORTH);
	            		resultGrid.add(scrollBar, BorderLayout.CENTER);
	            		resultGrid.revalidate();
	            		resultGrid.repaint();
	                }
            	}
            }
        });		
		
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		
		try {
			ImageIcon img = new ImageIcon(ImageIO.read(new File("src/main/resources/youtube.png")));
			logo = new JLabel(new ImageIcon(img.getImage().getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH)));
			searchPanel.add(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		
		this.add(searchPanel, BorderLayout.NORTH);
		this.add(resultGrid, BorderLayout.CENTER);
		this.add(mainMenu,BorderLayout.WEST);
	}

}
