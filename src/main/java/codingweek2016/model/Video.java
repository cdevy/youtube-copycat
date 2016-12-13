package codingweek2016.model;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.google.api.services.youtube.model.SearchResult;

import codingweek2016.VideoViewer;

@SuppressWarnings("serial")
public class Video extends JPanel {
	
	private JButton thumbnail = new JButton();
	private JButton title = new JButton();
	private JTextArea description = new JTextArea();
	private String id;
	
	public Video(SearchResult result) {
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		this.add(Box.createHorizontalStrut(10));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		id = result.getId().getVideoId();
		
		description.setText(result.getSnippet().getDescription());
		
		try {
			ImageIcon img = new ImageIcon(new URL(result.getSnippet().getThumbnails().getDefault().getUrl()));
			thumbnail.setIcon(new ImageIcon(img.getImage().getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH)));
		} catch (Exception e) {
		    System.out.println(e);
		}
		/* Cursor changes when entering and exiting the video thumbnail */
		thumbnail.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				thumbnail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent arg0) {
				thumbnail.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
			}
        });
		/* Action when clicking on the video thumbnail */
		thumbnail.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	new VideoViewer(title.getText(), id);
            }
        });
		new GridBagLayout();
		thumbnail.setOpaque(false);
		thumbnail.setContentAreaFilled(false);
		thumbnail.setBorderPainted(false);
		thumbnail.setPreferredSize(new Dimension(150,100));
		this.add(thumbnail);

		title.setText(result.getSnippet().getTitle());
		/* Cursor changes when entering and exiting the video title */
		title.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				title.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent arg0) {
				title.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
			}
        });
		/* Action when clicking on the video title */
		title.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	new VideoViewer(title.getText(), id);
            }
        });
		title.setOpaque(false);
		title.setContentAreaFilled(false);
		title.setBorderPainted(false);

		description.setOpaque(false);
		
		panel.add(title, BorderLayout.NORTH);
		panel.add(description, BorderLayout.CENTER);
		
		this.add(Box.createHorizontalStrut(10));
		
		this.add(panel);
	}
	
	public String getTitle() {
		return title.getText();
	}
	
	public String getID() {
		return id;
	}
	
}
