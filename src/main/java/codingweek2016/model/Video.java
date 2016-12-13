package codingweek2016.model;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.google.api.services.youtube.model.SearchResult;

import codingweek2016.VideoViewer;

@SuppressWarnings("serial")
public class Video extends JPanel {
	
	private JButton thumbnail = new JButton();
	private JButton title = new JButton();
	private String id;
	
	public Video(SearchResult result) {
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		id = result.getId().getVideoId();
		
		try {
			ImageIcon img = new ImageIcon(new URL(result.getSnippet().getThumbnails().getDefault().getUrl()));
			thumbnail.setIcon(new ImageIcon(img.getImage().getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH)));
		} catch (Exception e) {
		    System.out.println(e);
		}
		thumbnail.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	new VideoViewer(title.getText(), id);
            }
        });
		new GridBagLayout();
		thumbnail.setOpaque(false);
		thumbnail.setContentAreaFilled(false);
		thumbnail.setBorderPainted(false);
		thumbnail.setPreferredSize(new Dimension(100,100));
		this.add(thumbnail);

		
		title.setText(result.getSnippet().getTitle());
		title.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	new VideoViewer(title.getText(), id);
            }
        });
		title.setOpaque(false);
		title.setContentAreaFilled(false);
		title.setBorderPainted(false);
		this.add(title);
	}
	
	public String getTitle() {
		return title.getText();
	}
	
	public String getID() {
		return id;
	}
	
}
