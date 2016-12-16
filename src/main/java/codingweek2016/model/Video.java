package codingweek2016.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import java.awt.Image;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.SearchResult;

import codingweek2016.CommentsWindow;
import codingweek2016.VideoViewer;
import extraction.GetJarResources;

@SuppressWarnings("serial")
public class Video extends JPanel {
	
	private JButton thumbnail = new JButton();
	private JButton title = new JButton();
	private JButton comments = new JButton("<html><body><u>Comments</u></body><html/>");
	private JTextArea description = new JTextArea();
	private String id;
	
	private GetJarResources jar = new GetJarResources("youtubeCopycat.jar");

	
	public Video(SearchResult result) {
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
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
		
		Image img = Toolkit.getDefaultToolkit().createImage(jar.getResource("icons/commentIcon.png"));
		ImageIcon icon = new ImageIcon(img);
		comments.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));	
		
		comments.setOpaque(false);
		comments.setContentAreaFilled(false);
		comments.setBorderPainted(false);
		comments.setPreferredSize(new Dimension(200,80));
		comments.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				comments.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent arg0) {
				comments.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
			}
        });
		comments.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	new CommentsWindow(title.getText(), id);
            }
        });
		
		panel.add(title, BorderLayout.NORTH);
		panel.add(description, BorderLayout.CENTER);
		panel.add(comments, BorderLayout.SOUTH);
		
		this.add(Box.createHorizontalStrut(10));
		
		this.add(panel);
	}
	
	
	public Video(PlaylistItem item) {
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		this.add(Box.createHorizontalStrut(10));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		id = item.getContentDetails().getVideoId();
		
		description.setText(item.getSnippet().getDescription());
		try {
			ImageIcon img = new ImageIcon(new URL(item.getSnippet().getThumbnails().getDefault().getUrl()));
			thumbnail.setIcon(new ImageIcon(img.getImage().getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH)));
		} catch (Exception e) {
			e.printStackTrace();
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
		thumbnail.setOpaque(false);
		thumbnail.setContentAreaFilled(false);
		thumbnail.setBorderPainted(false);
		thumbnail.setPreferredSize(new Dimension(150,100));
		this.add(thumbnail);

		title.setText(item.getSnippet().getTitle());
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
		
		Image img = Toolkit.getDefaultToolkit().createImage(jar.getResource("icons/commentIcon.png"));
		ImageIcon icon = new ImageIcon(img);
		comments.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));	
		
		comments.setOpaque(false);
		comments.setContentAreaFilled(false);
		comments.setBorderPainted(false);
		comments.setPreferredSize(new Dimension(200,80));
		comments.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseEntered(MouseEvent arg0) {
				comments.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent arg0) {
				comments.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent arg0) {
				// Do nothing
			}

			public void mouseReleased(MouseEvent arg0) {
				// Do nothing
			}
        });
		comments.addActionListener(new ActionListener() {
			  
            public void actionPerformed(ActionEvent e) {
            	new CommentsWindow(title.getText(), id);
            }
        });
		
		panel.add(title, BorderLayout.NORTH);
		panel.add(description, BorderLayout.CENTER);
		panel.add(comments, BorderLayout.SOUTH);
		
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
